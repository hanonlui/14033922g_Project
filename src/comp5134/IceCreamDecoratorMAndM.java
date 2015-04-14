package comp5134;

public final class IceCreamDecoratorMAndM extends IceCreamDecorator{
	
	public IceCreamDecoratorMAndM(IceCream icecream) {
		super(icecream);
	}
	public String getDescription(){
		return this.decoratedIceCream.getDescription() + " with an M&M flavor";
	}
	public double price(){
		return this.decoratedIceCream.price() + 5.0f;
	}
	public String getDecoratorName(){
		return "M&M";
	}
}
