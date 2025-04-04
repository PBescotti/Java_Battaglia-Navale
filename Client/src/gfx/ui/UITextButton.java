package gfx.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import game.Game;
import gfx.Text;

/*
    Scrive il testo che gli è stato passato partendo da sinistra del bottone
    Quando il cursore non è sopra il bottone il contrno è nero e quando c'è sopra è rosso
    Ha un ClickListener che viene chiamato quando viene clicato il bottone 
    Il ClickListener si inizializza durante la creazione del bottone
*/
public class UITextButton extends UIObject {

    private Game game;
    private boolean hovering;
    private String text;
    private ClickListener clicker;

    public UITextButton(Game game, int x, int y, int width, int height, boolean center, String text, ClickListener clicker) {
        super(x, y, width, height);
        this.game = game;
        this.clicker = clicker;
        this.text = text;


        if (center){
            bounds.x -= width / 2;
            bounds.y -= height / 2;
        }
    }

    @Override
    public void update() {
        hovering = checkHovering();
    }

    @Override
    public void click(MouseEvent e) {
        if (bounds.contains(e.getPoint()))
            clicker.onClick();
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(hovering ? Color.RED : Color.BLACK);

        g2d.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        Text.drawString(g, text, bounds.x + 7, bounds.y + bounds.height - 15, false, Color.BLACK, Game.assets.font25);

    }

    private boolean checkHovering() {
        return bounds.contains(game.getMouseManager().mousePosition());
    }
}
