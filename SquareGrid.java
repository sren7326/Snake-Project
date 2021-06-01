import javafx.scene.paint.*;
import javafx.scene.shape.*;
public class SquareGrid
{
    private Square[][] squares;
    private Snake s;
    private int size;
    public SquareGrid(int sideLength, Snake mySnake){
        squares = new Square[sideLength][sideLength];
        for(int row = 0; row < sideLength; row++){
            for(int col = 0; col < sideLength; col++){
                squares[row][col] = new Square(row, col);
            }
        }
        s = mySnake;
        size = sideLength;
    }
    
    public int getSize(){
        return size;
    }
    
    public Snake getSnake(){
        return s;
    }
    
    public Square getSquare(int x, int y){
        return squares[y][x];
    }
    
    public void setApple(int x, int y, boolean a){
        squares[x][y].setApple(a);
    }
    
    public Square getApple(){
        Square s = null;
        for(int row = 0; row < squares.length; row++){
            for(int col = 0; col < squares[0].length; col++){
                if(squares[row][col].getApple())
                    s = squares[row][col];
                return s;
            }
        }
        return s;
    }
    
    public boolean checkBoard(){
        int x = s.getHead().getX();
        int y = s.getHead().getY();
        Square headSquare = getSquare(x, y);
        if(headSquare.getApple()){
            headSquare.setApple(false);
            s.eat(size);
            int appleX = (int) Math.random()*size;
            int appleY = (int) Math.random()*size;
            while(s.toArr()[appleX][appleY] != null){
                appleX = (int) Math.random()*size;
                appleY = (int) Math.random()*size;
            }
            getSquare(appleX, appleY).setApple(true);
        }
        if(s.getLength() > size*size/2)
            return false;
        return true;
    }
    
    public Rectangle[][] display(double screenSize){
        double squareSize = screenSize/size;
        Rectangle[][] answer = new Rectangle[size][size];
        double x = 2;
        double y = 2;
        Rectangle r = null;
        Color c = Color.LAWNGREEN;
        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
                r = new Rectangle(x, y, squareSize, squareSize);
                r.setFill(c);
                if(c.equals(Color.LIMEGREEN))
                    c = Color.LAWNGREEN;
                else
                    c = Color.LIMEGREEN;
                x += squareSize;
                answer[row][col] = r;
            }
            y += squareSize;
            x = 2;
            if(c.equals(Color.LIMEGREEN))
                c = Color.LAWNGREEN;
            else
                c = Color.LIMEGREEN;
        }
        return answer;
    }
    
    public boolean isValid(){
        if(!s.isValid())
            return false;
        for(int i = 0; i < s.getLength(); i++){
            SnakePart p = s.getPart(i);
            int x = p.getX();
            int y = p.getY();
            if(x < 0 || y < 0)
                return false;
            if(!(x < size && y < size))
                return false;
        }
        return true;
    }
    
    public String[][] toArr(){
        String[][] arr = new String[this.getSize()][this.getSize()];
        String[][] sArr = s.toArr();
        for(int row = 0; row < arr.length; row++){
            for(int col = 0; col < arr[0].length; col++){
                arr[row][col] = ".";
                if(sArr[row][col] != null)
                    arr[row][col] = sArr[row][col];
                else if(squares[row][col].getApple())
                    arr[row][col] = "o";
            }
        }
        return arr;
    }
}
