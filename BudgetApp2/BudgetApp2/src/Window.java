import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Window extends JPanel {

	Window() {
		this.setPreferredSize(new Dimension(1000, 500));
		this.setFocusable(true);
	}

	public void paintComponent(Graphics g){

	}

	private void scaler(int wantedWidth, int wantedHeight, int wantedX, int wantedY){
		
	}
}
