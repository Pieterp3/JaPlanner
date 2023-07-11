package floorplanning.items;

import util.io.Load;
import util.structures.List;
import util.structures.Map;

public class FlooringItemManager {
	
	private static Map<String, Pricing> flooringItemPricing;

	public static void init() {
		flooringItemPricing = new Map<>();
		Map<String, String> data = Load.loadCFG("FlooringItemData");
		for (String name : data.keySet()) {
			String[] values = data.get(name).split("\t");
			double installPrice = Double.parseDouble(values[0]);
			double materialPrice = Double.parseDouble(values[1]);
			double removalPrice = Double.parseDouble(values[2]);
			double moveAndReplacePrice = Double.parseDouble(values[3]);
			Pricing pricing = new Pricing(name, installPrice, materialPrice, removalPrice, moveAndReplacePrice);
			flooringItemPricing.put(name, pricing);
		}
	}

	public static List<String> getFlooringItemNames() {
		return flooringItemPricing.getKeys();
	}

	public static Pricing getPricing(String name) {
		return flooringItemPricing.get(name);
	}

}