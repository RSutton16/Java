
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Panel extends JPanel{
	final int screenWidth = 1000;
	final int screenHeight = 500;
	public static ArrayList<Entity> entites = new ArrayList<>();

	Ball ball = new Ball(10, 10, 10, 10);
	Paddle paddleL = new Paddle(10, screenHeight / 2 - 50, 10, 50);
	Paddle paddleR = new Paddle(screenWidth - 20, screenHeight / 2 - 50, 10, 50);

	public Panel(){
		this.setPreferredSize(new Dimension(1000, 500));
		this.setFocusable(true);
	}
	
	public void drawGame(Graphics g){
		for (Entity entity : entites) {
			entity.draw(g);
		}
	}
	
	public void updateGame(){
		for (Entity entity : entites) {
			entity.update();
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawGame(g);
	}
}
