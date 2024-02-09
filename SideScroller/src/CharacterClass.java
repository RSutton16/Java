import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

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
    private boolean inAir = false;
    private boolean onGround = true;
    private double jumpHeight;
    private double animNumber = 1;
    private double animSpeed = .05;
    private double gravityStrength = 2;
    private charDirectionSheet currentDirection = charDirectionSheet.RIGHT;
    private Rectangle charCollision;
    private ArrayList<BufferedImage> currentAnimation = null;
    private HashMap<charActionSheet, ArrayList<BufferedImage>> animationMap = new HashMap<>();

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
       
    }
    
    public void update(){
        updateCollisionBox();
        xCommands();
        yCommands();
        restoreHealth();

        if(animNumber < currentAnimation.size() - 1){
            animNumber += animSpeed;
        } else {
            animNumber = 0;
        }
        if(health < maxHealth){
            if(yVelocity > speedMultiplyer){
                if(maxStamina > jumpHeight){
                    System.out.println(" ");
                }
            }
        }
    }

    public void draw(Graphics g){
        if(currentAnimation != null)
        if(charDirectionSheet.LEFT == currentDirection){
            g.drawImage(currentAnimation.get((int)animNumber), (int)this.xPos + (int)this.width, (int)this.yPos, ((int)width + 32) * -1, (int)height, null);
        } else {       
            g.drawImage(currentAnimation.get((int)animNumber), (int)this.xPos, (int)this.yPos, (int)width + 32, (int)height, null);
        }        
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
                currentAnimation = animationMap.get(charActionSheet.IDLE);
            break;
            case CROUCHING:
                xChangeVelocity(crouchSpeed);
                currentAnimation = animationMap.get(charActionSheet.CROUCHING);
            break;
            case WALKING:
                xChangeVelocity(walkSpeed);
                currentAnimation = animationMap.get(charActionSheet.WALKING);
            break;
            case RUNNING:
                xChangeVelocity(runSpeed);
                currentAnimation = animationMap.get(charActionSheet.RUNNING);
            break;
        }
        if(!isObjectX())
        this.xPos += xVelocity;
    }

    private void xChangeVelocity(double xWantedVelo){
        if(currentDirection == charDirectionSheet.LEFT)
            xWantedVelo *= -1;
        
        //gets rid of annoying "stick drift" feeling when you stop moving
        if(xWantedVelo == 0){
            this.xVelocity = (int)xVelocity;
        }

        if (xVelocity > xWantedVelo){
            this.xVelocity -= xVelocityChanger;
        } else if (xVelocity < xWantedVelo){
            this.xVelocity += xVelocityChanger;
        }
    }

    public void jump(){
        
    }

    private void yCommands(){
        if(yVelocity < gravityStrength){
            yVelocity += yVelocityChanger;
        } 
        if(!isObjectY()){
            yPos += yVelocity;
        }
    }

    public void newAnimation(charActionSheet action, String path, int pictureAmount){
        ArrayList<BufferedImage> images = new ArrayList<>();
        InputStream is = getClass().getResourceAsStream(path);
        try{
            BufferedImage charSpriteSheet = ImageIO.read(is);
            for(int i = 0; i < pictureAmount; i++){
                images.add(charSpriteSheet.getSubimage(i * 48, 16, 48, 32));
            }
            animationMap.put(action, images);
        
        } catch (IOException e ){
            e.printStackTrace();
        } finally {
            try {
                if(!(is == null))
                is.close();
            } catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    public boolean isObjectY(){
        charCollision.y += yVelocity;
        if(charCollision.y < 0 || charCollision.y > 500 - this.height){
            yPos -= yVelocity;
            yVelocity = 0;
            return true;
        }

        for (Object object : Panel.objectCollision) {
            if(object.getRect().intersects(charCollision)){
                yPos -= yVelocity;
                yVelocity = 0;
                return true;
            }
        }
        return false;
    }

    public boolean isObjectX(){
        charCollision.x += xVelocity;
        if(charCollision.x < 0 || charCollision.x > 1000 - this.width){
            xPos -= xVelocity;
            xVelocity = 0;
            return true;
        }

        for (Object object : Panel.objectCollision) {
            if(object.getRect().intersects(charCollision)){
                xPos -= xVelocity;
                xVelocity = 0;
                return true;
            }
        }
        return false;
    }
}
