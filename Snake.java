import javafx.scene.paint.*;
import javafx.scene.shape.*;
public class Snake
{
    private SnakePart[] parts;
    private int length;
    private boolean hasEaten;
    
    public Snake(int[] xCoords, int[] yCoords, int len){
        parts = new SnakePart[len];
        length = len;
        for(int i = 0; i < parts.length; i++){
            parts[i] = new SnakePart(xCoords[i], yCoords[i], "E", false);
        }
        parts[parts.length - 1].setHead(true);
        hasEaten = false;
    }
    
    public void move(){
        SnakePart[] temp = new SnakePart[length];
        for(int i = 1; i < parts.length; i++){
            temp[i - 1] = parts[i];
            temp[i - 1].setHead(false);
        }
        String d = temp[length - 2].getDirection();
        int x = temp[length - 2].getX();
        int y = temp[length - 2].getY();
        if(d.equals("N"))
            temp[length - 1] = new SnakePart(x, y - 1, "N", true);
        if(d.equals("E"))
            temp[length - 1] = new SnakePart(x + 1, y, "E", true);
        if(d.equals("S"))
            temp[length - 1] = new SnakePart(x, y + 1, "S", true);
        if(d.equals("W"))
            temp[length - 1] = new SnakePart(x - 1, y, "W", true);
        parts = temp;
    }
    
    public void eat(int boardSize){
        increaseLength();
        hasEaten = true;
    }
    
    public void changeDirection(String newDirection){
        String headDirection = parts[parts.length - 1].getDirection();
        if(headDirection.equals("N") || headDirection.equals("S")){
            if(newDirection.equals("E") || newDirection.equals("W"))
                parts[parts.length - 1].setDirection(newDirection);
        }
        else{
            if(newDirection.equals("N") || newDirection.equals("S"))
                parts[parts.length - 1].setDirection(newDirection);
        }
    }
    
    public SnakePart getPart(int p){
        return parts[p];
    }
    
    public SnakePart getPart(int x, int y){
        for(int i = 0; i < length; i++){
            if(this.getPart(i).getX() == x && this.getPart(i).getY() == y){
                return this.getPart(i);
            }
        }
        return null;
    }
    
    public SnakePart getHead(){
        for(int i = 0; i < length; i++){
            if(parts[i].getHead())
                return parts[i];
        }
        return null;
    }
    
    public String getDirection(){
        return getHead().getDirection();
    }
    
    public boolean isValid(){
        int[] xVals = new int[length];
        int[] yVals = new int[length];
        
        for(int i = 0; i < parts.length; i++){
            for(int ii = 0; ii < i; ii++)
                if(parts[i].getX() == xVals[ii] && parts[i].getY() == yVals[ii])
                    return false;
            xVals[i] = parts[i].getX();
            yVals[i] = parts[i].getY();
        }
        
        return true;
    }
    
    private void increaseLength(){
        SnakePart[] temp = new SnakePart[parts.length + 1];
        for(int i = 0; i < parts.length; i++){
            temp[i + 1] = parts[i];
        }
        temp[0] = null;
        parts = temp;
        length++;
    }
    
    public int getLength(){
        return length;
    }
    
    public String[][] toArr(){
        String[][] result = new String[10][10];
        for(int i = 0; i < parts.length; i++){
            SnakePart p = parts[i];
            if(p == null)
                break;
            int y = p.getY();
            int x = p.getX();
            if(p != null && x >= 0 && y >= 0 && x < 10 && y < 10)
                result[parts[i].getY()][parts[i].getX()] = parts[i].toString();
        }
        return result;
    }
}
