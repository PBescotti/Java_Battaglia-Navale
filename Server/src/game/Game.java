package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import server.GameServer;
import display.Display;
import game.input.MouseManager;
import gfx.Assets;
import states.*;

public class Game {
    
    public static boolean running = true;
    public static Assets assets;
    public static State waitingState,
                        gameState;

    private String title;
    private Dimension resolution;
    private Display display;
    private MouseManager mouseManager;
    private BufferStrategy bs;
    private Graphics g;

    private GameServer client;
    
    public Game(String title, int width, int height) {
        this.title = title;
        resolution = new Dimension(width, height);
    }
    
    private void init() {
        assets = new Assets();
        mouseManager = new MouseManager();
        display = new Display(title, resolution.width, resolution.height);
        display.addMouseManager(mouseManager);
        display.getCanvas().createBufferStrategy(2);    //Crea una strategia di buffer del canvas
        bs = display.getCanvas().getBufferStrategy();              //Assegna la strategia di buffer
        
        client = new GameServer();
        waitingState = new WaitingState(this);
        gameState = new GameState(this);

        StateManager.setState(waitingState);
    }

    public void start() {
        init();
        
        int fps = 24;
        float timePerTick = 1000000000 / fps;
        float delta = 0;
        long now;
        long lastTime = System.nanoTime();

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime);
            lastTime = now;

            if (delta >= timePerTick) {       //Esegue le istruzione nell'if $fps volte al secondo
                delta = 0;
                
                update();           //Si occupa di controllare e aggiornare variabili
                render();           //Si occupa di mostrare a schermo gli elementi dell'interfaccia grafica/gioco
            }
        }
    }

    private void update() {
        if (StateManager.getState() != null)    //Se è stato impostato uno stato dell'applicazione chiama il metodo update
            StateManager.getState().update();
    }
    
    private void render() {                
        g = bs.getDrawGraphics();              //Assegna l'oggetto per disegnare sul canvas
        g.clearRect(0, 0 , resolution.width, resolution.height);

        // DRAW HERE

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, resolution.width, resolution.height);

        if (StateManager.getState() != null)    //Se è stato impostato uno stato dell'applicazione chiama il metodo render
            StateManager.getState().render(g);

        //

        bs.show();
        g.dispose();
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }
    
    public GameServer getClient() {
        return client;
    }
    
    public Dimension getResolution() {
        return resolution;
    }
}
