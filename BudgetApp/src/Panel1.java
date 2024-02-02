import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class Panel1 extends JPanel {
	public static ArrayList<User> userList = new ArrayList<>();
	public Map<String, JTextField> textLabels = new HashMap<>();
	public Map<String, JButton> buttonList = new HashMap<>();
	public Map<String, JLabel> labelList = new HashMap<>();


	public ArrayList<Amount> houseOutcome = new ArrayList<>();

	public static double householdOutcomePerWeek = 0;
	User currentUser = userList.get(0);
	JButton currentButtonPress = null;
	String currentPage = " ";
	
	Panel1() {
		this.setPreferredSize(new Dimension(1000, 500));
		this.setFocusable(true);
		//homeScreen();
		defaultPage();

	}


    public BufferedImage loadImage(String path) {
        BufferedImage image = null;
        try (InputStream is = getClass().getResourceAsStream(path)) {
            if (is == null) {
                throw new IOException("Resource not found: " + path);
            }
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.drawImage(loadImage("RRRPixel.png"), 300, 50, 400, 400, null);
		
	}

	/**
	 * 
	 * @param text
	 * @param size
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param backColor
	 * @param textColor
	 * @param e
	 */
	public void makeButton(String text, int size, int x, int y, int width, int height, ActionListener e) {
		JButton button = new JButton();
		button.setText(text);
		button.setBounds(x, y, width, height);
		button.setFont(new Font("Times New Roman", Font.BOLD, size));
		button.setBackground(Color.white);
		button.setForeground(Color.black);
		button.addActionListener(e);
		button.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		button.setFocusPainted(false);
		buttonList.put(text, button);
		this.add(button);
	}
	
	public void makeLabel(String text, int size, int x, int y, int width, int height, Color backColor, Color textColor,boolean backgroundVisible){
		JLabel label = new JLabel();
		label.setText(text);
		label.setBounds(x, y, width, height);
		label.setFont(new Font("Times New Roman", Font.BOLD, size));
		label.setBackground(backColor);
		label.setForeground(textColor);
		label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		label.setOpaque(backgroundVisible);
		labelList.put(text, label);
		this.add(label);
	}
	
	public void makeTextField(String name, int size, int x, int y, int width, int height, Color backColor, Color textColor){
		JTextField textField = new JTextField();
		textField.setName(name);
		textField.setBounds(x, y, width, height);
		textField.setFont(new Font("Times New Roman", Font.BOLD, size));
		textField.setBackground(backColor);
		textField.setForeground(textColor);
		this.add(textField);
		textLabels.put(name, textField);
	}

	public void clearScreen(LayoutManager man) {
		this.removeAll();
		this.repaint();
		this.setLayout(man);
		calcHouseOut();
		int defUser = 0;
		boolean isDefUser = false;
		for(User user : userList){
			if(user.firstName == "Default"){
				defUser = userList.indexOf(user);
				isDefUser = true;
			}
		}
		
		if(userList.size() < 1){
			currentUser = new User("Default", "User");
		} else if(isDefUser && userList.size() > 1){
			//gets rid of default user if there is another profile
			userList.remove(userList.remove(defUser));
		}
		if(!userList.contains(currentUser)){
			currentUser = userList.get(0);
		}
		
	}

	public void homeScreen() {
		clearScreen(null);
		makeButton("Get Started", 20, 0, 0, 200, 150, e -> defaultPage());
	}

	public void defaultPage(){
		currentPage = "default";

		int sepAmo = 10;
		int buttonSize = 85;
		clearScreen(null);
		incomePanel();
		outcomePanel();
		housePanel();
		currentUserPanel();
		currentUserIncomeTotal();
		currentUserOutcomeTotal();

		makeButton("New User", 15, 890, 10, buttonSize, buttonSize, e -> newUser());
		makeButton("Pick User", 15, 890, 10 + buttonSize + sepAmo, buttonSize, buttonSize, e -> chooseUser());
		makeButton("Delete User", 15, 890,  10 + buttonSize * 2 + sepAmo * 2, buttonSize, buttonSize, e -> deleteUser());
		makeButton("View Stats", 15, 890, 10+ buttonSize * 3 + sepAmo * 3, buttonSize, buttonSize, e -> defaultPage());
		
		makeButton("Income", 15, 795, 10 + buttonSize + sepAmo, buttonSize, buttonSize, e -> addIncome());
		makeButton("Outcome", 15, 795,  10 + buttonSize * 2 + sepAmo * 2, buttonSize, buttonSize, e -> addOutcome());
		makeButton("House", 15, 795, 10+ buttonSize * 3 + sepAmo * 3, buttonSize, buttonSize, e -> addHouse());
	}

	

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public void newUser(){
		currentPage = "";
		clearScreen(null);

		makeLabel("First Name", 15, 10,400, 100, 50, Color.white, Color.black, false);
		makeLabel("Last Name", 15, 120,400, 100, 50, Color.white, Color.black, false);

		makeTextField("Enter First Name", 20, 10, 440, 100, 50, Color.white, Color.black);
		makeTextField("Enter Last Name", 20, 120, 440, 100, 50, Color.white, Color.black);

		makeButton("Back", 20, 890, 450, 100, 40, e -> defaultPage());
		makeButton("Enter", 20, 230, 440, 100, 50, e -> createUser());
	}

	public void createUser(){
		currentUser = new User(textLabels.get("Enter First Name").getText(), textLabels.get("Enter Last Name").getText());
		defaultPage();
	}

	public void chooseUser(){
		currentPage = "";
		clearScreen(null);

		ButtonGroup buttonGroup = new ButtonGroup();
		makeButton("Back", 20, 890, 450, 100, 40, e -> defaultPage());

		int userNum = 1;
		for(User user: userList){
			JRadioButton button = new JRadioButton();
			button.setText(user.firstName + " " + user.lastName);
			buttonGroup.add(button);
			button.setFont(new Font("Times New Roman", Font.BOLD, 30));
			button.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			button.setBackground(Color.white);
			button.setForeground(Color.black);
			button.setFocusPainted(false);
			button.setBounds(10, 20 + (userNum * 60), 200, 30);
			button.addActionListener(e -> pickUser(user));
			this.add(button);
			userNum++;
		}		
		repaint();
	}

	private void pickUser(User user){
		currentUser = user;
		defaultPage();
	}

	public void deleteUser(){
		currentPage = "";

		clearScreen(null);

		makeButton("Back", 20, 890, 450, 100, 40, e -> defaultPage());

		int userNum = 1;
		for(User user: userList){
			JRadioButton button = new JRadioButton();
			button.setText(user.firstName + " " + user.lastName);
			button.setBounds(10, 20 + (userNum * 60), 200, 30);
			button.setFont(new Font("Times New Roman", Font.BOLD, 30));
			button.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			button.setBackground(Color.white);
			button.setForeground(Color.black);
			button.setFocusPainted(false);
			button.addActionListener(e -> popUpScreen(user));
			this.add(button);
			userNum++;
		}		
		repaint();
	}
	public void popUpScreen(User user){
		clearScreen(null);
		makeLabel("Are You Sure?", 15, 400, 200, 200, 100, Color.white, Color.black, true);
		makeButton("YES", 30, 400, 300, 90, 50, e -> removeUser(user));
		makeButton("NO", 30, 510, 300, 90, 50, e-> deleteUser());
	}
	public void removeUser(User user){
		userList.remove(user);
		defaultPage();
	}

	public void addIncome(){
		currentPage = "";

		clearScreen(null);
		makeLabel("Name", 15, 10,400, 100, 50, Color.white, Color.black, false);
		makeLabel("Amount", 15, 120,400, 100, 50, Color.white, Color.black, false);

		makeTextField("Name Of Income", 20, 10, 440, 100, 50, Color.white, Color.black);
		makeTextField("Amount Of Income", 20, 120, 440, 100, 50, Color.white, Color.black);

		makeButton("Back", 20, 890, 450, 100, 40, e -> defaultPage());
		makeButton("Enter", 20, 230, 440, 100, 50, e -> addInO());

		int userNum = 1;
		int buttonX = 10;
		for(Amount amount: currentUser.income){
			JRadioButton button = new JRadioButton();
			button.setText(amount.name + " $" + amount.amount);
			
			if(userNum % 11 == 1 && userNum > 2)
			{
				buttonX += 201;
				userNum -= 11;
			}
			button.setBounds(buttonX, 10 + (userNum * 31), 200, 30);
			button.setFont(new Font("Times New Roman", Font.BOLD, 30));
			button.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			button.setBackground(Color.white);
			button.setForeground(Color.black);
			button.setFocusPainted(false);			
			button.addActionListener(e -> currentUser.removeIncome(amount));
			this.add(button);
			userNum++;
		}
		
		repaint();
	}

	public void addInO(){
		String temp = "";
		int temp1 = 0;
		try{
			temp = textLabels.get("Name Of Income").getText();
		 	temp1 = Integer.parseInt(textLabels.get("Amount Of Income").getText());
			if(temp.length() > 0 ){
				if(temp1> 0){
				currentUser.newIncome(temp, temp1, "");
				}
			}
			defaultPage();
		}
		catch (Exception e){
			makeLabel("Please Enter Valid Information", 10, 10, 10, 10, 10, Color.black, Color.white, true);
		}
		
	}

	public void addOutcome(){
		currentPage = "";

		clearScreen(null);

		makeLabel("Name", 15, 10,400, 100, 50, Color.white, Color.black, false);
		makeLabel("Amount", 15, 120,400, 100, 50, Color.white, Color.black, false);

		makeTextField("Name Of Expense", 20, 10, 440, 100, 50, Color.white, Color.black);
		makeTextField("Amount Of Expense", 20, 120, 440, 100, 50, Color.white, Color.black);

		makeButton("Back", 20, 890, 450, 100, 40, e -> defaultPage());
		makeButton("Enter", 20, 230, 440, 100, 50, e ->  addOutO());
			
		int userNum = 1;
		int buttonX = 10;
		for(Amount amount: currentUser.outcome){
			JRadioButton button = new JRadioButton();
			button.setText(amount.name + " $" + amount.amount);
			if(userNum % 11 == 1 && userNum > 2)
			{
				buttonX += 201;
				userNum -= 11;
			}
			button.setBounds(buttonX, 10 + (userNum * 31), 200, 30);
			button.setFont(new Font("Times New Roman", Font.BOLD, 30));
			button.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			button.setBackground(Color.white);
			button.setForeground(Color.black);
			button.setFocusPainted(false);
			button.addActionListener(e -> currentUser.removeOutcome(amount));
			this.add(button);
			userNum++;
		}
		
		repaint();
	}

	public void addOutO(){
		String temp = "";
		int temp1 = 0;
		try{
			temp = textLabels.get("Name Of Expense").getText();
		 	temp1 = Integer.parseInt(textLabels.get("Amount Of Expense").getText());
			 if(temp.length() > 0 ){
				if(temp1> 0){
					currentUser.addOutcome(temp, temp1, "");
				}
			}
			defaultPage();
		}
		catch (Exception e){
			makeLabel("Please Enter Valid Information", 10, 10, 10, 10, 10, Color.black, Color.white, true);
		}
		
	}

	public void calcHouseOut(){
		double tmpAmount = 0;
		for(Amount houseOut : houseOutcome){
			tmpAmount += houseOut.amount;
		}
		householdOutcomePerWeek = tmpAmount;
	}
	public void addHouseOutcome(String name, int amount){
		currentPage = "";

		houseOutcome.add(new Amount(name, amount, "Null"));
		calcHouseOut();
	}
	public void removeHouseOutcome(Amount amount){
		houseOutcome.remove(amount);
	}
	public void calcHouseOutcome(){

		double tempAmount = 0;
		for(Amount outcomeAmount : houseOutcome){
			tempAmount += outcomeAmount.amount;
		}
		householdOutcomePerWeek = tempAmount;
	}

	public void addHouse(){		
		currentPage = "";

		clearScreen(null);

		makeLabel("Name", 15, 10,400, 100, 50, Color.white, Color.black, false);
		makeLabel("Amount", 15, 120,400, 100, 50, Color.white, Color.black, false);

		makeTextField("Name Of House Expense", 20, 10, 440, 100, 50, Color.white, Color.black);
		makeTextField("Amount Of House Expense", 20, 120, 440, 100, 50, Color.white, Color.black);

		makeButton("Back", 20, 890, 450, 100, 40, e -> defaultPage());
		
		makeButton("Enter", 20, 230, 440, 100, 50, e ->  addHouseO());
		
		int userNum = 1;
		int buttonX = 10;
		for(Amount amount: houseOutcome){
			JRadioButton button = new JRadioButton();
			button.setText(amount.name + " $" + amount.amount);
			if(userNum % 11 == 1 && userNum > 2)
			{
				buttonX += 201;
				userNum -= 11;
			}
			button.setBounds(buttonX, 10 + (userNum * 31), 200, 30);
			button.setFont(new Font("Times New Roman", Font.BOLD, 30));
			button.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			button.setBackground(Color.white);
			button.setForeground(Color.black);
			button.setFocusPainted(false);
			button.addActionListener(e -> houseOutcome.remove(amount));
			this.add(button);
			userNum++;
		}
		
		repaint();
	}

	public void addHouseO(){
		String temp = "";
		int temp1 = 0;
		try{
			temp = textLabels.get("Name Of House Expense").getText();
		 	temp1 = Integer.parseInt(textLabels.get("Amount Of House Expense").getText());
			if(temp.length() > 0 ){
				if(temp1> 0){
					addHouseOutcome(temp, temp1);
				}
			}
			defaultPage();
		}
		catch (Exception e){
			makeLabel("Please Enter Valid Information", 10, 10, 10, 10, 10, Color.black, Color.white, true);
		}
		
	}

	public void incomePanel(){

		int userNum = 1;
		for(Amount amount : currentUser.income){
			if(userNum < 16)
			makeLabel("   " + amount.name + " $" + amount.amount, 20, 10, 55 + (userNum * 25), 300, 25, Color.green, Color.black, true);
			userNum++;
		}
		repaint();
	}

	public void outcomePanel(){
		int userNum = 1;
		for(Amount amount : currentUser.outcome){
			if(userNum < 16)
			makeLabel("   " + amount.name + " $" + amount.amount, 20, 320, 55 + (userNum * 25), 300, 25, Color.red, Color.black, true);
			userNum++;
		}
		repaint();
	}

	public void housePanel(){
		int userNum = 1;
		for(Amount amount : houseOutcome){
			if(userNum < 12)
			makeLabel("   " + amount.name + " $" + amount.amount, 20, 630, 80 + (userNum * 25), 150, 25, Color.red, Color.black, true);
			userNum++;
		}
		repaint();
	}

	public void currentUserPanel(){
		makeLabel("  " + currentUser.firstName.charAt(0) + ". " + currentUser.lastName, 40, 630, 10, 250, 85, Color.white, Color.black, true);
		labelList.get("  " + currentUser.firstName.charAt(0) + ". " + currentUser.lastName).setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	}

	public void viewStats(){

	}

	public void currentUserIncomeTotal(){
		currentUser.calcIncome();
		if(currentUser.incomePerWeek > 0){
		makeLabel("  Income Weekly: " + currentUser.incomePerWeek, 20, 10, 10, 300, 50, Color.green, Color.black, true);
		}
	}
	public void currentUserOutcomeTotal(){
		currentUser.calcOutcome();
		if(currentUser.outcomePerWeek > 0){
		makeLabel("  Outcome Weekly: " + currentUser.outcomePerWeek, 20, 320, 10, 300, 50, Color.red, Color.black, true);
		}
	}
}
