import java.awt.event.KeyEvent;

public class PlayerClass extends CharacterClass{
    private int keyCodeJump, keyCodeCrouch, keyCodeLeft, keyCodeRight, keyCodeSprint;
    public boolean leftKeyPressed = false;
    public boolean rightKeyPressed = false;
    public boolean sprintKeyPressed = false;
    public boolean jumpKeyPressed = false;
    public boolean crouchKeyPressed = false;

    public PlayerClass(int health, int maxHealth, int stamina, int maxStamina, double xPos, double yPos, int keyCodeJump, int keyCodeCrouch, int keyCodeSprint, int keyCodeRight, int keyCodeLeft) {
        super(health, maxHealth, stamina, maxStamina, xPos, yPos);
                this.keyCodeCrouch = keyCodeCrouch;
                this.keyCodeJump = keyCodeJump;
                this.keyCodeLeft = keyCodeLeft;
                this.keyCodeSprint = keyCodeSprint;
                this.keyCodeRight = keyCodeRight;
                
                newAnimation(charActionSheet.IDLE, "Woodcutter_idle.png", 4);
                newAnimation(charActionSheet.WALKING, "Woodcutter_walk.png", 6);
                newAnimation(charActionSheet.CROUCHING, "Woodcutter_jump.png", 6);
                newAnimation(charActionSheet.RUNNING, "Woodcutter_run.png", 6);

    }
    
    public void buttonClicked(KeyEvent e){
        int code = e.getKeyCode();
        if(code == keyCodeJump){
            jumpKeyPressed = true;
        } else if (code == keyCodeCrouch){
            crouchKeyPressed = true;
        } else if (code == keyCodeLeft){
            leftKeyPressed = true;
        } else if (code == keyCodeRight){
            rightKeyPressed = true;
        } else if (code == keyCodeSprint){
            sprintKeyPressed = true;
        }
        controller();
    }
    
    public void buttonUnClicked(KeyEvent e){
        int code = e.getKeyCode();
        if(code == keyCodeJump){
            jumpKeyPressed = false;
        } else if (code == keyCodeCrouch){
            crouchKeyPressed = false;
        } else if (code == keyCodeLeft){
            leftKeyPressed = false;
            changeAction(charActionSheet.IDLE);
        } else if (code == keyCodeRight){
            rightKeyPressed = false;
            changeAction(charActionSheet.IDLE);
        } else if (code == keyCodeSprint){
            sprintKeyPressed = false;
        }    
        controller();
    }

    private void controller(){
        if(leftKeyPressed && !rightKeyPressed){
            changeDirection(charDirectionSheet.LEFT);
            changeSpeeds();
        } else if(rightKeyPressed && !leftKeyPressed){
            changeDirection(charDirectionSheet.RIGHT);
            changeSpeeds();
        } else {
            changeAction(charActionSheet.IDLE);
        }
    }
        
    private void changeSpeeds(){
        if(!sprintKeyPressed && !crouchKeyPressed){
            changeAction(charActionSheet.WALKING);
        } else if(!crouchKeyPressed && sprintKeyPressed){
            changeAction(charActionSheet.RUNNING);
        } else if(!sprintKeyPressed && crouchKeyPressed) {
            changeAction(charActionSheet.CROUCHING);
        }

        if(jumpKeyPressed){
            jump();
        }
    }
}
