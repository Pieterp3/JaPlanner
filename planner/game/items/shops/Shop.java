package game.items.shops;

import game.items.Item;
import game.items.ItemContainer;
import util.structures.List;

public class Shop extends ItemContainer {
	
	private String name;
	private List<Item> defaultItems;
	private ShopManager shopManager;

	public Shop(ShopManager shopManager, String name, List<Item> defaultItems) {
		this.shopManager = shopManager;
		this.name = name;
		this.defaultItems = defaultItems;
	}

	public String getName() {
		return name;
	}

	public List<Item> getDefaultItems() {
		return defaultItems;
	}

	public ShopManager getShopManager() {
		return shopManager;
	}

}
