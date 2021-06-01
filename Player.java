import java.util.Random;
public class Player
{
    private int AILevel = -1;
    private SquareGrid grid;
    
    
    public Player(SquareGrid myGrid){
        grid = myGrid;
    }
    public Player(SquareGrid myGrid, int myAI){
        AILevel = myAI;
        grid = myGrid;
    }
    
    public void makeAMove(){
        if(AILevel == 0)
            grid.getSnake().changeDirection(AI0());
        else if(AILevel == 1)
            grid.getSnake().changeDirection(AI1());
        else if(AILevel == 2){
            grid.getSnake().changeDirection(AI2());
        }
        grid.getSnake().move();
    }
    
    public String randDirection(){
        Random r = new Random();
        int direction = r.nextInt(4);
        String d = "E";
        if(direction == 1)
            d = "S";
        else if(direction == 2)
            d = "W";
        else if(direction == 3)
            d = "N";
        return d;
    }
    
    public SquareGrid getGrid(){
        return grid;
    }
    
    public String AI0(){
        return randDirection();
    }
    
    public String AI1(){
        Square a = grid.getApple();
        if(a == null)
            return AI0();
        else{
            int x = a.getX();
            int y = a.getY();
            String d = grid.getSnake().getDirection();
            int headX = grid.getSnake().getHead().getX();
            int headY = grid.getSnake().getHead().getY();
            if(headX < x){
                if(d != "E")
                    return "E";
                else if(headY < y)
                    return "S";
                else
                    return "N";
            }
            else if(headX > x){
                if(d != "W")
                    return "W";
                else if(headY < y)
                    return "S";
                else
                    return "N";
            }
            else if(headY < y){
                return "S";
            }
            else{
                return "N";
            }
        }
    }
    
    public String AI2(){
        int max = grid.getSize();
        String d = grid.getSnake().getDirection();
        int headX = grid.getSnake().getHead().getX();
        int headY = grid.getSnake().getHead().getY();
        if(headX < max - 1 && headY < max - 1){
            return AI1();
        }else{
            if(d.equals("E") || d.equals("W")){
                if(headY > (int) max/2)
                    return "N";
                else
                    return "E";
            }
            else{
                if(headX > (int) max/2)
                    return "E";
                else
                    return "W";
            }
        }
    }
}
