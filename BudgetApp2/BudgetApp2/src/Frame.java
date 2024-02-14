import javax.swing.JFrame;

public class Frame extends JFrame{
	Frame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new Window());
		this.pack();
		this.setAlwaysOnTop(true);
		this.setTitle("Budget 102 Alpha Build 2");
		this.setVisible(true);
	}
}
