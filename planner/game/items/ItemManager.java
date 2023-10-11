package game.items;

import game.definitions.impl.ItemDefinition;
import util.io.Load;
import util.io.Save;
import util.structures.List;
import util.structures.Map;

/**
 * Manipulates items.
 */
public class ItemManager {
	
	private static Map<Integer, ItemDefinition> definitions = new Map<>();

	public static ItemDefinition getDefinition(int id) {
		return definitions.get(id);
	}

	public static void addDefinition(ItemDefinition definition) {
		definitions.put(definition.getId(), definition);
	}

	public static void removeDefinition(int id) {
		definitions.remove(id);
	}

	public static void removeDefinition(ItemDefinition definition) {
		definitions.remove(definition.getId());
	}

	public static void loadDefinitions() {
		Map<String, String> lines = Load.loadCFG("ItemDefinitions");
		List<String> keys = lines.getKeys();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String[] values = lines.get(key).split(";");
			int id = Integer.parseInt(key);
			String name = values[0];
			int value = Integer.parseInt(values[1]);
			boolean stackable = Boolean.parseBoolean(values[2]);
			String description = values[3];
			ItemDefinition definition = new ItemDefinition(id, name, value, stackable, description);
			addDefinition(definition);
		}
	}

	public static void saveDefinitions() {
		Map<String, String> lines = new Map<>();
		List<Integer> keys = definitions.getKeys();
		for (int i = 0; i < keys.size(); i++) {
			int key = keys.get(i);
			ItemDefinition definition = definitions.get(key);
			String value = definition.getName() + ";" + definition.getValue() + ";" + definition.isStackable() + ";" + definition.getDescription();
			lines.put(key + "", value);
		}
		Save.saveConfigFile("ItemDefinitions", lines);
	}

	public static Map<Integer, ItemDefinition> getDefinitions() {
		return definitions;
	}

    public static int getNextAvailableId() {
        int i = 0;
		while(definitions.containsKey(i)) {
			i += 1;
		}
		return i;
    }

}
