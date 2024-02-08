import java.awt.Graphics;
import java.awt.Rectangle;
public class CharacterClass{
    private int health, maxHealth;
    private double xVelocity, yVelocity;
    private double speedMultiplyer;
    public double stamina;
    private double maxStamina;
    private double xPos, yPos, width, height;
    private double xVelocityChanger = .5;
    private double yVelocityChanger = .6;
    private double walkSpeed = 3;
    private double crouchSpeed = .5;
    private double runSpeed = 5;
    private charActionSheet currentAction = charActionSheet.IDLE;
    private boolean jumping = false;
    private double jumpHeight = this.yPos - 50;
    private charDirectionSheet currentDirection = charDirectionSheet.RIGHT;
    private Rectangle charCollision;

    public CharacterClass(int health, int maxHealth, int stamina, int maxStamina, double xPos, double yPos, double width, double height){
        this.health = health;
        this.maxHealth = maxHealth;
        this.stamina = stamina;
        this.maxHealth = maxHealth;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        charCollision = new Rectangle();
    }
    
    public void update(){
        updateCollisionBox();
        xCommands();
        yCommands();
        restoreHealth();
    }



    public void draw(Graphics g){
        g.drawRect((int)this.xPos, (int)this.yPos, (int)this.width, (int)this.height);
    }

    public void updateCollisionBox(){
        this.charCollision.x = (int)this.xPos;
        this.charCollision.y = (int)this.yPos;
        this.charCollision.width = (int)this.width;
        this.charCollision.height = (int)this.height;
    }

    public void changeAction(charActionSheet action){
        this.currentAction = action;
    }
    
    public void changeDirection(charDirectionSheet dir){
        this.currentDirection = dir;
    }

    public void takeDMG(int amount){
        this.health -= amount;
    }

    private void restoreHealth(){
        this.health += 1;
    }

    private void xCommands(){
        switch (currentAction){
            case IDLE:
                xChangeVelocity(0);
            break;
            case CROUCHING:
                xChangeVelocity(crouchSpeed);
            break;
            case WALKING:
                xChangeVelocity(walkSpeed);
            break;
            case RUNNING:
                xChangeVelocity(runSpeed);
            break;
        }
        this.xPos += xVelocity;
    }

    public void jump(){
        jumpHeight = this.yPos - 50;
        jumping = true;
    }

    private void yCommands(){
        boolean collision = false;
        for (Object object : Panel.objectCollision) {
            if(object.getRect().intersects(charCollision)){
                collision = true;
                yPos = object.getY() - height;
            }
        }

        if(!collision){
            if(jumping){
                yPos += yVelocityChanger;
            } else {
                yPos += yVelocityChanger;
            }
        } 
    }

    private void xChangeVelocity(double xWantedVelo){
        if(currentDirection == charDirectionSheet.LEFT)
            xWantedVelo *= -1;

        if (xVelocity > xWantedVelo){
            this.xVelocity -= xVelocityChanger;
        } else if (xVelocity < xWantedVelo){
            this.xVelocity += xVelocityChanger;
        }
    }

}
