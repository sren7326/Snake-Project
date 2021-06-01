import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.transform.*;
public class SnakePart
{
    int xCoord, yCoord;
    String direction;
    boolean isHead;
    
    public SnakePart(int x, int y, String myDirection, boolean head){
        xCoord = x;
        yCoord = y;
        direction = myDirection;
        isHead = head;
    }
    
    public int getX(){
        return xCoord;
    }
    public int getY(){
        return yCoord;
    }
    public void setX(int x){
        xCoord = x;
    }
    public void setY(int y){
        yCoord = y;
    }
    
    public boolean getHead(){
        return isHead;
    }
    public void setHead(boolean newHead){
        isHead = newHead;
    }
    
    public String getDirection(){
        return direction;
    }
    public void setDirection(String newDirection){
        direction = newDirection;
    }
    
    public Rectangle display(double x, double y, double width, double height){
        Rectangle r = new Rectangle(x, y, width, height);
        r.setFill(Color.MEDIUMBLUE);
        r.setStrokeWidth(3);
        r.setStroke(Color.BLACK);
        r.setArcWidth(height/2);
        r.setArcHeight(height/2);
        if(direction.equals("N") || direction.equals("S")){
            r.setY(y - width);
            r.setWidth(height);
            r.setHeight(width);
        }
        return r;
    }
    
    public String toString(){
        if(isHead)
            if(direction.equals("N"))
                return("^");
            else if (direction.equals("E"))
                return(">");
            else if (direction.equals("W"))
                return("<");
            else
                return ("v");
        if(direction.equals("N") || direction.equals("S"))
            return("H");
        else
            return("=");
    }
}
