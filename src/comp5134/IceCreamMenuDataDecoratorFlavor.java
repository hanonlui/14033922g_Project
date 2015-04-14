package comp5134;

public final class IceCreamMenuDataDecoratorFlavor extends IceCreamMenuDataDecorator {

	private String flavorName;
	private double flavorPrice;
	
	public IceCreamMenuDataDecoratorFlavor(IceCreamMenuData menuData, String flavor, double price) {
		super(menuData);
		this.flavorName = flavor;
		this.flavorPrice = price;
	}
	
	public int getNoOfIceCreamFlavor(){
		return this.menuData.getNoOfIceCreamFlavor() + 1;
	}
	public int getNoOfDecorator(){
		return this.menuData.getNoOfDecorator();
	}
	public String getIceCreamFlavorNameAt(int index){
		if( index==(this.menuData.getNoOfIceCreamFlavor()) ) {
			return this.flavorName;
		}
		return this.menuData.getIceCreamFlavorNameAt(index);
	}
	public double getIceCreamFlavorPriceAt(int index){
		if( index==(this.menuData.getNoOfIceCreamFlavor()) ) {
			return this.flavorPrice;
		}
		return this.menuData.getIceCreamFlavorPriceAt(index);
	}
	public String getIceCreamDecoratorNameAt(int index){
		return this.menuData.getIceCreamDecoratorNameAt(index);
	}
	public double getIceCreamDecoratorPriceAt(int index){
		return this.menuData.getIceCreamDecoratorPriceAt(index);
	}
	public boolean isDecoratorNameExist(String name){
		return this.menuData.isDecoratorNameExist(name);
	}
}
