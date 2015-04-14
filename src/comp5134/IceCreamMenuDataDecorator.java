package comp5134;

public abstract class IceCreamMenuDataDecorator extends IceCreamMenuData {

	protected IceCreamMenuData menuData;

	public IceCreamMenuDataDecorator(IceCreamMenuData menuData) {
		super();
		this.menuData = menuData;
	}
}
