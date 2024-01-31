package StatBaseball;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;



public class Panel extends JPanel{

    Panel(int width, int height){
        this.setPreferredSize(new Dimension(width, height));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawRect(10, 20, 100, 100);
    }

    public void updateGame(){
        
    }
}
