import javafx.scene.paint.*;
import javafx.scene.shape.*;
public class Square
{
    int xCoord, yCoord;
    boolean hasApple;
    public Square(int x, int y){
        xCoord = x;
        yCoord = y;
        hasApple = false;
    }
    
    public void setApple(boolean apple){
        hasApple = apple;
    }
    
    public boolean getApple(){
        return hasApple;
    }
    
    public int getX(){
        return xCoord;
    }
    public int getY(){
        return xCoord;
    }
    
    public Shape display(double x, double y, double width, double radius){
        Shape s = null;
        if(hasApple){
            s = new Circle(x + width/2, y + width/2, radius);
            s.setFill(Color.RED);
            s.setStrokeWidth(3);
            s.setStroke(Color.BLACK);
            return s;
        }
        return s;
    }
}
