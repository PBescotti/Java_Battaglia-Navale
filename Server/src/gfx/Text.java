package gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;


/*
    Serve a scrivere del testo a schermo
*/
public class Text {

	public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color c, Font font){
		g.setColor(c);
		g.setFont(font);
		int x = xPos;
		int y = yPos;

        String[] lines = text.split("\n");  //Nel caso ci sia il carattere di a capo divide la frase per ogni carattere di a capo e la scrive di conseguenza

        for (String line : lines) {
            if (center) {
                FontMetrics fm = g.getFontMetrics(font);        //FontMetrics serve per prendere le misure della frase e centrarla correttamente
                x = xPos - fm.stringWidth(line) / 2;
                y = yPos - fm.getHeight() / 2 + fm.getAscent();
            }
            g.drawString(line, x, y);
            yPos += g.getFontMetrics(font).getHeight();
        }

		
	}

}
