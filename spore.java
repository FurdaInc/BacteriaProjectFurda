package furda.inc;

import javax.swing.*;
import java.awt.*;

public class spore extends JPanel {
    private int xSquare, ySquare;
    private int squareSize = 10;

    public spore(Rectangle bounds){
        this.setLayout(null);

        this.setBounds(bounds);
        xSquare = this.getWidth()/2;
        ySquare = this.getHeight()/2;
        this.setOpaque(true);
    }


    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //g2.clearRect(0, 0, this.getWidth(), this.getHeight());
        g2.setColor(Color.red);
        g2.fillRect(xSquare, ySquare, squareSize, squareSize);
    }


}
