package graphics;

import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;

public class Text {
    

    public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color c, Font font){
        g.setColor(c);
        g.setFont(font);
        int x = xPos;
        int y = yPos;

        if(center){
            FontMetrics fn = g.getFontMetrics(font); 
            x = xPos - fn.stringWidth(text) / 2;
            y = (yPos - fn.getHeight() / 2) + fn.getAscent();
        }
        g.drawString(text, x, y);
    }
}
