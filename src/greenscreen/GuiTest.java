/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greenscreen;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author BestDark
 */
public class GuiTest extends JFrame {
    
    String path;
    
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                GuiTest SelectIt = new GuiTest();
            }
        });
    }
    
    public GuiTest(){
        super("Convert Video to Hologram");
        setVisible(true);
        setSize(450,300);
        setLocation(getWidth(), getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        getContentPane().setLayout(null);
        
        JButton btnButton = new JButton("Choose video mp4");
        btnButton.setBounds(160, 94, 120, 25);
        btnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileOpen = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter(".mp4", "mp4");
                fileOpen.addChoosableFileFilter(filter);
                
                int ret = fileOpen.showDialog(null, "Select");
                
                if(ret == JFileChooser.APPROVE_OPTION){
                    path = fileOpen.getSelectedFile().toString();
                }
                System.out.println(path);
            }
        });
        getContentPane().add(btnButton);
    }
}

