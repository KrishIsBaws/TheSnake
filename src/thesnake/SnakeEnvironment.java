/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thesnake;

import audio.AudioPlayer;
import environment.Environment;
import environment.GraphicsPalette;
import environment.Grid;
import image.ResourceTools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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

    private GameState state = GameState.PAUSED;
    private Grid grid;
    private int score = 0;
    private Snake snake;
    private int speed = -5;
    private int moveCounter = speed;
    private ArrayList<Point> apples;
    private ArrayList<Point> poisonapple;
    private ArrayList<Point> diamond;
    private ArrayList<Point> lightning;
    private boolean drawPicture = false;
    private Image exorcist;
    private Image dragonHead;
    private Image diamonds;
    private Image poison;
    private Image knight;
    private Image snakeskin;
    private Direction direction;

    public SnakeEnvironment() {
        AudioPlayer.play("/Sound/ObstacleCourse.wav");
    }

    @Override
    public void initializeEnvironment() {
        exorcist = ResourceTools.loadImageFromResource("resources/VillageBurn.jpg");
        dragonHead = ResourceTools.loadImageFromResource("resources/dragonhead.png");
        diamonds = ResourceTools.loadImageFromResource("resources/diamond.png");
        knight = ResourceTools.loadImageFromResource("resources/knighthead.png");
        poison = ResourceTools.loadImageFromResource("resources/Poison.png");
snakeskin = ResourceTools.loadImageFromResource("resources/wing.png");
this.direction = direction;



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
        for (int i = 0; i < 1; i++) {
            this.apples.add(getRandomGridLocation());
        }

        this.poisonapple = new ArrayList<Point>();
        for (int i = 0; i < 3; i++) {
            this.poisonapple.add(getRandomGridLocation());
        }

        this.diamond = new ArrayList<Point>();
        for (int i = 0; i < 2; i++) {
            this.diamond.add(getRandomGridLocation());

        }
        this.lightning = new ArrayList<Point>();
        this.lightning.add(getRandomGridLocation());
        this.lightning.add(getRandomGridLocation());









    }

    private Point getRandomGridLocation() {
        return new Point((int) (Math.random() * this.grid.getColumns()), (int) (Math.random() * this.grid.getRows()));

    }

    @Override
    public void timerTaskHandler() {
//        System.out.println("use your keys");
        if (getState() == GameState.RUNNING) {
            if (snake != null) {
                if (moveCounter <= 0) {
                    snake.move();
                    moveCounter = speed;
                    speed = -5;
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

    }

    private void checkSnakeIntersection() {
        // if the snake location is the same as an apple
        //then grow the snake and remove the apple
        // later, move the apple and make a sound and increase the score
//        System.out.println("Checking");


        for (int i = 0; i < this.apples.size(); i++) {
            if (snake.getHead().equals(this.apples.get(i))) {
                System.out.println("Yum Yum");
                AudioPlayer.play("/Sound/crunching-1.wav");
                this.snake.grow(1);
                this.setScore(this.getScore() + 2);
//                this.speed = 0;
                this.apples.get(i).x = (int) (Math.random() * this.grid.getColumns());
                this.apples.get(i).y = (int) (Math.random() * this.grid.getRows());
            }
        }

        for (int i = 0; i < this.poisonapple.size(); i++) {
            if (snake.getHead().equals(this.poisonapple.get(i))) {
                System.out.println("BOOOOOM");
//                this.setScore(this.getScore() - 5);
//                this.speed = 1;
                
                AudioPlayer.play("/Sound/bomb.wav");
                state = GameState.ENDED;
                this.poisonapple.get(i).x = (int) (Math.random() * this.grid.getColumns());
                this.poisonapple.get(i).y = (int) (Math.random() * this.grid.getRows());
            }
        }
        for (int i = 0; i < this.lightning.size(); i++) {
            if (snake.getHead().equals(this.lightning.get(i))) {
                this.snake.grow(4);
                AudioPlayer.play("/Sound/slurp.wav");
                this.poisonapple.get(i).x = (int) (Math.random() * this.grid.getColumns());
                this.poisonapple.get(i).y = (int) (Math.random() * this.grid.getRows());
            }
        }
        for (int i = 1; i < this.snake.getBody().size(); i++) {


            if (snake.getHead().equals(snake.getBody().get(i).getLocation())) {
                state = GameState.ENDED;
                JOptionPane.showMessageDialog(this, "Press Enter and Spacebar");



            }
        }

        for (int i = 0; i < this.diamond.size(); i++) {
            if (snake.getHead().equals(this.diamond.get(i))) {
                System.out.println("OOHH DIAMONDS");
                this.snake.grow(2);
                this.setScore(this.getScore() + 10);
                this.speed -= 2;
                AudioPlayer.play("/Sound/chime.wav");
                this.diamond.get(i).x = (int) (Math.random() * this.grid.getColumns());
                this.diamond.get(i).y = (int) (Math.random() * this.grid.getRows());
            }
        }
        for (int i = 0; i < this.lightning.size(); i++) {
            if (snake.getHead().equals(this.lightning.get(i))) {
                System.out.println("OOHH DIAMONDS");
                this.snake.grow(2);
                this.setScore(this.getScore() + 10);
                this.speed = 0;
                AudioPlayer.play("/Sound/chime.wav");
//                JOptionPane.showMessageDialog(null, "OOHH");
                this.lightning.get(i).x = (int) (Math.random() * this.grid.getColumns());
                this.lightning.get(i).y = (int) (Math.random() * this.grid.getRows());
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
        if (e.getKeyCode() == KeyEvent.VK_P) {
            if (state == GameState.RUNNING) {
                state = GameState.PAUSED;
            } else if (state == GameState.PAUSED) {
                state = GameState.RUNNING;
            }
        }
        int startAngle = 0;
        int arcAngle = 310;

        if (direction == Direction.RIGHT) {
            startAngle = 30;
        } else if (direction == Direction.UP) {
            startAngle = 120;
        } else if (direction == Direction.LEFT) {
            startAngle = 210;
        } else if (direction == Direction.DOWN) {
            startAngle = 300;
        }

        if (e.getKeyCode() == KeyEvent.VK_S) {
            this.setScore(this.getScore() + 500);
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
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            state = GameState.ENDED;
        } else if (e.getKeyCode() == KeyEvent.VK_G) {
            this.snake.grow(2);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            
               
               setState(GameState.RESTART);
               
            
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
                    graphics.drawImage(knight, this.grid.getCellPosition(this.apples.get(i)).x, this.grid.getCellPosition(this.apples.get(i)).y, getGrid().getCellWidth(), getGrid().getCellHeight(), this);
                }
            }

            if (this.poisonapple != null) {
                for (int i = 0; i < this.poisonapple.size(); i++) {
                    GraphicsPalette.drawBomb(graphics, this.grid.getCellPosition(this.poisonapple.get(i)), this.grid.getCellSize(), Color.BLACK);
                }
            }
            if (this.diamond != null) {
                for (int i = 0; i < this.diamond.size(); i++) {
                    graphics.drawImage(diamonds, this.grid.getCellPosition(this.diamond.get(i)).x, this.grid.getCellPosition(this.diamond.get(i)).y, getGrid().getCellWidth(), getGrid().getCellHeight(), this);

                }
            }
            if (this.lightning != null) {
                for (int i = 0; i < this.lightning.size(); i++) {
                    graphics.drawImage(poison, this.grid.getCellPosition(this.lightning.get(i)).x, this.grid.getCellPosition(this.lightning.get(i)).y, getGrid().getCellWidth(), getGrid().getCellHeight(), this);


                }
            }
//            

            Point cellLocation;
            graphics.setColor(Color.RED);
            if (getSnake() != null) {
                for (int i = 0; i < 1; i++) {
                    cellLocation = getGrid().getCellPosition(getSnake().getBody().get(i));
                    graphics.drawImage(dragonHead, cellLocation.x, cellLocation.y, getGrid().getCellWidth(), getGrid().getCellHeight(), this);
                }

                for (int i = 1; i < getSnake().getBody().size(); i++) {
                    cellLocation = getGrid().getCellPosition(getSnake().getBody().get(i));
                    graphics.drawImage(snakeskin, cellLocation.x, cellLocation.y, getGrid().getCellWidth(), getGrid().getCellHeight(), this);
                }

            }



            GraphicsPalette.enterPortal(graphics, new Point(300, 50), new Point(this.grid.getCellSize()), Color.yellow);
            GraphicsPalette.leavePortal(graphics, new Point(650, 50), new Point(this.grid.getCellSize()), Color.yellow);



            graphics.setColor(Color.BLUE);
            graphics.setFont(new Font("Mistral", Font.ITALIC, 50));
            graphics.drawString("Score: " + this.getScore(), 125, 75);

            graphics.setFont(new Font("Mistral", Font.BOLD, 72));
            graphics.drawString("Dragon Run ", 370, 75);

            if (drawPicture) {
                graphics.drawImage(exorcist, 0, 0, this);
            }

            if (state == GameState.ENDED) {
            graphics.setFont(new Font("Calibri", Font.ITALIC, 100));
            AudioPlayer.play("/Sound/applause-2.wav");
            setState(GameState.ENDED);
            this.drawPicture = true;
//            this.score = 0;
//            graphics.drawString(("Nice Job "), 80, 75);
            graphics.setColor(Color.red);
            graphics.setFont(new Font("Stencil", Font.BOLD, 100));
            graphics.drawString(("Score: " + this.getScore()), 110, 200);
//            JOptionPane.showMessageDialog(this, "YOU WIN!!! GREAT JOB");
            }
            
            if (state == GameState.RESTART) {
                this.score = 0;
                setState(GameState.RUNNING);
                this.drawPicture = false;
            }
            
            
            
 


        }
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

    /**
     * @return the state
     */
    public GameState getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(GameState state) {
        this.state = state;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        if ((this.score < 500) && (score >= 500)) {
            System.out.println("WOOOOOOOOOOT");
            //show picture
            // play sound
            setState(GameState.ENDED);
            this.drawPicture = true;
            JOptionPane.showMessageDialog(this, "YOU WIN!!! GREAT JOB");
        }
        this.score = score;
    }
    
    public int getSpeed(){
        return speed;
        
         
    }
    
    
    public void setSpeed(int speed){
        this.speed = speed;
    }
}
