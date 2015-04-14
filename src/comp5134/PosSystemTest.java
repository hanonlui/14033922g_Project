package comp5134;

import static org.junit.Assert.*;

import org.junit.Test;

public class PosSystemTest {

	@Test
	public void test() {
		testAllPublicMethods();
	}
	public static void testAllPublicMethods() {
		
		// Test Ice Cream
		IceCream icecream;
		icecream = new IceCreamFlavorVanilla();
		assertEquals("Error: IceCreamFlavorVanilla.getDescription Not Equal", "Vanilla", icecream.getDescription());
		assertEquals("Error: IceCreamFlavorVanilla.price Not Equal", 20.0f, icecream.price(), 0.001f);
		icecream = new IceCreamFlavorChocolate();
		assertEquals("Error: IceCreamFlavorChocolate.getDescription Not Equal", "Chocolate", icecream.getDescription());
		assertEquals("Error: IceCreamFlavorChocolate.price Not Equal", 20.0f, icecream.price(), 0.001f);
		icecream = new IceCreamFlavorCustom("a", 1.0f);
		assertEquals("Error: IceCreamFlavorChocolate.getDescription Not Equal", "a", icecream.getDescription());
		assertEquals("Error: IceCreamFlavorChocolate.getDescription Not Equal", 1.0f, icecream.price(), 0.001f);
		IceCreamDecorator dIcecream;
		dIcecream = new IceCreamDecoratorCustom(icecream, "abc", 3.0f);
		assertEquals("Error: IceCreamDecoratorCustom.getDescription Not Equal", "a with abc flavor", dIcecream.getDescription());
		assertEquals("Error: IceCreamDecoratorCustom.price Not Equal", 4.0f, dIcecream.price(), 0.001f);
		assertEquals("Error: IceCreamDecoratorCustom.getDecoratorCount Not Equal", 1, dIcecream.getDecoratorCount("abc"));
		assertEquals("Error: IceCreamDecoratorCustom.getDecoratorCount Not Equal", 0, dIcecream.getDecoratorCount("M&M"));
		dIcecream = new IceCreamDecoratorMAndM(dIcecream);
		assertEquals("Error: IceCreamDecoratorMAndM.getDescription Not Equal", "a with abc flavor with an M&M flavor", dIcecream.getDescription());
		assertEquals("Error: IceCreamDecoratorMAndM.price Not Equal", 9.0f, dIcecream.price(), 0.001f);
		assertEquals("Error: IceCreamDecoratorMAndM.getDecoratorCount Not Equal", 1, dIcecream.getDecoratorCount("abc"));
		assertEquals("Error: IceCreamDecoratorMAndM.getDecoratorCount Not Equal", 1, dIcecream.getDecoratorCount("M&M"));
		dIcecream = new IceCreamDecoratorStrawberry(dIcecream);
		assertEquals("Error: IceCreamDecoratorMAndM.getDescription Not Equal", "a with abc flavor with an M&M flavor with a Strawberry flavor", dIcecream.getDescription());
		assertEquals("Error: IceCreamDecoratorMAndM.price Not Equal", 13.0f, dIcecream.price(), 0.001f);
		assertEquals("Error: IceCreamDecoratorMAndM.getDecoratorCount Not Equal", 1, dIcecream.getDecoratorCount("abc"));
		assertEquals("Error: IceCreamDecoratorMAndM.getDecoratorCount Not Equal", 1, dIcecream.getDecoratorCount("M&M"));
		assertEquals("Error: IceCreamDecoratorMAndM.getDecoratorCount Not Equal", 1, dIcecream.getDecoratorCount("Strawberry"));
		
		// Test Menu Item Data
		IceCreamMenuData menuData = new IceCreamMenuDataBasic();
		assertEquals("Error: IceCreamMenuDataBasic.getNoOfIceCreamFlavor Not Equal", 2, menuData.getNoOfIceCreamFlavor());
		assertEquals("Error: IceCreamMenuDataBasic.getNoOfDecorator Not Equal", 2, menuData.getNoOfDecorator());
		assertEquals("Error: IceCreamMenuDataBasic.getIceCreamFlavorNameAt Not Equal", "Chocolate", menuData.getIceCreamFlavorNameAt(0));
		assertEquals("Error: IceCreamMenuDataBasic.getIceCreamFlavorNameAt Not Equal", "Vanilla", menuData.getIceCreamFlavorNameAt(1));
		assertEquals("Error: IceCreamMenuDataBasic.getIceCreamFlavorPriceAt Not Equal", 20.0f, menuData.getIceCreamFlavorPriceAt(0), 0.0001f);
		assertEquals("Error: IceCreamMenuDataBasic.getIceCreamFlavorPriceAt Not Equal", 20.0f, menuData.getIceCreamFlavorPriceAt(1), 0.0001f);
		assertEquals("Error: IceCreamMenuDataBasic.getIceCreamDecoratorNameAt Not Equal", "M&M", menuData.getIceCreamDecoratorNameAt(0));
		assertEquals("Error: IceCreamMenuDataBasic.getIceCreamDecoratorNameAt Not Equal", "Strawberry", menuData.getIceCreamDecoratorNameAt(1));
		assertEquals("Error: IceCreamMenuDataBasic.getIceCreamDecoratorPriceAt Not Equal", 5.0f, menuData.getIceCreamDecoratorPriceAt(0), 0.0001f);
		assertEquals("Error: IceCreamMenuDataBasic.getIceCreamDecoratorPriceAt Not Equal", 4.0f, menuData.getIceCreamDecoratorPriceAt(1), 0.0001f);
		assertEquals("Error: IceCreamMenuDataBasic.isDecoratorNameExist Not Equal", false, menuData.isDecoratorNameExist("Oreo"));
		menuData = new IceCreamMenuDataDecoratorFlavor(menuData, "Peanut", 21.0f);
		assertEquals("Error: IceCreamMenuDataDecoratorFlavor.getNoOfIceCreamFlavor Not Equal", 3, menuData.getNoOfIceCreamFlavor());
		assertEquals("Error: IceCreamMenuDataDecoratorFlavor.getNoOfDecorator Not Equal", 2, menuData.getNoOfDecorator());
		assertEquals("Error: IceCreamMenuDataDecoratorFlavor.getIceCreamFlavorNameAt Not Equal", "Chocolate", menuData.getIceCreamFlavorNameAt(0));
		assertEquals("Error: IceCreamMenuDataDecoratorFlavor.getIceCreamFlavorNameAt Not Equal", "Vanilla", menuData.getIceCreamFlavorNameAt(1));
		assertEquals("Error: IceCreamMenuDataDecoratorFlavor.getIceCreamFlavorNameAt Not Equal", "Peanut", menuData.getIceCreamFlavorNameAt(2));
		assertEquals("Error: IceCreamMenuDataDecoratorFlavor.getIceCreamFlavorPriceAt Not Equal", 20.0f, menuData.getIceCreamFlavorPriceAt(0), 0.0001f);
		assertEquals("Error: IceCreamMenuDataDecoratorFlavor.getIceCreamFlavorPriceAt Not Equal", 20.0f, menuData.getIceCreamFlavorPriceAt(1), 0.0001f);
		assertEquals("Error: IceCreamMenuDataDecoratorFlavor.getIceCreamFlavorPriceAt Not Equal", 21.0f, menuData.getIceCreamFlavorPriceAt(2), 0.0001f);
		assertEquals("Error: IceCreamMenuDataDecoratorFlavor.getIceCreamDecoratorNameAt Not Equal", "M&M", menuData.getIceCreamDecoratorNameAt(0));
		assertEquals("Error: IceCreamMenuDataDecoratorFlavor.getIceCreamDecoratorNameAt Not Equal", "Strawberry", menuData.getIceCreamDecoratorNameAt(1));
		assertEquals("Error: IceCreamMenuDataDecoratorFlavor.getIceCreamDecoratorPriceAt Not Equal", 5.0f, menuData.getIceCreamDecoratorPriceAt(0), 0.0001f);
		assertEquals("Error: IceCreamMenuDataDecoratorFlavor.getIceCreamDecoratorPriceAt Not Equal", 4.0f, menuData.getIceCreamDecoratorPriceAt(1), 0.0001f);
		assertEquals("Error: IceCreamMenuDataDecoratorFlavor.isDecoratorNameExist Not Equal", false, menuData.isDecoratorNameExist("Oreo"));
		menuData = new IceCreamMenuDataDecoratorDecorator(menuData, "Oreo", 3.3f);
		assertEquals("Error: IceCreamMenuDataDecoratorDecorator.getNoOfIceCreamFlavor Not Equal", 3, menuData.getNoOfIceCreamFlavor());
		assertEquals("Error: IceCreamMenuDataDecoratorDecorator.getNoOfDecorator Not Equal", 3, menuData.getNoOfDecorator());
		assertEquals("Error: IceCreamMenuDataDecoratorDecorator.getIceCreamFlavorNameAt Not Equal", "Chocolate", menuData.getIceCreamFlavorNameAt(0));
		assertEquals("Error: IceCreamMenuDataDecoratorDecorator.getIceCreamFlavorNameAt Not Equal", "Vanilla", menuData.getIceCreamFlavorNameAt(1));
		assertEquals("Error: IceCreamMenuDataDecoratorDecorator.getIceCreamFlavorNameAt Not Equal", "Peanut", menuData.getIceCreamFlavorNameAt(2));
		assertEquals("Error: IceCreamMenuDataDecoratorDecorator.getIceCreamFlavorPriceAt Not Equal", 20.0f, menuData.getIceCreamFlavorPriceAt(0), 0.0001f);
		assertEquals("Error: IceCreamMenuDataDecoratorDecorator.getIceCreamFlavorPriceAt Not Equal", 20.0f, menuData.getIceCreamFlavorPriceAt(1), 0.0001f);
		assertEquals("Error: IceCreamMenuDataDecoratorDecorator.getIceCreamFlavorPriceAt Not Equal", 21.0f, menuData.getIceCreamFlavorPriceAt(2), 0.0001f);
		assertEquals("Error: IceCreamMenuDataDecoratorDecorator.getIceCreamDecoratorNameAt Not Equal", "M&M", menuData.getIceCreamDecoratorNameAt(0));
		assertEquals("Error: IceCreamMenuDataDecoratorDecorator.getIceCreamDecoratorNameAt Not Equal", "Strawberry", menuData.getIceCreamDecoratorNameAt(1));
		assertEquals("Error: IceCreamMenuDataDecoratorDecorator.getIceCreamDecoratorNameAt Not Equal", "Oreo", menuData.getIceCreamDecoratorNameAt(2));
		assertEquals("Error: IceCreamMenuDataDecoratorDecorator.getIceCreamDecoratorPriceAt Not Equal", 5.0f, menuData.getIceCreamDecoratorPriceAt(0), 0.0001f);
		assertEquals("Error: IceCreamMenuDataDecoratorDecorator.getIceCreamDecoratorPriceAt Not Equal", 4.0f, menuData.getIceCreamDecoratorPriceAt(1), 0.0001f);
		assertEquals("Error: IceCreamMenuDataDecoratorDecorator.getIceCreamDecoratorPriceAt Not Equal", 3.3f, menuData.getIceCreamDecoratorPriceAt(2), 0.0001f);
		assertEquals("Error: IceCreamMenuDataDecoratorDecorator.isDecoratorNameExist Not Equal", true, menuData.isDecoratorNameExist("Oreo"));
	}
}
