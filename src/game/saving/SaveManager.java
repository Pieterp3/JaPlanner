package game.saving;

import game.GameManager;

public class SaveManager {
	
	private static final String PLAYER_SAVE_DIRECTORY = "game/players/";
	private static final String ITEM_SAVE_DIRECTORY = "game/items/";
	private static final String NPC_SAVE_DIRECTORY = "game/npcs/";
	private static final String OBJECT_SAVE_DIRECTORY = "game/objects/";
	private static final String OPTIONS = "game/Options";

	private GameManager gameManager;

	public SaveManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	public GameManager getGameManager() {
		return gameManager;
	}

	public String getPlayerSaveDirectory() {
		return PLAYER_SAVE_DIRECTORY;
	}

	public String getNpcSaveDirectory() {
		return NPC_SAVE_DIRECTORY;
	}

	public String getObjectSaveDirectory() {
		return OBJECT_SAVE_DIRECTORY;
	}

	public String getOptions() {
		return OPTIONS;
	}

	public static String getItemDirectory() {
		return ITEM_SAVE_DIRECTORY;
	}


}
