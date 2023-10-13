package floorplanning.items;

import util.io.Load;
import util.structures.List;
import util.structures.Map;

public class FlooringItemManager {

	private static Map<String, Pricing> flooringItemPricing;
	private static Map<String, String> flooringItemUppercases;

	public static void init() {
		flooringItemPricing = new Map<>();
		flooringItemUppercases = new Map<>();
		List<String> data = Load.loadData("FlooringItemData");
		for (String line : data) {
			String[] values = line.split("=");
			double installPrice = Double.parseDouble(values[1]);
			double materialPrice = Double.parseDouble(values[2]);
			double removalPrice = Double.parseDouble(values[3]);
			double moveAndReplacePrice = Double.parseDouble(values[4]);
			Pricing pricing = new Pricing(values[0], installPrice, materialPrice, removalPrice, moveAndReplacePrice);
			flooringItemPricing.put(values[0], pricing);
			flooringItemUppercases.put(values[0].toUpperCase(), values[0]);
		}
	}

	public static List<String> getFlooringItemNames() {
		return flooringItemPricing.getKeys();
	}

	public static Pricing getPricing(String name) {
		if (flooringItemUppercases.containsKey(name.toUpperCase()))
			return flooringItemPricing.get(flooringItemUppercases.get(name.toUpperCase()));
		return flooringItemPricing.get(name);
	}

}