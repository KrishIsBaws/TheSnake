/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thesnake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static thesnake.Direction.UP;

/**
 *
 * @author Achyuth1998
 */
public class Snake {

    private ArrayList<Point> body;
    private Direction direction = Direction.RIGHT;
    private int growthCounter;
      
    {
        body = new ArrayList<>();
    }

    public void move() {
        //create a new location for the head, using the direction
        int x = 0;
        int y = 0;

        switch (getDirection()) {
            case UP:
                x = 0;
                y = -1;
                break;

            case DOWN:
                x = 0;
                y = 1;
                break;

            case LEFT:
                x = -1;
                y = 0;
                break;

            case RIGHT:
                x = 1;
                y = 0;
                break;
        }

        body.add(0, new Point(getHead().x + x, getHead().y + y));
        //delete tail
        if (growthCounter > 0) {
            growthCounter--;
            
        } else {
            body.remove(body.size() - 1);
        }
        
    } 

    public Point getHead() {
        return body.get(0);
    }

    /**
     * @return the body
     */
    public ArrayList<Point> getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(ArrayList<Point> body) {
        this.body = body;
    }

    /**
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    
       private static void showAssignment() {
//        MyFrame window = new MyFrame();
//        window.setTitle("My Assignment");
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setLayout(new BorderLayout());
//
//        Assignment myAssignment = new Assignment();
//        myAssignment.setBackground(Color.WHITE);
//        myAssignment.setVisible(true);
//
//        window.add(myAssignment, BorderLayout.CENTER);
//        window.setVisible(true);

        JPanel panel = new JPanel();
        ImageIcon icon = new ImageIcon("image.jpg");
        JLabel label = new JLabel();
        label.setIcon(icon);
        panel.add(label);


    }

    /**
     * @return the growthCounter
     */
    public int getGrowthCounter() {
        return growthCounter;
    }

    /**
     * @param growthCounter the growthCounter to set
     */
    public void setGrowthCounter(int growthCounter) {
        this.growthCounter = growthCounter;
    }
    /**
     * @param growthCounter the growthCounter to set
     */
    public void grow(int growth) {
        this.growthCounter += growth;
    }

    void getHead(Color BLACK) {
            }



  

    
}
