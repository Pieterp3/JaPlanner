package game.items.shops;

import game.entity.live.LiveEntity;
import game.items.Item;
import game.items.ItemContainer;
import util.structures.List;

public class Shop extends ItemContainer {

	private String name;
	private List<Item> defaultItems;
	private List<Item> stock;
	private ShopManager shopManager;
	private int currencyId;
	private double stockPriceModifier = 0.85;

	public Shop(ShopManager shopManager, String name, List<Item> defaultItems) {
		this(shopManager, name, defaultItems, ShopManager.DEFAULT_CURRENCY_ID);
	}

	public Shop(ShopManager shopManager, String name, List<Item> defaultItems, int currencyId) {
		this.shopManager = shopManager;
		this.name = name;
		this.defaultItems = defaultItems;
		this.currencyId = currencyId;
		this.stock = new List<Item>();
		stock.add(defaultItems);
	}

	public String getName() {
		return name;
	}

	public List<Item> getDefaultItems() {
		return defaultItems;
	}

	public List<Item> getStock() {
		return stock;
	}

	public ShopManager getShopManager() {
		return shopManager;
	}

	public boolean purchaseItem(LiveEntity player, Item item) {
		if (!player.getInventory().hasSpaceFor(item)) {
			return false;
		}
		int amount = Math.min(item.getAmount(), getStockAmount(item.getId()));
		int cost = amount * item.getValue();
		if (player.getInventory().contains(currencyId, cost)) {
			player.getInventory().removeItem(currencyId, cost);
			player.getInventory().addItem(item);
			stock.remove(item);
			return true;
		}
		return false;
	}

	public boolean sellItem(LiveEntity player, Item item) {
		if (player.getInventory().contains(item)) {
			player.getInventory().removeItem(item);
			player.getInventory().addItem(new Item(currencyId, (int)(item.getValue()*item.getAmount()*stockPriceModifier)));
			stock.add(item);
			return true;
		}
		return false;
	}

	public int getStockAmount(int id) {
		int amount = 0;
		for (int i = 0; i < stock.size(); i++) {
			Item item = stock.get(i);
			if (item.getId() == id) {
				amount += item.getAmount();
			}
		}
		return amount;
	}

}
