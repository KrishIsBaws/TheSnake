/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thesnake;

import environment.ApplicationStarter;
import environment.Environment;
import image.ResourceTools;

/**
 *
 * @author Achyuth1998
 */
public class TheSnake {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        start();
    }

    private static void start() {
        Environment myGame = new SnakeEnvironment();
        myGame.setBackground(ResourceTools.loadImageFromResource("resources/iron man .jpeg"));
        
        ApplicationStarter.run("TheSnakeGame", myGame);
    }
}
