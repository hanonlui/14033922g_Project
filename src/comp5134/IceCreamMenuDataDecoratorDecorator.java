package comp5134;

public final class IceCreamMenuDataDecoratorDecorator extends IceCreamMenuDataDecorator {

	private String decoratorName;
	private double decoratorPrice;
	
	public IceCreamMenuDataDecoratorDecorator(IceCreamMenuData menuData, String decorator, double price) {
		super(menuData);
		this.decoratorName = decorator;
		this.decoratorPrice = price;
	}
	
	public int getNoOfIceCreamFlavor(){
		return this.menuData.getNoOfIceCreamFlavor();
	}
	public int getNoOfDecorator(){
		return this.menuData.getNoOfDecorator() + 1;
	}
	public String getIceCreamFlavorNameAt(int index){
		return this.menuData.getIceCreamFlavorNameAt(index);
	}
	public double getIceCreamFlavorPriceAt(int index){
		return this.menuData.getIceCreamFlavorPriceAt(index);
	}
	public String getIceCreamDecoratorNameAt(int index){
		if( index==(this.menuData.getNoOfDecorator()) ) {
			return this.decoratorName;
		}
		return this.menuData.getIceCreamDecoratorNameAt(index);
	}
	public double getIceCreamDecoratorPriceAt(int index){
		if( index==(this.menuData.getNoOfDecorator()) ) {
			return this.decoratorPrice;
		}
		return this.menuData.getIceCreamDecoratorPriceAt(index);
	}
	public boolean isDecoratorNameExist(String name){
		if( decoratorName.equals(name) ){
			return true;
		}
		return this.menuData.isDecoratorNameExist(name);
	}
}
