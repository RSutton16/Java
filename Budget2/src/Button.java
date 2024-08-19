import javax.swing.JButton;

public class Button extends JButton{

    public Button(int x, int y, int sizeX, int sizeY, Panel panel) {
        this.setBounds(x, y, sizeX, sizeY);
        panel.add(this);
    }
}
