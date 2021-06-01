import java.util.Scanner;
public class TextTester
{
    public static void printGrid(String[][] arr){
        for(int row = 0; row < arr.length; row++){
            for(int col = 0; col < arr[0].length; col++){
                System.out.print(arr[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void main(String[] args){
        Snake s, s2, s3;
        SquareGrid grid, grid2, grid3;
        String[][] gArr;
        int[] x = {0, 1, 2, 3};
        int[] y = {4, 4, 4, 4};
        s = new Snake(x, y, 4);
        s2 = new Snake(x, y, 4);
        s3 = new Snake(x, y, 4);
        grid = new SquareGrid(10, s);
        grid2 = new SquareGrid(10, s2);
        grid3 = new SquareGrid(10, s3);
        Player p = new Player(grid2, 0);
        Player p2 = new Player(grid3, 1);
        Scanner scan = new Scanner(System.in);
        
        grid.setApple(4, 6, true);
        grid2.setApple(4, 6, true);
        
        gArr = grid.toArr();
        printGrid(gArr);
        while(grid.isValid() && grid.checkBoard()){
            String str = scan.nextLine();
            if(str.equals("0"))
                break;
            else if(str.equals("w"))
                s.changeDirection("N");
            else if(str.equals("a"))
                s.changeDirection("W");
            else if(str.equals("s"))
                s.changeDirection("S");
            else
                s.changeDirection("E");
            s.move();
            gArr = grid.toArr();
            printGrid(gArr);
        }
        System.out.println("\ngame over\n\n");
        
        System.out.println("AI 0's turn");
        scan.nextLine();
        int numTurns = 0;
        while(grid2.isValid() && grid2.checkBoard()){
            p.makeAMove();
            gArr = grid2.toArr();
            printGrid(gArr);
            if(numTurns > 20)
                break;
        }
        System.out.println("\ngame over\n\n");
        
        System.out.println("AI 1's turn");
        scan.nextLine();
        numTurns = 0;
        while(grid3.isValid() && grid3.checkBoard()){
            p2.makeAMove();
            gArr = grid3.toArr();
            printGrid(gArr);
            if(numTurns > 20)
                System.out.println("AI won!");
                break;
        }
        System.out.println("\ngame over\n\n");
    }
}
