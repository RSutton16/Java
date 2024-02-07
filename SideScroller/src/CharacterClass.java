public class CharacterClass{
    private int health, maxHealth, stamina, maxStamina;
    private double xVelocity, yVelocity;
    private double speedMultiplyer;
    private double xPos, yPos, width, height;
    private double xVelocityChanger = .5;
    private double yVelocityChanger = .6;
    private double walkSpeed = 5;
    private double crouchSpeed = 2.5;
    private double runSpeed = 7.5;
    private charActionSheet currentAction = charActionSheet.IDLE;
    private boolean jumping = false;
    
    public void update(){
    xCommands();
    yCommands();
    }
    public void draw(){

    }
    public void control(){

    }
    private void xCommands(){
        switch (currentAction){
            case IDLE:
                xChangeVelocity(0);
            break;
            case CROUCHING:
                xChangeVelocity(crouchSpeed);
            case WALKING:
                xChangeVelocity(walkSpeed);
            break;
            case RUNNING:
                xChangeVelocity(runSpeed);
            break;
        }
        xPos += xVelocity;
    }

    private void yCommands(){
        if(!jumping){
            //gravity
            yPos -= yVelocityChanger;
        }
        if(jumping){
            
        }
    }

    private void xChangeVelocity(double wantedVelo){
        if (xVelocity > wantedVelo){
            xVelocity -= xVelocityChanger;
        } else if (xVelocity < wantedVelo){
            xVelocity += xVelocityChanger;
        }
    }

}
