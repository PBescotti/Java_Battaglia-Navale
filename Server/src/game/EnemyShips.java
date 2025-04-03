package game;

import java.awt.Color;
import java.awt.Graphics;

import display.Display;
import game.attackboard.AttackCell;
import gfx.Text;

public class EnemyShips {
    
    private static final int BORDER_WIDTH = AttackCell.DEFAULT_CELL_WIDTH * 11 / 2;
    private static final int BORDER_HEIGHT = 300;
    private static final int BORDER_X_OFFSET = AttackCell.DEFAULT_CELL_WIDTH * 11 + 100;
    private static final int BORDER_Y_OFFSET = Display.resolution.height / 2 - AttackCell.DEFAULT_CELL_HEIGHT * 11 / 2;

    private int[] enemyShips = new int[4];

    public EnemyShips() {
        enemyShips[0] = 2;
        enemyShips[1] = 3;
        enemyShips[2] = 3;
        enemyShips[3] = 1;
        
    }

    public void render(Graphics g) {

        drawBorder(g);
        Text.drawString(g, "ENEMY SHIPS", BORDER_X_OFFSET + BORDER_WIDTH - BORDER_WIDTH / 2, BORDER_Y_OFFSET + 20, true, Color.BLACK, Game.assets.font40);
        for (int i = 0; i < 4; i++) {
            String text = Integer.toString(enemyShips[i]) + "x Ships length " + Integer.toString(i + 1);
            Text.drawString(g, text, BORDER_X_OFFSET + 10, (BORDER_Y_OFFSET + 75) + (70 * i), false, Color.BLACK, Game.assets.font25);
        }

    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.BLACK);
        for (int thickness = 0; thickness <= 2; thickness++)
            g.drawRect(BORDER_X_OFFSET + thickness, BORDER_Y_OFFSET + thickness, BORDER_WIDTH - thickness * 2, BORDER_HEIGHT - thickness * 2);
    }

    public void update(String shipDestroyed) {
        switch (shipDestroyed) {
            case "DESTROYED_SHIP_4":
                enemyShips[3]--;
                break;
            case "DESTROYED_SHIP_3":
                enemyShips[2]--;
                break;
            case "DESTROYED_SHIP_2":
                enemyShips[1]--;
                break;
            case "DESTROYED_SHIP_1":
                enemyShips[0]--;
                break;
            default:
                break;
        }
    }

}
