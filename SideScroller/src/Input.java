import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener{
    
    PlayerClass player;
    public Input(PlayerClass player){
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.buttonClicked(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.buttonUnClicked(e);
    }
    
}

