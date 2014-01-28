/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thesnake;

import environment.ApplicationStarter;
import environment.Environment;
import image.ResourceTools;
import javax.swing.JOptionPane;

/**
 *
 * @author Achyuth1998
 */
public class TheSnake {
      private static String String;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      
        start();
        
        saySomething("asda");
    }

    private static void start() {
        Environment myGame = new SnakeEnvironment();
        myGame.setBackground(ResourceTools.loadImageFromResource("resources/Yellow.jpg"));
        
        ApplicationStarter.run("TheSnakeGame", myGame);
    }

    private static void saySomething(String asda) {
          JOptionPane.showMessageDialog(null, "Welcome to snake run");
          JOptionPane.showMessageDialog(null, "To Play this game, you must use the arrow keys to navigate where the snake goes...");
          JOptionPane.showMessageDialog(null, "Try to eat the apples and diamonds while avoiding the Bombs or you will get an annoying message..");
          JOptionPane.showMessageDialog(null, "The aim is to get the highest score possible without using the SECERET cheat code");
          JOptionPane.showMessageDialog(null, "Good Luck!");
        }
}
