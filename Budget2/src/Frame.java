import javax.swing.JFrame;

public class Frame extends JFrame {
	Frame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new Panel());
		this.pack();
		this.setAlwaysOnTop(true);
		this.setTitle("Budget 101");
		this.setVisible(true);

	}

}
