import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;



public class Panel extends JPanel{
    Game game;
    public static ArrayList<Object> objectCollision = new ArrayList<>();


    PlayerClass testPlay = new PlayerClass(1, 1, 1, 1, 100,
     100, 50, 50, 87, 83, 
     16, 68, 65);
    Input input;

    Panel(int width, int height){
        this.setPreferredSize(new Dimension(width, height));
        this.setFocusable(true);
        game = new Game(this);
        input = new Input(testPlay);
        this.addKeyListener(input);
        new LevelCreator();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        testPlay.draw(g);
        for (Object object : objectCollision) {
            object.draw(g);
        }
    }

    public void updateGame(){
        testPlay.update();
    }
}