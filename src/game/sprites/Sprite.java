package game.sprites;

import ui.graphics.Color;
import ui.graphics.Graphics;
import util.math.Point;
import util.structures.lists.List;
import util.structures.queues.LoopingQueue;
import util.structures.queues.Queue;

public class Sprite {

	private List<String[][]> spriteMap;
	private boolean playing;
	private Point topLeftLocation;
	private int drawnWidth, drawnHeight;
	private String name;
	private int displayStage = 0;
	private int animDelay = 200;
	private long lastAnim;

	public Sprite(String name) {
		this.spriteMap = new List<>();
		this.name = name;
		this.playing = false;
		this.topLeftLocation = new Point(0, 0);
		this.drawnWidth = 16;
		this.drawnHeight = 16;
		this.displayStage = 0;
	}

	public void createBlankStage(int size) {
		String[][] colors = new String[size][size];
		for (int y = 0; y < colors.length; y++) {
			for (int x = 0; x < colors[y].length; x++) {
				colors[y][x] = "000000";
			}
		}
		addSpriteStage(colors);
	}

	public void play() {
		playing = true;
	}

	public void stop() {
		playing = false;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void advance() {
		displayStage += 1;
		if (displayStage >= spriteMap.size()) {
			displayStage = 0;
		}
	}

	public void rewind() {
		displayStage -= 1;
		if (displayStage < 0) {
			displayStage = spriteMap.size() - 1;
		}
	}

	public void addSpriteStage(String[][] colors) {
		spriteMap.add(colors);
	}

	public void draw(Graphics g) {
		String[][] colors = spriteMap.get(displayStage);
		for (int y = 0; y < colors.length; y++) {
			for (int x = 0; x < colors[y].length; x++) {
				if (colors[y][x] != null) {
					g.setColor(Color.decode(colors[y][x]));
					g.fillRect(topLeftLocation.getIntX() + x * drawnWidth, topLeftLocation.getIntY() + y * drawnHeight,
							drawnWidth, drawnHeight);
				}
			}
		}
		if (isPlaying()) {
			if (System.currentTimeMillis() - lastAnim > animDelay) {
				advance();
				lastAnim = System.currentTimeMillis();
			}
		}
	}

	public int getDisplayStage() {
		return displayStage + 1;
	}

	public String createConfigString() {
		String configString = "";
		for (int i = 0; i < spriteMap.size(); i++) {
			configString += spriteMap.get(i).length + "," + spriteMap.get(i)[0].length + ",";
			for (int y = 0; y < spriteMap.get(i).length; y++) {
				for (int x = 0; x < spriteMap.get(i)[y].length; x++) {
					configString += spriteMap.get(i)[y][x] + ",";
				}
			}
		}
		return configString.substring(0, configString.length() - 1);
	}

	public void loadFromConfigLing(String data) {
		String[] splitData = data.split(",");
		int index = 0;
		while (index < splitData.length) {
			int height = Integer.parseInt(splitData[index++]);
			int width = Integer.parseInt(splitData[index++]);
			String[][] colors = new String[height][width];
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++)
					colors[y][x] = splitData[index++];
			}
			addSpriteStage(colors);
		}
	}

	public void setTopLeftLocation(Point topLeftLocation) {
		this.topLeftLocation = topLeftLocation;
	}

	public void setDrawnWidth(int drawnWidth) {
		this.drawnWidth = drawnWidth;
	}

	public void setDrawnHeight(int drawnHeight) {
		this.drawnHeight = drawnHeight;
	}

	public String getName() {
		return name;
	}

	public int getDrawnHeight() {
		return drawnHeight;
	}

	public int getDrawnWidth() {
		return drawnWidth;
	}

    public int getStageCount() {
        return spriteMap.size();
    }

    public String getColor(int intX, int intY) {
        return spriteMap.get(displayStage)[intY][intX];
    }

	public void removeCurrentStage() {
		spriteMap.remove(displayStage);
	}

	public void setColor(int intX, int intY, Color selectedColor) {
		spriteMap.get(displayStage)[intY][intX] = selectedColor.getHex().substring(1);
	}

}
