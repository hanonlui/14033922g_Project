package comp5134;

public final class IceCreamDecoratorCustom extends IceCreamDecorator{
	
	private String name;
	private double price = 0.0f;
	
	public IceCreamDecoratorCustom(IceCream icecream, String name, double price) {
		super(icecream);
		this.decoratedIceCream = icecream;
		this.name = name;
		this.price = price;
	}
	public String getDescription(){
		return this.decoratedIceCream.getDescription() + " with " + this.name + " flavor";
	}
	public double price(){
		return this.decoratedIceCream.price() + this.price;
	}
	public String getDecoratorName(){
		return this.name;
	}
}
