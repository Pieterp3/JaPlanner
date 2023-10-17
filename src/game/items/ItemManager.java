package game.items;

import game.definitions.impl.ItemCombatDefinition;
import game.definitions.impl.ItemDefinition;
import util.io.Load;
import util.io.Save;
import util.structures.Map;
import util.structures.lists.List;

/**
 * Manipulates items.
 */
public class ItemManager {
	
	//TODO make managers for shops, markets, items and other systems extend a base manager class
	private static Map<Integer, ItemDefinition> definitions = new Map<>();
	private static Map<Integer, ItemCombatDefinition> combatDefinitions = new Map<>();

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
		lines = Load.loadCFG("ItemCombatDefinitions");
		keys = lines.getKeys();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String[] values = lines.get(key).split(";");
			int id = Integer.parseInt(key);
			int attackSpeed = Integer.parseInt(values[0]);
			int attackRange = Integer.parseInt(values[1]);
			int meleeAccuracy = Integer.parseInt(values[2]);
			int meleeStrength = Integer.parseInt(values[3]);
			int rangeAccuracy = Integer.parseInt(values[4]);
			int rangeStrength = Integer.parseInt(values[5]);
			int magicAccuracy = Integer.parseInt(values[6]);
			int magicDamage = Integer.parseInt(values[7]);
			ItemCombatDefinition definition = new ItemCombatDefinition(getDefinition(id), attackSpeed, attackRange, meleeAccuracy, meleeStrength, rangeAccuracy, rangeStrength, magicAccuracy, magicDamage);
			combatDefinitions.put(id, definition);
		}
	}

	public static ItemCombatDefinition getCombatDefinition(int id) {
		return combatDefinitions.get(id);
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
		lines = new Map<>();
		keys = combatDefinitions.getKeys();
		for (int i = 0; i < keys.size(); i++) {
			int key = keys.get(i);
			ItemCombatDefinition definition = combatDefinitions.get(key);
			String value = definition.getAttackSpeed() + ";" + definition.getAttackRange() + ";" + definition.getMeleeAccuracy() + ";" + definition.getMeleeStrength() + ";" + definition.getRangeAccuracy() + ";" + definition.getRangeStrength() + ";" + definition.getMagicAccuracy() + ";" + definition.getMagicDamage();
			lines.put(key + "", value);
		}
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
