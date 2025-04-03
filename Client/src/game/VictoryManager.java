package game;

import java.awt.Color;
import java.awt.Graphics;

import display.Display;
import gfx.Text;

public class VictoryManager {
    
    private static final Color victoryBackgroundColor = new Color(0, 0, 100, 150);
    private boolean win=false;

    public boolean checkVictory(String hitInfo) {
        int DestroyedShipsCounter = 0;
        if (hitInfo.contains("DESTROYED_SHIP")) {
            System.out.println("DISTRUTUW"); 
            DestroyedShipsCounter++;
        }

        if (DestroyedShipsCounter == 9) {
            win = true;
            return true;
        }
            
        return false;
    }
    


    public void renderVictoryScreen(Graphics g) {
        if (win) {
            g.setColor(victoryBackgroundColor);
            g.fillRect(0, 0, Display.resolution.height, Display.resolution.height);
            Text.drawString(g, "Victory!", Display.resolution.width / 2, Display.resolution.height / 2, true, Color.WHITE, Game.assets.font50);
        }
    }

    public boolean isWin() {
        return win;
    }
}
    
    
    
    


    
