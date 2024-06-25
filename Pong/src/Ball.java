public class Ball extends Entity{
    Ball(float x, float y, float width, float height){
        super(x, y, width, height);
        xVelo = 1;
        yVelo = 1;
    }

    public void update(){
        if(x - xVelo < 0 || x + xVelo > 1000){
            xVelo = -xVelo;
        }
        if(y - yVelo < 0 || y + yVelo > 500){
            yVelo = -yVelo;
        }
        super.update();
        
    }


}
