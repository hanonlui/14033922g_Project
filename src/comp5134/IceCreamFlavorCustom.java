package comp5134;

public final class IceCreamFlavorCustom extends IceCream {

	private String name;
	private double price;
	
	public IceCreamFlavorCustom(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}
	public String getDescription(){
		return this.name;
	}
	public double price(){
		return price;
	}
}
