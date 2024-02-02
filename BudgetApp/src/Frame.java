import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Frame extends JFrame{
	Frame(){
		ImageIcon image = new ImageIcon("RRRPixel.png");
		this.setIconImage(image.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new Panel1());
		this.pack();
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setTitle("Budget 101");
		this.setVisible(true);
	}
}
