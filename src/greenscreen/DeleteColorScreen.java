/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greenscreen;

import marvin.MarvinPluginCollection;
import marvin.io.MarvinImageIO;
import marvin.image.MarvinImage;
import marvin.color.MarvinColorModelConverter;
/**
 *
 * @author BestDark
 */
public class DeleteColorScreen {
    // url = infront type of picture
    // type = behide location (type of pic)
    // url ---> D:/name type ---> .jpg
    // public DeleteColorScreen(String url, String type)
    public DeleteColorScreen(){
        // MarvinImage image = MarvinImageIO.loadImage(url + type);
        MarvinImage imageIn = MarvinImageIO.loadImage("C:\\Users\\BestDark\\Desktop\\a.jpg");
        MarvinImage imageOut = new MarvinImage(imageIn.getWidth(), imageIn.getHeight());
        
        RemoveColorScreen(imageIn, imageOut);
        // MarvinImageIO.saveImage(imageOut, url + type)
        MarvinImageIO.saveImage(imageOut, "C:\\Users\\BestDark\\Desktop\\a.png");
    }
    
    // call DeleteColorScreen ---> DeleteColorScreen(url)
    public static void main(String[] args) {
        new DeleteColorScreen();
    }
    
    private void RemoveColorScreen(MarvinImage imageIn, MarvinImage imageOut){
        for(int y = 0; y < imageIn.getHeight(); y++){
            for(int x = 0; x < imageIn.getWidth(); x++){
                
                int color = imageIn.getIntColor(x, y);
                int r = imageIn.getIntComponent0(x, y);
                int g = imageIn.getIntComponent1(x, y);
                int b = imageIn.getIntComponent2(x, y);
                
                double[] hsv = MarvinColorModelConverter.rgbToHsv(new int[] {color});
                
                if(hsv[0] >= 60 && hsv[0] <= 130 && hsv[1] >= 0.4 && hsv[2] >= 0.3){
                    imageOut.setIntColor(x, y, 0, 127, 127, 127);
                }else{
                    imageOut.setIntColor(x, y, color);
                }
            }
        }
        ReduceColorScreen(imageOut);
    }
    
    private void ReduceColorScreen(MarvinImage imageOut){
        for(int y = 0; y < imageOut.getHeight(); y++){
            for(int x = 0; x < imageOut.getWidth(); x++){
                
                int r = imageOut.getIntComponent0(x, y);
                int g = imageOut.getIntComponent1(x, y);
                int b = imageOut.getIntComponent2(x, y);
                int color = imageOut.getIntColor(x, y);
                
                double[] hsv = MarvinColorModelConverter.rgbToHsv(new int[] {color});
                
                if(hsv[0] >= 60 && hsv[0] <= 130 && hsv[1] >= 0.15 && hsv[2] > 0.15){
                    if((r*b != 0 && (g*g)/(r*b) >= 1.5 )){
                        imageOut.setIntColor(x, y, 255, (int)(r*1.4), (int)g, (int)(b*1.4));
                    }else{
                        imageOut.setIntColor(x, y, 255, (int)(r*1.2), g, (int)(b*1.2));
                    }
                }
            }
        }
        MarvinPluginCollection.alphaBoundary(imageOut, 6);
    }
    
}
