package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import display.Display;
import game.attackboard.AttackCell;
import gfx.Text;

public class EnemyShips {
    
    private static final int BORDER_WIDTH = AttackCell.DEFAULT_CELL_WIDTH * 11 / 2;
    private static final int BORDER_HEIGHT = 300;
    private static final int BORDER_X_OFFSET = AttackCell.DEFAULT_CELL_WIDTH * 11 + 100;
    private static final int BORDER_Y_OFFSET = Display.resolution.height / 2 - AttackCell.DEFAULT_CELL_HEIGHT * 11 / 2;
    private static final int[] ENEMY_SHIPS = {2, 3, 3, 1};

    private int[] enemyShips = new int[4];

    public EnemyShips() {
        enemyShips = ENEMY_SHIPS.clone();
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
        Graphics2D g2d = (Graphics2D)g; 
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(BORDER_X_OFFSET, BORDER_Y_OFFSET, BORDER_WIDTH, BORDER_HEIGHT);
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

    public void reset() {
        enemyShips = ENEMY_SHIPS.clone();
    }

}
