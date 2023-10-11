package game.items.shops;

import game.GameManager;
import game.items.Item;
import util.structures.List;

public class ShopManager {
	
	private GameManager gameManager;
	private List<Shop> shops;
	private int defaultCurrencyId = 100;

	public ShopManager(GameManager gameManager) {
		this.gameManager = gameManager;
		this.shops = new List<Shop>();
		initShops();
	}

	private void initShops() {
		//TODO: load shops through savemanager
		List<Item> items = new List<Item>();
		items.add(new Item[] {new Item(1, 100), new Item(2, 100), new Item(3, 100)});
		shops.add(new Shop(this, "Test Shop", items));
	}

	public GameManager getGameManager() {
		return gameManager;
	}

	public List<Shop> getShops() {
		return shops;
	}

	public int getDefaultCurrencyId() {
		return defaultCurrencyId;
	}

}
