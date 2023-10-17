package floorplanning.items;

import util.structures.lists.List;

public class Pricing {

	private String name;
	private double installPrice;
	private double materialPrice;
	private double removalPrice;
	private double moveAndReplacePrice;

	public Pricing(String name, double installPrice, double materialPrice, double removalPrice,
			double moveAndReplacePrice) {
		this.name = name;
		this.installPrice = installPrice;
		this.materialPrice = materialPrice;
		this.removalPrice = removalPrice;
		this.moveAndReplacePrice = moveAndReplacePrice;
	}

	public String getName() {
		return name;
	}

	public double getInstallPrice() {
		return installPrice;
	}

	public double getMaterialPrice() {
		return materialPrice;
	}

	public double getRemovalPrice() {
		return removalPrice;
	}

	public double getMoveAndReplacePrice() {
		return moveAndReplacePrice;
	}

	public double getPrice(boolean install, boolean material, boolean removal, boolean moveAndReplace) {
		double price = 0;
		if (install) {
			price += installPrice;
		}
		if (material) {
			price += materialPrice;
		}
		if (removal) {
			price += removalPrice;
		}
		if (moveAndReplace) {
			price += moveAndReplacePrice;
		}
		return price;
	}

	public double getPrice(List<PriceType> priceTypes) {
		return getPrice(priceTypes.contains(PriceType.INSTALL), priceTypes.contains(PriceType.MATERIAL),
				priceTypes.contains(PriceType.REMOVE), priceTypes.contains(PriceType.MOVE_AND_REPLACE));
	}
	
}
