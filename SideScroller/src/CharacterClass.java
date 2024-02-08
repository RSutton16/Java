import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.util.ArrayList;

import javax.imageio.ImageIO;
public class CharacterClass{
    private int health, maxHealth;
    private double xVelocity, yVelocity;
    private double speedMultiplyer;
    public double stamina;
    private double maxStamina;
    private double xPos, yPos, width, height;
    private double xVelocityChanger = .05;
    private double yVelocityChanger = .6;
    private double walkSpeed = 1;
    private double crouchSpeed = .5;
    private double runSpeed = 3;
    private charActionSheet currentAction = charActionSheet.IDLE;
    private boolean jumping = false;
    private double jumpHeight = this.yPos - 50;
    private charDirectionSheet currentDirection = charDirectionSheet.RIGHT;
    private Rectangle charCollision;
    //private ArrayList<BufferedImage> jumpAnim = new ArrayList<>();
    private ArrayList<BufferedImage> idleAnim = new ArrayList<>();
    private ArrayList<BufferedImage> walkAnim = new ArrayList<>();
    private ArrayList<BufferedImage> runAnim = new ArrayList<>();

    private BufferedImage charSpriteImage;
    private ArrayList<BufferedImage> currentAnimation;


    public CharacterClass(int health, int maxHealth, int stamina, int maxStamina, double xPos, double yPos){
        this.health = health;
        this.maxHealth = maxHealth;
        this.stamina = stamina;
        this.maxHealth = maxHealth;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = 48;
        this.height = 96;
        charCollision = new Rectangle();
        loadAnimations("Woodcutter_idle.png",4, idleAnim);
        loadAnimations("Woodcutter_walk.png",6, walkAnim);
        loadAnimations("Woodcutter_run.png",6, runAnim);

        currentAnimation = walkAnim;


    }
    
    private double animNumber = 1;
    public void update(){
        updateCollisionBox();
        xCommands();
        yCommands();
        restoreHealth();


        if(animNumber + 1 < currentAnimation.size()){
            animNumber += .05;
        } else {
            animNumber = 0;
        }
    }



    public void draw(Graphics g){
        if(charDirectionSheet.LEFT == currentDirection){
            g.drawImage(currentAnimation.get((int)animNumber), (int)this.xPos + (int)this.width, (int)this.yPos, ((int)width + 32) * -1, (int)height, null);
        } else {       
            g.drawImage(currentAnimation.get((int)animNumber), (int)this.xPos, (int)this.yPos, (int)width + 32, (int)height, null);
        }        
    
    //g.drawRect((int)this.xPos, (int)this.yPos, (int)this.width, (int)this.height);
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
                currentAnimation = idleAnim;
            break;
            case CROUCHING:
                xChangeVelocity(crouchSpeed);
                currentAnimation = walkAnim;
            break;
            case WALKING:
                xChangeVelocity(walkSpeed);
                currentAnimation = walkAnim;
            break;
            case RUNNING:
                xChangeVelocity(runSpeed);
                currentAnimation = runAnim;
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

    private void loadAnimations(String path, int amount, ArrayList<BufferedImage> list){
        InputStream is = getClass().getResourceAsStream(path);
        try{
            charSpriteImage = ImageIO.read(is);

        } catch (IOException e ){
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e){
                e.printStackTrace();
            }

            }

        for(int i = 1; i < amount; i++){
            list.add(charSpriteImage.getSubimage(i * 48 - 48, 16, 48, 32));
        }
    }

}
