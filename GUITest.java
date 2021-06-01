import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.input.*;
import javafx.stage.*;
import java.util.ArrayList;
import javafx.scene.transform.*;
public class GUITest extends Application
{
    Group root;
    Snake s;
    SquareGrid grid;
    String[][] gArr;
    int[] x = {0, 1, 2, 3};
    int[] y = {4, 4, 4, 4};
    int width = 500;
    int height = 500;
    ArrayList<Shape> snake = new ArrayList<Shape>();
    ArrayList<Shape> squareObjs = new ArrayList<Shape>();

    public void start(Stage primaryStage){
        root = new Group();
        s = new Snake(x, y, 4);
        grid = new SquareGrid(10, s);
        
        grid.setApple(6, 4, true);
        
        //s.getHead().setDirection("N");
        
        displayGrid();
        displaySnake();
        
        Scene scene = new Scene(root, width, height, Color.WHITE);
        
        scene.setOnKeyPressed(this::processKeyPress);
        
        primaryStage.setTitle("squares");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void displayGrid(){
        Rectangle[][] gridRectangles = grid.display(width);
        String[][] arr = grid.toArr();
        for(int i = 0; i < snake.size(); i++){
            root.getChildren().remove(squareObjs.get(i));
        }
        for(int row = 0; row < gridRectangles.length; row++){
            for(int col = 0; col < gridRectangles[0].length; col++){
                root.getChildren().add(gridRectangles[row][col]);
            }
        }
        for(int row = 0; row < gridRectangles.length; row++){
            for(int col = 0; col < gridRectangles[0].length; col++){
                Rectangle r = gridRectangles[row][col];
                double rX = r.getX();
                double rY = r.getY();
                int gridSize = grid.getSize();
                Shape sq = grid.getSquare(col, row).display(rX, rY, width/gridSize, width/(gridSize*2) - 10);
                if(sq != null)
                    root.getChildren().add(sq);  
            }
        }
    }
    
    public void displaySnake(){
        Rectangle[][] gridRectangles = grid.display(width);
        String[][] arr = grid.toArr();
        for(int i = 0; i < snake.size(); i++){
            root.getChildren().remove(snake.get(i));
        }
        for(int row = 0; row < gridRectangles.length; row++){
            for(int col = 0; col < gridRectangles[0].length; col++){
                
                Rectangle r = gridRectangles[row][col];
                double rX = r.getX();
                double rY = r.getY();
                int gridSize = grid.getSize();
                
                if(s.getPart(col, row) != null){
                    Rectangle p = s.getPart(col, row).display(rX + 5, rY + 10, width/gridSize - 10, height/gridSize - 20);
                    snake.add(p);
                    root.getChildren().add(p);
                    
                    if(s.getPart(col, row).getHead()){
                        double pX = p.getX();
                        double pY = p.getY();
                        double pW = p.getWidth();
                        double pH = p.getHeight();
                        Circle eye = new Circle(pX + pW - 10, pY + 10, 5);
                        if(s.getHead().getDirection().equals("N") || s.getHead().getDirection().equals("S")){
                            // r.getTransforms().add(new Rotate(90, x, y + width));
                        }
                        snake.add(eye);
                        root.getChildren().add(eye);
                    } 
                }
                
            }
        }
    }
    
    public void processKeyPress(KeyEvent event){
        if(event.getCode() == KeyCode.W){
            s.changeDirection("N");
        }else if(event.getCode() == KeyCode.A){
            s.changeDirection("W");
        }else if(event.getCode() == KeyCode.S){
            s.changeDirection("S");
        }else if(event.getCode() == KeyCode.N){
            s.changeDirection("E");
        }
        s.move();
        displaySnake();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
