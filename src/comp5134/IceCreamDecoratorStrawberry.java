package comp5134;

public final class IceCreamDecoratorStrawberry extends IceCreamDecorator {
	
	public IceCreamDecoratorStrawberry(IceCream icecream) {
		super(icecream);
	}
	public String getDescription(){
		return this.decoratedIceCream.getDescription() + " with a Strawberry flavor";
	}
	public double price(){
		return this.decoratedIceCream.price() + 4.0f;
	}
	public String getDecoratorName(){
		return "Strawberry";
	}
}
