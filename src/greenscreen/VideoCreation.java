/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greenscreen;

import java.awt.AWTException;
import java.awt.Dimension;
//import java.awt.Rectangle;
//import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;

import java.io.IOException;
/**
 *
 * @author BestDark
 */
public class VideoCreation {
    
    public static int indexVideo = 1;
    private static final double FRAME_RATE = 50;
    private static final int SECOND_TO_RUN_FOR = 20;
    private static final String outputFilename = "C:/Users/BestDark/Desktop/Test.mp4";
    private static Dimension screenBounds;
    /*
    public static void main(String[] args){
        
        final IMediaWriter writer = ToolFactory.makeWriter(outputFilename);
        screenBounds = Toolkit.getDefaultToolkit().getScreenSize();
        writer.addAudioStream(0, 0, ICodec.ID.CODEC_ID_MPEG4, screenBounds.width/2, screenBounds.height/2);
        long startTime = System.nanoTime();
        
        for(int index = 0; index < 15; index++){
        //for(int index = 0; index < SECOND_TO_RUN_FOR*FRAME_RATE; index++){
            //BufferedImage screen = getDesktopScreenShot();
            //BufferedImage bgrScreen = convertToType(screen,BufferedImage.TYPE_3BYTE_BGR);
            BufferedImage bgrScreen = getVideoImage();
            bgrScreen = convertToType(bgrScreen, BufferedImage.TYPE_3BYTE_BGR);
            writer.encodeVideo(0, bgrScreen, System.nanoTime()-startTime, TimeUnit.NANOSECONDS);
            
            try{
                //Thread.sleep((long)(1000/FRAME_RATE));
                Thread.sleep((long)(100));
            }catch(InterruptedException e){
            
            }     
        }
        writer.close();
    }
    
    public static BufferedImage convertToType(BufferedImage sourceImage, int targetType){
               
        BufferedImage image;
        
        
        if(sourceImage.getType() == targetType){
            image = sourceImage;
        }else{
            image = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), targetType);
            image.getGraphics().drawImage(sourceImage, 0, 0, null);
        }

        return image;
    }
    
    private static BufferedImage getVideoImage(){
        
        File imgLoc = new File("C:/Users/BestDark/Desktop/" + indexVideo + ".png");
        BufferedImage image = null;
        try{
            image = ImageIO.read(imgLoc);
            //Robot robot = new Robot();
            //Rectangle captureSize = new Rectangle(screenBounds);
            
            //return robot.createScreenCapture(captureSize);
            
        //}catch(AWTException e){
        }catch(IOException e){
            e.printStackTrace();           
        }
        indexVideo++;
        return image;
    }*/
    public static void main(String[] args) throws IOException{
        final IMediaWriter writer = ToolFactory.makeWriter(outputFilename);
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4, 
               720, 304);
        long nextFrameTime = 0;
        final long frameRate =25/1000;
        long startTime = System.nanoTime();
        while (indexVideo<5) {
            BufferedImage videoImage = null;
                try {
                    videoImage = getVideoImage();
                } catch (AWTException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            writer.encodeVideo(0, videoImage,nextFrameTime, 
                TimeUnit.MILLISECONDS);
            nextFrameTime += frameRate;
        }
        writer.close();
    }

    private static BufferedImage getVideoImage() throws IOException, AWTException {

         File imgLoc = new File("C:/Users/Bestdark/Desktop/video"+indexVideo+".jpg");
         BufferedImage img;
        img = ImageIO.read(imgLoc);
        System.out.println(imgLoc.getName());
        indexVideo++;
        return img;

    }
}
