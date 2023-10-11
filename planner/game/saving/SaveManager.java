package game.saving;

import game.GameManager;
import game.entity.player.Player;

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

	public void savePlayer(Player player) {
		
	}

	public void loadPlayer(String username) {
		
	}

	
	public void savePlayers() {
		
	}

	public void saveNpcs() {
		
	}

	public void loadNpcs() {
		
	}

	public void reloadNpcs() {
		
	}

	public void saveObjects() {
		
	}

	public void loadObjects() {
		
	}

	public void reloadObjects() {
		
	}

	public void saveOptions() {
		
	}

	public void loadOptions() {
		
	}
	
	public void reloadOptions() {
		
	}

	public static String getItemDirectory() {
		return ITEM_SAVE_DIRECTORY;
	}


}
