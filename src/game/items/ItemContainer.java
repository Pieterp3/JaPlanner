package game.items;

import game.definitions.impl.ItemDefinition;
import util.structures.List;

public class ItemContainer {

	private List<Item> items;
	private int maxSize;

	public ItemContainer() {
		items = new List<Item>();
		maxSize = 10;
	}

	public ItemContainer(int size) {
		items = new List<Item>();
		maxSize = size;
	}

	public boolean contains(int id, int amount) {
		ItemDefinition def = ItemManager.getDefinition(id);
		if (def == null)
			return false;
		return getAmount(id) >= amount;
	}

	public int getAmount(int id) {
		ItemDefinition def = ItemManager.getDefinition(id);
		if (def == null)
			return 0;
		int amount = 0;
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			if (item.getDefinition().getId() == id) {
				amount += item.getAmount();
				if (def.isStackable())
					break;
			}
		}
		return amount;
	}

	public boolean addItem(Item item) {
		if (items.size() >= maxSize)
			return false;
		ItemDefinition def = item.getDefinition();
		if (def == null)
			return false;
		if (def.isStackable()) {
			for (int i = 0; i < items.size(); i++) {
				Item it = items.get(i);
				if (it.getDefinition().getId() == item.getDefinition().getId()) {
					it.add(item.getAmount());
					return true;
				}
			}
		}
		items.add(item);
		return true;
	}

	public boolean removeItem(Item item) {
		if (items.size() <= 0)
			return false;
		ItemDefinition def = item.getDefinition();
		if (def == null)
			return false;
		if (def.isStackable()) {
			for (int i = 0; i < items.size(); i++) {
				Item it = items.get(i);
				if (it.getDefinition().getId() == item.getDefinition().getId()) {
					it.remove(item.getAmount());
					if (it.getAmount() <= 0)
						items.remove(i);
					return true;
				}
			}
		}
		for (int i = 0; i < items.size(); i++) {
			Item it = items.get(i);
			if (it.getDefinition().getId() == item.getDefinition().getId()) {
				items.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean hasSpaceFor(List<Item> items) {
		int neededSlots = 0;
		for (Item item : items) {
			ItemDefinition def = item.getDefinition();
			if (def.isStackable() && contains(item))
				continue;
			neededSlots += 1;
		}
		return getFreeSlots() >= neededSlots;
	}

	public boolean hasSpaceFor(Item item) {
		ItemDefinition def = item.getDefinition();
		if (def == null)
			return false;
		if (def.isStackable()) {
			for (int i = 0; i < items.size(); i++) {
				Item it = items.get(i);
				if (it.getDefinition().getId() == item.getDefinition().getId()) {
					return true;
				}
			}
		} else if (items.size() >= maxSize)
			return false;
		return true;
	}

	public void swapItems(ItemContainer container, int slot1, int slot2) {
		Item item1 = items.get(slot1);
		Item item2 = container.items.get(slot2);
		items.set(item2, slot1);
		container.items.set(item1, slot2);
	}

	public boolean hasSpaceFor(Item... items) {
		int neededSlots = 0;
		for (Item item : items) {
			ItemDefinition def = item.getDefinition();
			if (def.isStackable() && contains(item))
				continue;
			neededSlots += 1;
		}
		return getFreeSlots() >= neededSlots;
	}

	public boolean removeItem(int id, int amount) {
		return removeItem(new Item(id, amount));
	}

	public boolean removeItem(int id) {
		return removeItem(new Item(id));
	}

	public boolean contains(Item item) {
		return contains(item.getDefinition().getId(), item.getAmount());
	}

	public long getValue() {
		long value = 0;
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			value += item.getDefinition().getValue() * item.getAmount();
		}
		return value;
	}

	public int getFreeSlots() {
		return maxSize - items.size();
	}

	public int getSize() {
		return items.size();
	}

	public int getMaxSize() {
		return maxSize;
	}

	public Item getItemInSlot(int slot) {
		return items.get(slot);
	}

	public List<Item> getMostValueableStacks(int stacks) {
		List<Item> list = new List<Item>();
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			if (list.size() < stacks) {
				list.add(item);
				continue;
			}
			for (int j = 0; j < list.size(); j++) {
				Item it = list.get(j);
				if (item.getDefinition().getValue() > it.getDefinition().getValue()) {
					list.remove(j);
					list.add(item);
					break;
				}
			}
		}
		return list;
	}

	public List<Item> getMostValueableStack() {
		return getMostValueableStacks(1);
	}

	public void sortByNameAscending() {
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			for (int j = 0; j < items.size(); j++) {
				Item it = items.get(j);
				if (item.getDefinition().getName().compareTo(it.getDefinition().getName()) < 0) {
					items.set(it, i);
					items.set(item, j);
				}
			}
		}
	}

	public void sortByValueAscending() {
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			for (int j = 0; j < items.size(); j++) {
				Item it = items.get(j);
				if (item.getDefinition().getValue() < it.getDefinition().getValue()) {
					items.set(it, i);
					items.set(item, j);
				}
			}
		}
	}

	public void sortByValueDescending() {
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			for (int j = 0; j < items.size(); j++) {
				Item it = items.get(j);
				if (item.getDefinition().getValue() > it.getDefinition().getValue()) {
					items.set(it, i);
					items.set(item, j);
				}
			}
		}
	}

	public void sortByNameDescending() {
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			for (int j = 0; j < items.size(); j++) {
				Item it = items.get(j);
				if (item.getDefinition().getName().compareTo(it.getDefinition().getName()) > 0) {
					items.set(it, i);
					items.set(item, j);
				}
			}
		}
	}

	public void clear() {
		items.clear();
	}

	public List<Item> getItems() {
		return items;
	}

	public void addItems(List<Item> items) {
		for (Item item : items) {
			addItem(item);
		}
	}

}
