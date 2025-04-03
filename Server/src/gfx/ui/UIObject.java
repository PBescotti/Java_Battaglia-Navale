package gfx.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

/*
    La classe abstract che estendono tutti gli elementi UI
    ha una variabile bounds che contiene la posizione e la larghezza e altezza del bottone
*/
public abstract class UIObject {
    
    protected Rectangle bounds;

    public UIObject(int x, int y, int width, int height){
        bounds = new Rectangle(x, y, width, height);
    }
    
    public abstract void update();
    public abstract void render(Graphics g);
    public abstract void click(MouseEvent e);

}
