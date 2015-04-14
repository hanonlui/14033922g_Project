package comp5134;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class PosGUI extends JFrame implements ActionListener{
	public static final long serialVersionUID = 1999;
  
	Container c;

	IceCreamMenuData viewUseMenuData;	// cached Data for display use

	// GUI use
	JFrame frame = new JFrame("Hi!");
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JPanel flavorAndDecoratorPanel = new JPanel();
	JButton btnNewIceCream;
	JButton[] btnFlavorList;
	JButton[] btnDecoratorList;
	JButton btnSysAdmin;	
	JTextField txtTotal;
	JTextField[] txtPurchasedDecorator;
	
	DecimalFormat myFormatter = new DecimalFormat("#0.##");
	
	// Temporary variable storage
	IceCream curIceCream;	// homework

	public PosGUI(IceCreamMenuData pMenuData) {
		super("Welcome to COMP5134 Ice-cream shop");
		
		// Integrating Data to Controller
		viewUseMenuData = pMenuData;
		
		// panel initialization
		c = getContentPane();
		this.setLayout(new BorderLayout(5,0));

		// button initialization
		btnNewIceCream = new JButton("New Ice-Cream");
		btnSysAdmin = new JButton("System Administrator");
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		
		btnNewIceCream.addActionListener(this);
		p1.add(btnNewIceCream);

		// set the layout of Flavor, decorator and purchased-decorator
		this.resetComponent();

		// flavor text setting
		for (int i = 0; i<btnFlavorList.length; i++){
			btnFlavorList[i].setText(this.viewUseMenuData.getIceCreamFlavorNameAt(i)+"    $"+this.viewUseMenuData.getIceCreamFlavorPriceAt(i));
			btnFlavorList[i].setEnabled(true);
		}

		// decorator text setting
		for (int i = 0; i<btnDecoratorList.length; i++){
			btnDecoratorList[i].setText(this.viewUseMenuData.getIceCreamDecoratorNameAt(i)+"    $"+this.viewUseMenuData.getIceCreamDecoratorPriceAt(i));
			btnDecoratorList[i].setEnabled(true);
		}

		// purchased-decorator text setting
		this.updateAllPurchasedList();
		
		// total text setting
		this.updateTotal();

		// System Administrator button initialization
		p4.setLayout(new GridLayout(1,2,1,1));
		btnSysAdmin.addActionListener(this);
		p4.add(btnSysAdmin);
		p4.add(txtTotal);
		txtTotal.setFont(txtTotal.getFont().deriveFont(txtTotal.getFont().getSize()* 1.8f));
		txtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// Overall layout format control
		flavorAndDecoratorPanel.setLayout(new GridLayout(1,2,1,1));
		flavorAndDecoratorPanel.add(p2);
		flavorAndDecoratorPanel.add(p3);
		
		c.add(p1, BorderLayout.NORTH);
		c.add(flavorAndDecoratorPanel, BorderLayout.CENTER);
		c.add(p5, BorderLayout.EAST);
		c.add(p4, BorderLayout.PAGE_END);
		
		this.setSize(500, 400);
		this.setLocation(500, 300);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent event){
		if(event.getSource()==btnNewIceCream){
			this.onClickedNewIceCream();
			return;
		}else if(event.getSource()==btnSysAdmin){
			this.onClickedSysAdmin();
			return;
		}else{
			for (int i = 0; i<this.viewUseMenuData.getNoOfIceCreamFlavor(); i++){
				if(event.getSource()==btnFlavorList[i]) {
					this.onClickedFlavor(i);
					return;
				}
			}
			for (int i = 0; i<this.viewUseMenuData.getNoOfDecorator(); i++){
				if(event.getSource()==btnDecoratorList[i]) {
					this.onClickedDecorator(i);
					return;
				}
			}
		}
	}

	private void resetComponent(){
		int noOfIceCreamFlavor = this.viewUseMenuData.getNoOfIceCreamFlavor();
		int noOfDecorator = this.viewUseMenuData.getNoOfDecorator();
		
		p2.removeAll();
		p3.removeAll();
		p5.removeAll();

		// Flavor list reset
		btnFlavorList = new JButton[noOfIceCreamFlavor];
		p2.setLayout(new GridLayout(noOfIceCreamFlavor+1,1,1,1));
		JTextField columnNameIceCream = new JTextField("ICE-CREAM Flavor");
		columnNameIceCream.setEditable(false);
		p2.add(columnNameIceCream);
		for (int i = 0; i<btnFlavorList.length; i++){
			btnFlavorList[i] = new JButton();
			btnFlavorList[i].addActionListener(this);
			p2.add(btnFlavorList[i]);
			btnFlavorList[i].setEnabled(false);
			btnFlavorList[i].setHorizontalAlignment(SwingConstants.LEFT);
		}
		
		// Decorator list reset
		btnDecoratorList = new JButton[noOfDecorator];
		p3.setLayout(new GridLayout(noOfDecorator+1,1,1,1));
		JTextField columnNameDecorator = new JTextField("Decorator");
		columnNameDecorator.setEditable(false);
		p3.add(columnNameDecorator);
		for (int i = 0; i<btnDecoratorList.length; i++){
			btnDecoratorList[i] = new JButton();
			btnDecoratorList[i].addActionListener(this);
			p3.add(btnDecoratorList[i]);
			btnDecoratorList[i].setEnabled(false);
			btnDecoratorList[i].setHorizontalAlignment(SwingConstants.LEFT);
		}

		// Purchased Decorator list reset
		txtPurchasedDecorator = new JTextField[noOfDecorator];
		p5.setLayout(new GridLayout(noOfDecorator+1,1,1,1));
		JTextField columnNameDecoratorAmount = new JTextField("Amount");
		columnNameDecoratorAmount.setEditable(false);
		p5.add(columnNameDecoratorAmount);
		for (int i = 0; i<txtPurchasedDecorator.length; i++){
			txtPurchasedDecorator[i] = new JTextField();
			txtPurchasedDecorator[i].setEditable(false);
			p5.add(txtPurchasedDecorator[i]);
		}

		// initialize a new ice-cream with empty UI
		this.createNewIceCream();
	}
	
	private void createNewIceCream() {
		// Ice-cream Data reset
		this.curIceCream = null;

		// UI reset
		for (int i = 0; i<btnFlavorList.length; i++){
			btnFlavorList[i].setText(this.viewUseMenuData.getIceCreamFlavorNameAt(i)+"    $"+this.viewUseMenuData.getIceCreamFlavorPriceAt(i));
			btnFlavorList[i].setEnabled(true);
		}
		for (int i = 0; i<btnDecoratorList.length; i++){
			btnDecoratorList[i].setText(this.viewUseMenuData.getIceCreamDecoratorNameAt(i)+"    $"+this.viewUseMenuData.getIceCreamDecoratorPriceAt(i));
			btnDecoratorList[i].setEnabled(true);
		}
		this.updateAllPurchasedList();
		this.updateTotal();
	}
	
	private void onClickedNewIceCream(){
		// initialize a new ice-cream with empty UI
		this.createNewIceCream();
	}
	
	private void onClickedSysAdmin(){
		// Pop up a dialog
		Object[] possibilities = {"Add New Flavor", "Add New Decorator"};
		Icon icon = UIManager.getIcon("OptionPane.questionIcon");
		String s = (String)JOptionPane.showInputDialog(
		                    frame,
		                    "Please select what you want to add",
		                    "Add Dialog",
		                    JOptionPane.PLAIN_MESSAGE,
		                    icon,
		                    possibilities,
		                    possibilities[0]);

		//If a string was returned, say so.
		if ((s != null) && (s.length() > 0)) {
			if( s=="Add New Flavor" ){
				String flavorNameReply = (String)JOptionPane.showInputDialog(
					                    frame,
					                    "Please input the flavor name",
					                    "Add Dialog",
					                    JOptionPane.PLAIN_MESSAGE,
					                    icon,
					                    null,
					                    null);
				if((flavorNameReply != null) && (flavorNameReply.length() > 0)) {
					String flavorPriceReply = (String)JOptionPane.showInputDialog(
		                    frame,
		                    "Please input the price of "+flavorNameReply,
		                    "Add Dialog",
		                    JOptionPane.PLAIN_MESSAGE,
		                    icon,
		                    null,
		                    null);
					if((flavorPriceReply != null) && (flavorPriceReply.length() > 0)) {
						double priceValue = 0.0f;
						try{
							priceValue = Double.parseDouble(flavorPriceReply);
						}catch(Exception e){
						}
						if( priceValue <= 0.0f  || priceValue>1000.0f){
							JOptionPane.showMessageDialog(frame,"Invalid Price Value","Warning",JOptionPane.WARNING_MESSAGE);
						}else{
							this.viewUseMenuData = new IceCreamMenuDataDecoratorFlavor(this.viewUseMenuData, flavorNameReply, priceValue);
							this.resetComponent();
						}
					}
				}else{
					JOptionPane.showMessageDialog(frame,"Missing Name","Warning",JOptionPane.WARNING_MESSAGE);
				}
			}else if( s=="Add New Decorator" ){
				String decoratorNameReply = (String)JOptionPane.showInputDialog(
	                    frame,
	                    "Please input the decorate name",
	                    "Add Dialog",
	                    JOptionPane.PLAIN_MESSAGE,
	                    icon,
	                    null,
	                    null);
				if((decoratorNameReply != null) && (decoratorNameReply.length() > 0)) {
					String decoratorPriceReply = (String)JOptionPane.showInputDialog(
				            frame,
				            "Please input the price of "+decoratorNameReply,
				            "Add Dialog",
				            JOptionPane.PLAIN_MESSAGE,
				            icon,
				            null,
				            null);
					if((decoratorPriceReply != null) && (decoratorPriceReply.length() > 0)) {
						double priceValue = 0.0f;
						try{
							priceValue = Double.parseDouble(decoratorPriceReply);
						}catch(Exception e){
						}
						if( priceValue <= 0.0f || priceValue>1000.0f){
							JOptionPane.showMessageDialog(frame,"Invalid Price Value","Warning",JOptionPane.WARNING_MESSAGE);
						}else{
							if( this.viewUseMenuData.isDecoratorNameExist(decoratorNameReply) ) {
								JOptionPane.showMessageDialog(frame,"Decorator exists.  Insert failed.","Warning",JOptionPane.WARNING_MESSAGE);
							}else{
								this.viewUseMenuData = new IceCreamMenuDataDecoratorDecorator(this.viewUseMenuData, decoratorNameReply, priceValue);
								this.resetComponent();
							}
						}
					}
				}else{
					JOptionPane.showMessageDialog(frame,"Missing Name","Warning",JOptionPane.WARNING_MESSAGE);
				}
			}
		    return;
		}
	}
	
	private void onClickedFlavor(int btnIndex){
		// Ice-cream composition
		// Semi-hardcode to demonstrate of "Decorator" for homework purpose
		switch(btnIndex) {
			case 0:
				this.curIceCream = new IceCreamFlavorChocolate();	// Simple, undecorated Ice-cream 
				break;
			case 1:
				this.curIceCream = new IceCreamFlavorVanilla();	// Simple, undecorated Ice-cream
				break;
			default:
				if( btnIndex >= this.viewUseMenuData.getNoOfIceCreamFlavor() ) {
					// invalid command, no action
					break;
				}
				// customized icecream flavor
				String customName = this.viewUseMenuData.getIceCreamFlavorNameAt(btnIndex);
				double customPrice = this.viewUseMenuData.getIceCreamFlavorPriceAt(btnIndex);
				this.curIceCream = new IceCreamFlavorCustom(customName, customPrice);	// Simple, undecorated Ice-cream
				break;
		}

		// UI update
		for (int j = 0; j<btnFlavorList.length; j++){
			btnFlavorList[j].setEnabled(true);
			btnFlavorList[j].setText(this.viewUseMenuData.getIceCreamFlavorNameAt(j)+"    $"+this.viewUseMenuData.getIceCreamFlavorPriceAt(j));
		}
		btnFlavorList[btnIndex].setEnabled(false);
		btnFlavorList[btnIndex].setText(this.viewUseMenuData.getIceCreamFlavorNameAt(btnIndex)+" "+this.viewUseMenuData.getIceCreamFlavorPriceAt(btnIndex)+"    Pick!");

		// UI update
		this.updateAllPurchasedList();
		this.updateTotal();
	}
	
	private void onClickedDecorator(int btnIndex){
		// Input validation
		if( this.curIceCream==null ){
			JOptionPane.showMessageDialog(frame,"Choose an Ice-cream First!!","Hi~",JOptionPane.WARNING_MESSAGE);
			return;
		}

		// Ice-cream composition
		// Semi-hardcode to demonstrate of "Decorator" for homework purpose
		switch(btnIndex) {
			case 0:
				this.curIceCream = new IceCreamDecoratorMAndM(this.curIceCream);	// Decorated Ice-cream 
				break;
			case 1:
				this.curIceCream = new IceCreamDecoratorStrawberry(this.curIceCream);	// Decorated Ice-cream
				break;
			default:
				if( btnIndex >= this.viewUseMenuData.getNoOfDecorator() ) {
					// invalid command, no action
					break;
				}
				// customized icecream flavor
				String customName = this.viewUseMenuData.getIceCreamDecoratorNameAt(btnIndex);
				double customPrice = this.viewUseMenuData.getIceCreamDecoratorPriceAt(btnIndex);
				this.curIceCream = new IceCreamDecoratorCustom(this.curIceCream, customName, customPrice);	// Decorated Ice-cream
				break;
		}

		// UI update
		this.updateAllPurchasedList();
		this.updateTotal();
	}
	
	private void updateAllPurchasedList(){
		for( int i = 0; i < txtPurchasedDecorator.length; ++i ){
			int purchasedNoOfDecorator = 0;
			if( this.curIceCream instanceof IceCreamDecorator ) {
				// Same name with different index works because of object comparison.
				purchasedNoOfDecorator = ((IceCreamDecorator)this.curIceCream).getDecoratorCount(this.viewUseMenuData.getIceCreamDecoratorNameAt(i));
			}
			txtPurchasedDecorator[i].setText("    "+Integer.toString(purchasedNoOfDecorator));
		}
	}
	
	private void updateTotal(){
		double totalPrice = 0.0f;
		if( this.curIceCream!=null ) {
			totalPrice = this.curIceCream.price();
		}
		this.txtTotal.setText("Total: " + this.myFormatter.format(totalPrice));
	}
}
