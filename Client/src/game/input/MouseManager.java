package game.input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import states.StateManager;

public class MouseManager implements MouseListener, MouseMotionListener {

    private Point mousePos = new Point();

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos.x = e.getX();
        mousePos.y = e.getY();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        StateManager.getState().click(e);
    }
    
    public Point mousePosition() {
        return mousePos;
    }
    
    //Unused MouseLister/MouseMotionListener methods
    
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
}
