package gfx.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

//  Si accouta di gestire gli elementi dell'UI che sono stati creati
public class UIManager {
    
    private ArrayList<UIObject> objects;

    public UIManager(){
        objects = new ArrayList<UIObject>();
    }

    public void update() {
        for (UIObject o : objects)
            o.update();
    }

    public void click(MouseEvent e) {
        for (UIObject o : objects)
            o.click(e);
    }

    public void render(Graphics g){
        for (UIObject o : objects)
            o.render(g);
    }

    public void add(UIObject o){
        objects.add(o);
    }

    public void clear() {
        objects.clear();
    }
}
