import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel extends JPanel {
	public static ArrayList<User> userList = new ArrayList<>();
	User currentUser = userList.get(1);

	Panel() {
		this.setPreferredSize(new Dimension(1000, 500));
		this.setFocusable(true);

		homeScreen();

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
	public void makeButton(String text, int size, int x, int y, int width, int height, Color backColor, Color textColor,
			ActionListener e) {
		JButton button = new JButton();
		button.setText(text);
		button.setBounds(x, y, width, height);
		button.setFont(new Font("Times New Roman", Font.BOLD, size));
		button.setBackground(backColor);
		button.setForeground(textColor);
		button.addActionListener(e);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		this.add(button);

	}
	
	public void makeLabel(String text, int size, int x, int y, int width, int height, Color backColor, Color textColor,boolean backgroundVisible){
		JLabel label = new JLabel();
		button.setText(text);
		label.setBounds(x, y, width, height);
		label.setFont(new Font("Times New Roman", Font.BOLD, size));
		label.setBackground(backColor);
		label.setForeground(textColor);
		label.setOpaque(backgroundVisible);
		this.add(label);
	}

	public void clearScreen() {
		this.removeAll();
		this.repaint();
	}

	public void homeScreen() {
		clearScreen();
		this.setLayout(null);
		makeButton("New User", 20, 0, 0, 200, 150, Color.gray, Color.orange, e -> newUser());
		makeButton("Choose User", 20, 250, 0, 200, 150, Color.gray, Color.orange, e -> chooseUser());

	}

	public void newUser() {
		clearScreen();
		
	}

	public void chooseUser() {
		clearScreen();
		int person = 0;
		for (User user : userList) {
			this.setLayout(new GridLayout());
			makeButton(user.firstName + " " + user.lastName, 20, person * 210, 0, 200, 150, Color.gray, Color.orange, e -> setUser(user));
			person++;
		}
	}

	public void setUser(User user) {
		currentUser = user;
		System.out.println(currentUser.firstName);
		addAndRemoveExpenseScreen();
	}

	public void addAndRemoveExpenseScreen() {
		clearScreen();
		this.setLayout(null);
		
		JTextField textBox = new JTextField(2);
		textBox.setBounds(10, 400, 200, 50);
		textBox.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textBox.setBackground(Color.gray);
		textBox.setForeground(Color.orange);
		this.add(textBox);

		JLabel userName = new JLabel();
		userName.setBounds(10, 50, 95, 40);
		userName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		userName.setBackground(Color.gray);
		userName.setForeground(Color.orange);
		userName.setText(currentUser.firstName + " " + currentUser.lastName);
		userName.setOpaque(true);
		this.add(userName);

		makeButton("Add Income", 10, 10, 110, 100, 50, Color.gray, Color.orange, e -> currentUser.newIncome());
		makeButton("Delete Income", 10, 10, 170, 100, 50, Color.gray, Color.orange, e -> currentUser.removeIncome(this));
		makeButton("Clear Income", 10, 10, 230, 100, 50, Color.gray, Color.orange, e -> currentUser.clearIncome());


	}
}
