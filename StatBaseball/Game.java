package StatBaseball;

public class Game implements Runnable{
    Panel panel;
    Game(Panel panel){
        this.panel = panel;
    }

    /*

     * game loop idea
     * call method 120 per second
     * put 1 second into milliseconds
     * 1000 milliseconds / 120 = timePerUpdate
     * 
     */

    double timePerFrame = 1000000000/120;
    double timePerUpdate = 1000000000/200;
    double lastFrame;
    double lastUpdate;
    double now = System.nanoTime();
    @Override
    public void run() {
        while(true){
            now = System.nanoTime();

            if((lastUpdate - now) > timePerUpdate){
                lastUpdate = now;
                
            }
        }
    }
    
}
