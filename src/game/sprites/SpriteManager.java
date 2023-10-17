package game.sprites;

import util.io.Load;
import util.io.Save;
import util.structures.Map;
import util.structures.lists.List;

public class SpriteManager {
	
	private static List<Sprite> sprites;
	public static final String SPRITE_CONFIG_FILE = "Sprites";

	public static void init() {
		sprites = new List<>();
		loadSprites();
	}

	public static void reloadSprites() {
		sprites = new List<>();
		loadSprites();
	}

	private static void loadSprites() {
		Map<String, String> spriteData = Load.loadCFG(SPRITE_CONFIG_FILE);
		for (String name : spriteData.keySet()) {
			Sprite sprite = new Sprite(name);
			sprite.loadFromConfigLing(spriteData.get(name));
			sprites.add(sprite);
		}
	}

	public static Sprite getSprite(String name) {
		for (Sprite sprite : sprites) {
			if (sprite.getName().equals(name))
				return sprite;
		}
		return null;
	}

	public static List<Sprite> getSprites() {
		return sprites;
	}

	public static void saveSprites() {
		Map<String, String> spriteData = new Map<>();
		for (Sprite sprite : sprites) {
			spriteData.put(sprite.getName(), sprite.createConfigString());
		}
		Save.saveConfigFile(SPRITE_CONFIG_FILE, spriteData);
	}

}
