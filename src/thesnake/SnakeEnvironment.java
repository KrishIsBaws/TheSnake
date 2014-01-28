/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thesnake;

import audio.AudioPlayer;
import environment.Environment;
import environment.GraphicsPalette;
import environment.Grid;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Achyuth1998
 */
class SnakeEnvironment extends Environment {

    private Grid grid;
    private int score = 0;
    private Snake snake;
    private int speed = 5;
    private int moveCounter = speed;
    private ArrayList<Point> apples;
    private ArrayList<Point> poisonapple;
    private ArrayList<Point> diamond;

    public SnakeEnvironment() {
        AudioPlayer.play("/Sound/ObstacleCourse.wav");
        AudioPlayer.play("/Sound/ObstacleCourse.wav");
        AudioPlayer.play("/Sound/ObstacleCourse.wav");
        AudioPlayer.play("/Sound/ObstacleCourse.wav");
        AudioPlayer.play("/Sound/ObstacleCourse.wav");

        

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




        this.apples = new ArrayList<Point>();
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
        this.apples.add(getRandomGridLocation());
       


        this.poisonapple = new ArrayList<Point>();
        this.poisonapple.add(getRandomGridLocation());
        this.poisonapple.add(getRandomGridLocation());
        this.poisonapple.add(getRandomGridLocation());
        this.poisonapple.add(getRandomGridLocation());
        this.poisonapple.add(getRandomGridLocation());
        this.poisonapple.add(getRandomGridLocation());
        this.poisonapple.add(getRandomGridLocation());
        this.poisonapple.add(getRandomGridLocation());
        this.poisonapple.add(getRandomGridLocation());
        this.poisonapple.add(getRandomGridLocation());
        this.poisonapple.add(getRandomGridLocation());
        this.poisonapple.add(getRandomGridLocation());
        
        
        
        




        this.diamond = new ArrayList<Point>();
        this.diamond.add(getRandomGridLocation());

    }

    private Point getRandomGridLocation() {
        return new Point((int) (Math.random() * this.grid.getColumns()), (int) (Math.random() * this.grid.getRows()));

    }

    @Override
    public void timerTaskHandler() {
//        System.out.println("use your keys");
        if (snake != null) {
            if (moveCounter <= 0) {
                snake.move();
                moveCounter = speed;
                speed = 0;
                checkSnakeIntersection();
            } else {
                moveCounter--;
            }

            if (snake.getDirection() == Direction.RIGHT) {
                if (snake.getHead().x >= this.grid.getColumns()) {
                    snake.getHead().x = 0;
                }
            } else if (snake.getDirection() == Direction.LEFT) {
                if (snake.getHead().x <= -1) {
                    snake.getHead().x = this.grid.getColumns() - 1;
                }
            } else if (snake.getDirection() == Direction.UP) {
                if (snake.getHead().y <= -1) {
                    snake.getHead().y = this.grid.getRows() - 1;
                }
            } else if (snake.getDirection() == Direction.DOWN) {
                if (snake.getHead().y >= this.grid.getRows()) {
                    snake.getHead().y = 0;
                }
            }

        }

    }

    private void checkSnakeIntersection() {
        // if the snake location is the same as an apple
        //then grow the snake and remove the apple
        // later, move the apple and make a sound and increase the score
//        System.out.println("Checking");


        for (int i = 0; i < this.apples.size(); i++) {
            if (snake.getHead().equals(this.apples.get(i))) {
                System.out.println("APPLE CHOMP");
                AudioPlayer.play("/Sound/crunching-1.wav");
                this.snake.grow(1);
                this.score += 1;
                this.speed -= 2;
                this.apples.get(i).x = (int) (Math.random() * this.grid.getColumns());
                this.apples.get(i).y = (int) (Math.random() * this.grid.getRows());
            }
        }

        for (int i = 0; i < this.poisonapple.size(); i++) {
            if (snake.getHead().equals(this.poisonapple.get(i))) {
                System.out.println("BOOOOOM");
                this.score -= 5;        
                AudioPlayer.play("/Sound/bomb.wav");
                JOptionPane.showMessageDialog(null, "BOOM!! Great job!!");
                this.poisonapple.get(i).x = (int) (Math.random() * this.grid.getColumns());
                this.poisonapple.get(i).y = (int) (Math.random() * this.grid.getRows());
            }
        }
        for (int i = 0; i < this.diamond.size(); i++) {
            if (snake.getHead().equals(this.diamond.get(i))) {
                System.out.println("OOHH DIAMONDS");
                this.snake.grow(2);
                this.score += 10;
                this.speed -= 2;
                AudioPlayer.play("/Sound/magic-chime-02.wav");
//                JOptionPane.showMessageDialog(null, "OOHH");
                this.diamond.get(i).x = (int) (Math.random() * this.grid.getColumns());
                this.diamond.get(i).y = (int) (Math.random() * this.grid.getRows());
            }
        }

    }

    private void checkHeadPosition() {
        if (snake.getHead().x < 0) {
            snake.getHead().x = grid.getColumns() - 1;
        }
        if (snake.getHead().y > grid.getColumns() - 1) {
            snake.getHead().y = 0;
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.score += 10;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            getSnake().setDirection(Direction.RIGHT);
            getSnake().move();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            getSnake().setDirection(Direction.LEFT);
            getSnake().move();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            getSnake().setDirection(Direction.UP);
            getSnake().move();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
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

            if (this.apples != null) {
                for (int i = 0; i < this.apples.size(); i++) {
                    GraphicsPalette.drawApple(graphics, this.grid.getCellPosition(this.apples.get(i)), this.grid.getCellSize());
                }
            }

            if (this.poisonapple != null) {
                for (int i = 0; i < this.poisonapple.size(); i++) {
                    GraphicsPalette.drawBomb(graphics, this.grid.getCellPosition(this.poisonapple.get(i)), this.grid.getCellSize(), Color.BLACK);
                }
            }
            if (this.diamond != null) {
                for (int i = 0; i < this.diamond.size(); i++) {
                    GraphicsPalette.drawDiamond(graphics, this.grid.getCellPosition(this.diamond.get(i)), new Point(this.grid.getCellSize()), Color.BLUE);
                }
            }

            Point cellLocation;
            graphics.setColor(Color.BLUE);
            if (getSnake() != null) {
                for (int i = 0; i < getSnake().getBody().size(); i++) {
                    cellLocation = getGrid().getCellPosition(getSnake().getBody().get(i));
                    graphics.fillRect(cellLocation.x, cellLocation.y, getGrid().getCellWidth(), getGrid().getCellHeight());


                }

            }
        }


        GraphicsPalette.enterPortal(graphics, new Point(500, 100), new Point(this.grid.getCellSize()), Color.yellow);
        GraphicsPalette.leavePortal(graphics, new Point(200, 200), new Point(this.grid.getCellSize()), Color.yellow);



        graphics.setColor(Color.BLUE);
        graphics.setFont(new Font("Calibri", Font.ITALIC, 50));
        graphics.drawString("Score: " + this.score, 50, 50);

        graphics.setFont(new Font("Comic Sans MS", Font.BOLD, 72));
        graphics.drawString("Snake Run ", 270, 75);
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
