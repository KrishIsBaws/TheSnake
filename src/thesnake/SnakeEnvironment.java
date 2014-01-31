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
    private int speed = 5;
    private int moveCounter = speed;
    private ArrayList<Point> apples;
    private ArrayList<Point> poisonapple;
    private ArrayList<Point> diamond;
    private ArrayList<Point> lightning;
    private boolean drawPicture = false;
    private Image exorcist;

    public SnakeEnvironment() {
        AudioPlayer.play("/Sound/ObstacleCourse.wav");
    }

    @Override
    public void initializeEnvironment() {
        this.exorcist = ResourceTools.loadImageFromResource("resources/exorcist2.jpg");

//        this.lightning = ResourceTools.loadImageFromResource("resources/the fist of difference.jpg");

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
        for (int i = 0; i < 13; i++) {
            this.apples.add(getRandomGridLocation());
        }

        this.poisonapple = new ArrayList<Point>();
        for (int i = 0; i < 7; i++) {
            this.poisonapple.add(getRandomGridLocation());
        }


        this.diamond = new ArrayList<Point>();
        this.diamond.add(getRandomGridLocation());

        this.lightning = new ArrayList<Point>();
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
                this.setScore(this.getScore() + 1);
                this.speed -= 2;
                this.apples.get(i).x = (int) (Math.random() * this.grid.getColumns());
                this.apples.get(i).y = (int) (Math.random() * this.grid.getRows());
            }
        }
        for (int i = 0; i < this.lightning.size(); i++) {
            if (snake.getHead().equals(this.lightning.get(i))) {
                System.out.println("FASTER");
                AudioPlayer.play("/Sound/chime.wav");
                this.speed -= 30;
                this.lightning.get(i).x = (int) (Math.random() * this.grid.getColumns());
                this.lightning.get(i).y = (int) (Math.random() * this.grid.getRows());
            }
        }
        for (int i = 0; i < this.poisonapple.size(); i++) {
            if (snake.getHead().equals(this.poisonapple.get(i))) {
                System.out.println("BOOOOOM");
                this.setScore(this.getScore() - 5);
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
                this.setScore(this.getScore() + 10);
                this.speed -= 2;
                AudioPlayer.play("/Sound/chime.wav");
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
        if (e.getKeyCode() == KeyEvent.VK_P) {
            if (state == GameState.RUNNING) {
               state = GameState.PAUSED;
            } else if (state == GameState.PAUSED) {
                state  = GameState.RUNNING;
            }
        }
        
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.setScore(this.getScore() + 10);
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
                    GraphicsPalette.drawDiamond(graphics, this.grid.getCellPosition(this.diamond.get(i)), this.grid.getCellSize(), Color.BLUE);

                }
            }
            if (this.lightning != null) {
                for (int i = 0; i < this.lightning.size(); i++) {
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


        GraphicsPalette.enterPortal(graphics, new Point(300, 50), new Point(this.grid.getCellSize()), Color.yellow);
        GraphicsPalette.leavePortal(graphics, new Point(650, 50), new Point(this.grid.getCellSize()), Color.yellow);



        graphics.setColor(Color.BLUE);
        graphics.setFont(new Font("Mistral", Font.ITALIC, 50));
        graphics.drawString("Score: " + this.getScore(), 125, 75);

        graphics.setFont(new Font("Mistral", Font.BOLD, 72));
        graphics.drawString("Snake Run ", 370, 75);

        if (drawPicture) {
            graphics.drawImage(exorcist, 150, 0, this);
        }
        if (state == GameState.ENDED) {
            graphics.setFont(new Font("Calibri", Font.ITALIC, 100));
            graphics.drawString("GAME OVER!!", 200, 300);
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
        if ((this.score < 60) && (score >= 60)) {
            System.out.println("WOOOOOOOOOOT");
            //show picture
            // play sound
            AudioPlayer.play("/Sound/screaming.wav");
            setState(GameState.ENDED);
            this.drawPicture = true;
        }
        this.score = score;
    }
}
