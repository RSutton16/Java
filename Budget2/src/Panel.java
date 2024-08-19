import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {
	int panelSizeX;
	int panelSizeY;
	Panel() {
		this.setPreferredSize(new Dimension(1000, 500));
		this.setFocusable(true);
		this.setLayout(null);
		new Button(25, 1, 25, 25, this);
	}

	@Override
	protected void paintComponent(Graphics g) {

	}

	public int ScalerX(int location){
		return location;
	}
	public int ScalerY(int location){
		return location;
	}

}