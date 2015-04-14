package comp5134;

public class IceCreamMenuDataBasic extends IceCreamMenuData{
	
	public int getNoOfIceCreamFlavor() {
		return 2;
	}
	public int getNoOfDecorator() {
		return 2;
	}
	public String getIceCreamFlavorNameAt(int index){
		switch(index) {
			case 0:	return "Chocolate";
			case 1:	return "Vanilla";
			default:	return "";
		}
	}
	public double getIceCreamFlavorPriceAt(int index){
		switch(index) {
			case 0:	return 20.0f;
			case 1:	return 20.0f;
			default:	return 0.0f;
		}
	}
	public String getIceCreamDecoratorNameAt(int index){
		switch(index) {
			case 0:	return "M&M";
			case 1:	return "Strawberry";
			default:	return "";
		}
	}
	public double getIceCreamDecoratorPriceAt(int index){
		switch(index) {
			case 0:	return 5.0f;
			case 1:	return 4.0f;
			default:	return 0.0f;
		}
	}
	public boolean isDecoratorNameExist(String name){
		return (name.equals("M&M") || name.equals("Strawberry"));
	}
}
