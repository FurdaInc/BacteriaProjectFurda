package furda.inc;

import javax.swing.*;
import java.awt.*;

public class backgroundPanel extends JComponent {
    private Image background;
    public backgroundPanel(Image background){
        this.background = background;
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
        /**/

    }

}
