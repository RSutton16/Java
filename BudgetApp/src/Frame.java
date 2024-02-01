import javax.swing.JFrame;

public class Frame extends JFrame{
	Frame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new Panel1());
		this.pack();
		this.setVisible(true);
	}
}
