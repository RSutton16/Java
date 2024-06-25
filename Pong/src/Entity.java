import java.awt.Graphics;
import java.awt.Rectangle;
public class Entity {
    public float x, y, width, height;
    public float xVelo, yVelo;
    private Rectangle hitBox;

    public Entity(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        hitBox = new Rectangle((int)x, (int)y, (int)width, (int)height);
        Panel.entites.add(this);
    }

    public void update(){
        move();
        updateBorder();
    }

    public void draw(Graphics g){
        g.fillRect((int)x,(int)y, (int)width, (int)height);
    }

    public void move(){
        this.x += xVelo;
        this.y += yVelo;
    }

    private void updateBorder(){
        hitBox.x = (int)x;
        hitBox.y = (int)y;
    }
}
