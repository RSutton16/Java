import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Panel1 extends JPanel {
	public static ArrayList<User> userList = new ArrayList<>();
	public Map<String, JTextField> textLabels = new HashMap<>();
	public Map<String, JButton> buttonList = new HashMap<>();
	ArrayList<Amount> houseOutcome = new ArrayList<>();

	double householdOutcomePerWeek = 0;
	User currentUser = userList.get(0);
	JButton currentButtonPress = null;
	
	Panel1() {
		this.setPreferredSize(new Dimension(1000, 500));
		this.setFocusable(true);

		//homeScreen();
		defaultPage();

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
		button.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.black));
		button.setBorderPainted(true);
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
		label.setOpaque(backgroundVisible);
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
		System.out.println(currentUser.firstName + " " + currentUser.lastName);
		System.out.println(currentUser.incomePerWeek);
	}

	public void homeScreen() {
		clearScreen(null);
		makeButton("Get Started", 20, 0, 0, 200, 150, e -> defaultPage());
	}

	public void defaultPage(){
		int sepAmo = 10;
		int buttonSize = 85;
		clearScreen(null);
		incomePanel();
		outcomePanel();
		housePanel();
		currentUserPanel();

		makeButton("New User", 10, 890, 10, buttonSize, buttonSize, e -> newUser());
		makeButton("Choose User", 10, 890, 10 + buttonSize + sepAmo, buttonSize, buttonSize, e -> chooseUser());
		makeButton("Delete User", 10, 890,  10 + buttonSize * 2 + sepAmo * 2, buttonSize, buttonSize, e -> deleteUser());
		makeButton("View Stats", 10, 890, 10+ buttonSize * 3 + sepAmo * 3, buttonSize, buttonSize, e -> defaultPage());
		
		makeButton("Edit Income", 10, 795, 10 + buttonSize + sepAmo, buttonSize, buttonSize, e -> addIncome());
		makeButton("Edit Outcome", 10, 795,  10 + buttonSize * 2 + sepAmo * 2, buttonSize, buttonSize, e -> addOutcome());
		makeButton("Edit House", 10, 795, 10+ buttonSize * 3 + sepAmo * 3, buttonSize, buttonSize, e -> addHouse());
		
	}

	

	public void newUser(){
		clearScreen(null);
		
		makeTextField("Enter First Name", 30, 10, 10, 200, 40, Color.white, Color.black);
		makeTextField("Enter Last Name", 30, 10, 60, 200, 40, Color.white, Color.black);
		makeButton("Back", 20, 300, 300, 100, 40, e -> defaultPage());
		makeButton("Create User", 10, 10, 110, 200, 40, e -> new User(textLabels.get("Enter First Name").getText(), textLabels.get("Enter Last Name").getText()));
	}

	public void chooseUser(){
		clearScreen(null);

		ButtonGroup buttonGroup = new ButtonGroup();
		makeButton("BACK", 400, 700, 20, 100, 100, e -> defaultPage());


		int userNum = 1;
		for(User user: userList){
			JRadioButton button = new JRadioButton();
			button.setText(user.firstName + " " + user.lastName);
			buttonGroup.add(button);
			button.setBounds(10, 20 + (userNum * 60), 200, 30);
			button.addActionListener(e -> currentUser = user);
			this.add(button);
			userNum++;
		}		
		repaint();
	}

	public void deleteUser(){
		clearScreen(null);

		makeButton("BACK", 400, 700, 20, 100, 100, e -> defaultPage());

		int userNum = 1;
		for(User user: userList){
			JRadioButton button = new JRadioButton();
			button.setText(user.firstName + " " + user.lastName);
			button.setBounds(10, 20 + (userNum * 60), 200, 30);
			button.addActionListener(e -> userList.remove(user));
			this.add(button);
			userNum++;
		}		
		repaint();
	}

	public void addIncome(){
		clearScreen(null);

		makeTextField("Name Of Income", 10, 10, 10, 100, 50, Color.white, Color.black);
		makeTextField("Amount Of Income", 10, 10, 60, 100, 50, Color.white, Color.black);
		makeButton("Enter", 10, 10, 120, 100, 100, e -> currentUser.newIncome(textLabels.get("Name Of Income").getText(), Integer.parseInt(textLabels.get("Amount Of Income").getText()), "Null"));


		makeButton("BACK", 10, 700, 20, 100, 100, e -> defaultPage());

		int userNum = 1;
		for(Amount amount: currentUser.income){
			JRadioButton button = new JRadioButton();
			button.setText(amount.name + ": " + amount.amount);
			button.setBounds(150, 20 + (userNum * 60), 200, 30);
			button.addActionListener(e -> currentUser.removeIncome(amount));
			this.add(button);
			userNum++;
		}
		
		repaint();
	}

	public void addOutcome(){
		clearScreen(null);

		makeTextField("Name Of Expense", 10, 10, 10, 100, 50, Color.white, Color.black);
		makeTextField("Amount Of Expense", 10, 10, 60, 100, 50, Color.white, Color.black);
		makeButton("Enter", 10, 10, 120, 100, 100, e -> currentUser.addOutcome(textLabels.get("Name Of Expense").getText(), Integer.parseInt(textLabels.get("Amount Of Expense").getText()), "Null"));


		makeButton("BACK", 10, 700, 20, 100, 100, e -> defaultPage());

		int userNum = 1;
		for(Amount amount: currentUser.outcome){
			JRadioButton button = new JRadioButton();
			button.setText(amount.name + ": " + amount.amount);
			button.setBounds(150, 20 + (userNum * 60), 200, 30);
			button.addActionListener(e -> currentUser.removeOutcome(amount));
			this.add(button);
			userNum++;
		}
		
		repaint();
	}

	public void addHouseOutcome(String name, int amount){
		houseOutcome.add(new Amount(name, amount, "Null"));
	}
	public void removeHouseOutcome(Amount amount){
		houseOutcome.remove(amount);
	}
	public void calcHouseOutcome(){
		double tempAmount = 0;
		for(Amount outcomeAmount : houseOutcome){
			tempAmount += outcomeAmount.amount;
			System.out.println(outcomeAmount.name + ": " + outcomeAmount.amount);
		}
		householdOutcomePerWeek = tempAmount;
	}

	public void addHouse(){
		clearScreen(null);

		makeTextField("Name Of Expense", 10, 10, 10, 100, 50, Color.white, Color.black);
		makeTextField("Amount Of Expense", 10, 10, 60, 100, 50, Color.white, Color.black);
		makeButton("Enter", 10, 10, 120, 100, 100, e -> addHouseOutcome(textLabels.get("Name Of Expense").getText(), Integer.parseInt(textLabels.get("Amount Of Expense").getText())));
		makeButton("BACK", 10, 700, 20, 100, 100, e -> defaultPage());

		int userNum = 1;
		for(Amount amount: houseOutcome){
			JRadioButton button = new JRadioButton();
			button.setText(amount.name + ": " + amount.amount);
			button.setBounds(150, 20 + (userNum * 60), 200, 30);
			button.addActionListener(e -> houseOutcome.remove(amount));
			this.add(button);
			userNum++;
		}
		
		repaint();
	}

	public void incomePanel(){
		int userNum = 1;
		for(Amount amount : currentUser.income){
			makeLabel("   " + amount.name + ": " + amount.amount, 20, 10, 55 + (userNum * 50), 300, 50, Color.green, Color.black, true);
			userNum++;
		}
		repaint();
	}

	public void outcomePanel(){
		int userNum = 1;
		for(Amount amount : currentUser.outcome){
			makeLabel("   " + amount.name + ": " + amount.amount, 20, 320, 55 + (userNum * 50), 300, 50, Color.red, Color.black, true);
			userNum++;
		}
		repaint();
	}

	public void housePanel(){
		int userNum = 1;
		for(Amount amount : houseOutcome){
			makeLabel("   " + amount.name + ": " + amount.amount, 20, 630, 55 + (userNum * 50), 150, 50, Color.red, Color.black, true);
			userNum++;
		}
		repaint();
	}

	public void currentUserPanel(){
		makeLabel(currentUser.firstName + " " +currentUser.lastName, 30, 630, 10, 250, 85, Color.white, Color.black, true);
	}

	public void viewStats(){

	}
}
