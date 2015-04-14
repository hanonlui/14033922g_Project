package comp5134;

public abstract class IceCreamDecorator extends IceCream {

	protected IceCream decoratedIceCream;

	// Interface to declare for Decorator implementation
	public abstract String getDescription();
	public abstract double price();
	public abstract String getDecoratorName();
	
	public IceCreamDecorator(IceCream iceCream) {
		super();
		this.decoratedIceCream = iceCream;
	}
	// Decorator-use only function
	public int getDecoratorCount(String name){
		int decoratorCount = 0;
		// if its parent is flavor, skip
		if(decoratedIceCream instanceof IceCreamDecorator) {
			decoratorCount += ((IceCreamDecorator) decoratedIceCream).getDecoratorCount(name);
		}
		// if this is decorator and name matched, decorator increment by 1.
		if(((String)this.getDecoratorName()).equals(name)) {
			decoratorCount++;
		}
		return decoratorCount;
	}
}
