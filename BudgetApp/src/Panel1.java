import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel1 extends JPanel {
	public static ArrayList<User> userList = new ArrayList<>();
	public Map<String, JTextField> textLabels = new HashMap<>();
	public Map<String, JButton> buttonList = new HashMap<>();

	User currentUser = userList.get(1);
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
	}

	public void homeScreen() {
		clearScreen(null);
		makeButton("Get Started", 20, 0, 0, 200, 150, e -> defaultPage());
	}

	public void defaultPage(){
		int sepAmo = 10;
		int buttonSize = 85;
		clearScreen(null);

		makeButton("New User", 10, 890, 10, buttonSize, buttonSize, e -> defaultPage());
		makeButton("Choose User", 10, 890, 10 + buttonSize + sepAmo, buttonSize, buttonSize, e -> defaultPage());
		makeButton("Delete User", 10, 890,  10 + buttonSize * 2 + sepAmo * 2, buttonSize, buttonSize, e -> defaultPage());
		makeButton("+House Outcome", 10, 890, 10+ buttonSize * 3 + sepAmo * 3, buttonSize, buttonSize, e -> defaultPage());
		
		makeButton("Edit Income", 10, 795, 10 + buttonSize + sepAmo, buttonSize, buttonSize, e -> defaultPage());
		makeButton("Edit Outcome", 10, 795,  10 + buttonSize * 2 + sepAmo * 2, buttonSize, buttonSize, e -> defaultPage());
		makeButton("-House Outcome", 10, 795, 10+ buttonSize * 3 + sepAmo * 3, buttonSize, buttonSize, e -> defaultPage());
		
		makeLabel(currentUser.firstName + " " +currentUser.lastName, 10, 630, 10, 250, buttonSize, Color.white, Color.white, true);
		makeLabel("House Outcome", 20, 630, buttonSize + sepAmo * 2, 150, 275, Color.white, Color.white, true);
		makeLabel("User Income", 20, 10, 10, 300, 480, Color.white, Color.white, true);
		makeLabel("User Outcome", 20, 320, 10, 300, 480, Color.white, Color.white, true);
	}


	/**
	 * 
	 * @param need
	 * 1 = text box and int box
	 * 2 = test box
	 */
	public void editPage(int need){
		clearScreen(null);
		switch (need){
			case 1:
				makeTextField("Text Field", 10, 50, 50, 50, 50, Color.black, Color.white);
				makeTextField("Number Field", 10, 50, 50, 50, 50, Color.black, Color.white);
				break;
			case 2: 
				makeTextField("Text Field", 10, 50, 50, 50, 50, Color.black, Color.white);
				break;
		}
		makeLabel(currentUser.firstName + currentUser.lastName, 20, 0, 0, 100, 100, Color.black, Color.white, true);
		makeLabel("Incomes", 10, 10, 100, 200, 300, Color.black, Color.WHITE, true);
		makeLabel("Outcomes", 10, 210, 100, 200, 300, Color.black, Color.WHITE, true);
		makeLabel("Income Per Week: " + currentUser.incomePerWeek, 10, 210, 100, 200, 300, Color.black, Color.WHITE, true);
		makeLabel("Outcome Per Week: " + currentUser.outcomePerWeek, 10, 210, 100, 200, 300, Color.black, Color.WHITE, true);
		makeButton("Enter", 10, 500, 400, 40, 50, null);
		
		makeButton("Remove All Income", 10, 500, 400, 40, 50, null);
		makeButton("Remove All Outcome", 10, 500, 400, 40, 50, null);
		makeButton("Enter", 10, 500, 400, 40, 50, null);

		makeButton("BACK", 20, 600, 400, 100, 100, e -> defaultPage());


	}
}
