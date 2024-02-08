import java.awt.Graphics;
import java.awt.Rectangle;

/*
 * used to create an object, is not an object itself though, use level creator to make objects etc. 
 */
public class Object {
    private double xPos, yPos, width, height;
    private Rectangle objectBox = new Rectangle();

    public void draw(Graphics g){
        g.drawRect((int)xPos, (int)yPos, (int)width, (int)height);
    }

    public Object(double xPos, double yPos, double width, double height){
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        updateCollisionBox();
        Panel.objectCollision.add(this);
    }

    public double getY(){
        return yPos;
    }

    public Rectangle getRect(){
        return objectBox;
    }
    private void updateCollisionBox(){
        this.objectBox.x = (int)this.xPos;
        this.objectBox.y = (int)this.yPos;
        this.objectBox.width = (int)this.width;
        this.objectBox.height = (int)this.height;
    }
}
