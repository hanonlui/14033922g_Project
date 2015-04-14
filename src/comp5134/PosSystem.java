package comp5134;

public class PosSystem {

	public static void main(String[] args) {
		IceCreamMenuData menuData = new IceCreamMenuDataBasic();
		new PosGUI(menuData);
	}
}
