package floorplanning.items;

import util.structures.List;

public class FlooringItem {
	
	private Pricing price;
	private double quantity;
	private List<PriceType> priceTypes;

	public FlooringItem(Pricing price, double quantity) {
		if (this.price == null) {
			throw new IllegalArgumentException("Price cannot be null");
		}
		this.price = price;
		this.quantity = quantity;
		this.priceTypes = new List<>();
	}

	public FlooringItem(String name, double quantity) {
		this(FlooringItemManager.getPricing(name), quantity);
	}

	public double getQuantity() {
		return quantity;
	}

	public double getCost() {
		return price.getPrice(priceTypes) * quantity;
	}

    public String getName() {
       return price.getName();
    }




}
