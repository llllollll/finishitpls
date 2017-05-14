/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greenscreen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IVideoPictureEvent;
import com.xuggle.xuggler.Global;

/**
 *
 * @author BestDark
 */
public class VideoCapture {  
    
    public static final double SECONDS_BETWEEN_FRAMES = 10;
    
    private static final String inputFilename = "C:/Fraps/Movies/Paladins 2017-04-08 10-28-01-68.avi";
    private static final String outputFilePrefix = "C:/Users/Game/Desktop/Programming/TempBestProject/Output";
    
    private static int mVideoStreamIndex = -1;
    
    private static long mLastPtsWrite = Global.NO_PTS;
    public static final long MICRO_SECONDS_BETWEEN_FRAMES = (long)(Global.DEFAULT_PTS_PER_SECOND * SECONDS_BETWEEN_FRAMES);
    
    public VideoCapture(){
        
    }
    
    public static void main(String[] args) throws IOException{
        IMediaReader mediaReader = ToolFactory.makeReader(inputFilename);
        mediaReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);
        mediaReader.addListener(new ImageSnapListener());
        while (mediaReader.readPacket() == null);
    }
    
    private static class ImageSnapListener extends MediaListenerAdapter{
        
        public void onVideoPicture(IVideoPictureEvent event){
            if(event.getStreamIndex() != mVideoStreamIndex){
                if(mVideoStreamIndex == -1){
                    mVideoStreamIndex = event.getStreamIndex();
                }else{
                    return;
                }
            }
            
            if(mLastPtsWrite == Global.NO_PTS){
                mLastPtsWrite = event.getTimeStamp() - MICRO_SECONDS_BETWEEN_FRAMES;
            }
            
            if(event.getTimeStamp() - mLastPtsWrite >= MICRO_SECONDS_BETWEEN_FRAMES){
                String outputFilename = dumpImageToFile(event.getImage());
                
                double seconds = ((double) event.getTimeStamp()) / Global.DEFAULT_PTS_PER_SECOND;
                System.out.printf("Elapsed time of %6.3f seconds wrote: %s \n", seconds, outputFilename);  
                mLastPtsWrite += MICRO_SECONDS_BETWEEN_FRAMES;
            }
        }
        
        private String dumpImageToFile(BufferedImage image){
            try{
                String outputFilename = outputFilePrefix + System.currentTimeMillis() + ".png";
                ImageIO.write(image, "png", new File(outputFilename));
                return outputFilename;
            }catch(IOException e){
                e.printStackTrace();
                return null;
            }
        }
        
    }
}
