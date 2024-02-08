public class LevelCreator {

    public LevelCreator(){
        testLevel();
    }

    private void testLevel(){
        newObject(0, 450, 60, 1000);
    }

    private void newObject(double x, double y, double height, double width){
        new Object(x, y, width, height);
    }
}
