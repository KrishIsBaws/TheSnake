/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thesnake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Achyuth1998
 */
public class Assignment extends javax.swing.JPanel {

    private Image myImage;
    
    /**
     * Creates new form Assignment
     */
    public Assignment() {
        initComponents();
        loadImage();
    }
    
     private void loadImage() {
        Image image = null;
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("resources/the-exorcist_73209.jpg");
            image = ImageIO.read(input);
        } catch (IOException e) {
        }

        this.myImage = image;
        
    }
    
    
    
    
    @Override
    public void paint(Graphics graphics){
//        graphics.setColor(new Color(50, 50, 250, 250));
//        graphics.fillOval(100, 150, 60, 60);
//        
//        graphics.setColor(new Color(50, 50, 100, 200));
//        graphics.fillOval(100, 200, 60, 60);
//        
//        graphics.setColor(new Color(75, 75, 150, 200));
//        graphics.fillOval(150, 175, 60, 60);
//        
//        graphics.setColor(new Color(90, 90, 150, 200));
//        graphics.fillOval(195, 175, 200, 200);
//        graphics.fillOval(250, 175, 200, 200);
//       
//        graphics.setColor(new Color(75, 75,150, 200));
//        graphics.fillOval(300, 200, 100, 100);
//        
//        graphics.setColor(Color.GRAY);
//        graphics.fillOval(500, 175, 200, 200);
//        
//        graphics.setColor(Color.WHITE);
//        graphics.fillOval(525, 200, 150, 150);
// 
//        graphics.drawImage(myImage, 800, 50, 600, 600, this);
//        
//        graphics.setColor(Color.ORANGE);
//        graphics.setFont(new Font("Arial", Font.ITALIC, 72));
//        graphics.drawString("Apple will make your dreams come true", 75, 150);
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}