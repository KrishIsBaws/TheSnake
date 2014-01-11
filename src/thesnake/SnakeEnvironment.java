/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thesnake;

import environment.Environment;
import environment.Grid;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Achyuth1998
 */
class SnakeEnvironment extends Environment {
   private Grid grid;
   private int score = 20000;
   private Snake snake;
   
   private int speed = 5;
   private int moveCounter = speed;

    public SnakeEnvironment() {
    }

    @Override
    public void initializeEnvironment() {
        this.setGrid(new Grid());
        this.getGrid().setColor(Color.blue);
        this.getGrid().setColumns(40);
        this.getGrid().setRows(20);
        this.getGrid().setPosition(new Point(50, 100));
        
        this.setSnake(new Snake());
        this.getSnake().getBody().add(new Point(5, 5));
        this.getSnake().getBody().add(new Point(5, 6));
        this.getSnake().getBody().add(new Point(5, 7));
        this.getSnake().getBody().add(new Point(5, 8));
        this.getSnake().getBody().add(new Point(6, 8));
   }

    @Override
    public void timerTaskHandler() {
        System.out.println("use your keys");
        if (snake != null) {
            snake.move();
            if (moveCounter <= 0){
                snake.move();
                moveCounter = speed;
            } else {
                moveCounter--;
            }
        }
    }
    
    private void checkHeadPosition(){
        if (snake.getHead().x < 0) {
            snake.getHead().x = grid.getColumns() - 1;
        }
        if (snake.getHead().y > grid.getColumns() - 1) {
            snake.getHead().y = 0;
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            this.score += 1; 
        }else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            getSnake().setDirection(Direction.RIGHT);
            getSnake().move();
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            getSnake().setDirection(Direction.LEFT);
            getSnake().move();
        }else if (e.getKeyCode() == KeyEvent.VK_UP){
            getSnake().setDirection(Direction.UP);
            getSnake().move();
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            getSnake().setDirection(Direction.DOWN);   
            getSnake().move();
        }
    }
        

    @Override
    public void keyReleasedHandler(KeyEvent e) {
         
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (this.getGrid() != null) {
            this.getGrid().paintComponent(graphics);
//            graphics.drawRect(50, 100, 80, 80);  
            
            
            Point cellLocation;
            graphics.setColor(Color.orange);
            if (getSnake() != null){
                for (int i = 0; i < getSnake().getBody().size(); i++) {
                    cellLocation = getGrid().getCellPosition(getSnake().getBody().get(i));
                    graphics.fillRect(cellLocation.x, cellLocation.y, getGrid().getCellWidth(), getGrid().getCellHeight());
                }
        }
        }
        graphics.setFont(new Font("Calibri", Font.ITALIC, 50));
        graphics.drawString("Score: " + this.score, 50, 50);
        
        graphics.setFont(new Font("Comic Sans MS", Font.BOLD, 72));
        graphics.drawString("The Snake Game ", 250, 75);
    }

    /**
     * @return the snake
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * @param snake the snake to set
     */
    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    /**
     * @return the grid
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * @param grid the grid to set
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}
