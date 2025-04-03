package states;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import game.Game;

// La classe abstract che estendono tutti gli stati che può assumere il programma
public abstract class State {
    
    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public abstract void update();
    public abstract void render(Graphics g);
    public abstract void click(MouseEvent e);

}
