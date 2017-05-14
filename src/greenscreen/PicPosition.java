/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greenscreen;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 *
 * @author BestDark
 */
public class PicPosition {
    int width = 360;
    int height = 244;

    public PicPosition(){
        try{
        BufferedImage imageUp = ImageIO.read(new File("C:/Users/BestDark/Desktop/a.jpg"));
        BufferedImage imageDown = ImageIO.read(new File("C:/Users/BestDark/Desktop/b.jpg"));
        BufferedImage imageBase = ImageIO.read(new File("C:/Users/BestDark/Desktop/base.PNG")); //1920*1020
        setPosition(imageUp, imageDown, imageBase);
        
        ImageIO.write(imageBase, "PNG", new File("C:/Users/BestDark/Desktop/output.PNG"));
        }catch (IOException e){
        
        }

    }
    
    public static void main(String[] args){
        new PicPosition();
    }
    
    public void RotateImage(BufferedImage imageLeft, BufferedImage imageRight){
    
    }
    
    public void setPosition(BufferedImage imageUp, BufferedImage imageDown, BufferedImage imageBase){
        Graphics2D g = imageBase.createGraphics();
        g.drawImage(imageUp, (imageBase.getWidth() - width) / 2, imageBase.getHeight() / 8, 360, 244, null);
        g.drawImage(imageDown, (imageBase.getWidth() - width) / 2, (imageBase.getHeight() * 5) / 8, 360, 244, null);/*
        g.drawImage(imageUp, (imageBase.getWidth() * 2 ) / 5, imageBase.getHeight() / 8, 360, 244, null);
        g.drawImage(imageDown, (imageBase.getWidth() * 2) / 5, (imageBase.getHeight() * 5) / 8, 360, 244, null);*/
        g.dispose();
    }
      /*      
    public void setPosition(BufferedImage imageUp, BufferedImage imageDown, BufferedImage imageBase){
        
        Graphics2D g = imageBase.createGraphics();
        g.drawImage(imageUp, (imageBase.getWidth() * 2 ) / 5, imageBase.getHeight() / 8, 360, 244, null);
        g.drawImage(imageDown, (imageBase.getWidth() * 2) / 5, (imageBase.getHeight() * 5) / 8, 360, 244, null);
        //left
        //g.drawImage(imageUp, (imageBase.getWidth() * 2 ) / 5, imageBase.getHeight() / 8, 360, 244, null);
        //right
        //g.drawImage(imageDown, (imageBase.getWidth() * 2) / 5, (imageBase.getHeight() * 5) / 8, 360, 244, null);
        g.dispose();
        
        
        
    }
    */
    
}
