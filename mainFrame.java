package furda.inc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class mainFrame extends JFrame {
    private static BufferedImage myImage;
    public mainFrame(){
        try {
            myImage = ImageIO.read(new File("C:\\Users\\Stein\\Pictures\\Saved Pictures\\Wallpapers\\Landscapes\\1523616787950.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setContentPane(new backgroundPanel(myImage));
        this.add(new spore(new Rectangle(0,0,1024,720)));


        //this.setSize(1024,720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        repaint();
        //this.setVisible(true);
    }
}
