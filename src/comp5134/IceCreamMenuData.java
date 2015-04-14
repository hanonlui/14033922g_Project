package comp5134;

public abstract class IceCreamMenuData {

	public abstract int getNoOfIceCreamFlavor();
	public abstract int getNoOfDecorator();
	public abstract String getIceCreamFlavorNameAt(int index);
	public abstract double getIceCreamFlavorPriceAt(int index);
	public abstract String getIceCreamDecoratorNameAt(int index);
	public abstract double getIceCreamDecoratorPriceAt(int index);
	public abstract boolean isDecoratorNameExist(String name);
}
