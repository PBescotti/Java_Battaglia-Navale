package game.attackboard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import game.Game;

public class AttackCell {

    public static final int DEFAULT_CELL_WIDTH = 60,
                            DEFAULT_CELL_HEIGHT = 60;

    private final Color hoveringColor = new Color(200, 200, 200, 200);
    private final Color clickedColor = new Color(170, 170, 170, 200);

    private Game game;
    private boolean hovering;
    private Rectangle bounds;
    private boolean clicked = false;
 
    public AttackCell(Game game, int x, int y, int width, int height) {
        this.game = game;
        bounds = new Rectangle(x, y, width, height);
    }

    public void update() {
        if (!clicked)
            hovering = checkHovering();
    }

    public boolean click(MouseEvent e) {
        if (!clicked && e != null && bounds.contains(e.getPoint())) {
            clicked = true;
            return true;
        }
        return false;    
    }

    public void render(Graphics g) {
        if (hovering) {        
            g.setColor(hoveringColor);
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
        
        if (clicked) {
            g.setColor(clickedColor);
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }

        g.setColor(Color.BLACK);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    private boolean checkHovering() {
        return bounds.contains(game.getMouseManager().mousePosition());
    }

    public void reset() {
        clicked = false;
    }
}