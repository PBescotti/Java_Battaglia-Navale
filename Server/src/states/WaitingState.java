package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import server.GameServer;
import game.Game;
import gfx.Text;
import gfx.ui.ClickListener;
import gfx.ui.UIManager;
import gfx.ui.UITextButton;


/*
    In questo stato il programma aspetta di connettersi con il server
    Nel caso di connessione fallita da un feedback a schermo e la possibilità di riporovare la connessione
*/
public class WaitingState extends State {

    private GameServer client;
    private long timer = 0,             // Per l'animazione durante la connessione
                 now = 0, 
                 lastTime = System.currentTimeMillis();

    private UIManager uiManager;

    public WaitingState(Game game) {
        super(game);
        this.client = game.getClient();
        this.client.start();

        uiManager = new UIManager();

        uiManager.add(new UITextButton(game, 0, 0, 500, 100, false, "SALVE", new ClickListener() {

            @Override
            public void onClick() {
                synchronized (client) {
                    client.notify();
                } 
            }
            
        }));
    }

    @Override
    public void update() {
        if (client.isConnected()) {
            StateManager.setState(Game.gameState);
            return;
        }

        if (client.isConnecting())  {
            now = System.currentTimeMillis();
            timer += now - lastTime;
            lastTime = now;
    
            if (timer >= 2000)
                timer = 0;
        } else {
            timer = 0;
            lastTime = System.currentTimeMillis();
            uiManager.update();
        }

        
    }

    @Override
    public void render(Graphics g) {
        if (client.isConnected()) {
            Text.drawString(g, "Connected", game.getResolution().width / 2, game.getResolution().height / 2, true, Color.BLACK, Game.assets.font50);
            return;
        }

        if (client.isConnecting()) {
            if (timer >= 1500)
                Text.drawString(g, "Connecting...", game.getResolution().width / 2, game.getResolution().height / 2, true, Color.BLACK, Game.assets.font50);
            else if (timer >= 1000)
                Text.drawString(g, "Connecting..", game.getResolution().width / 2, game.getResolution().height / 2, true, Color.BLACK, Game.assets.font50);
            else if (timer >= 500)
                Text.drawString(g, "Connecting.", game.getResolution().width / 2, game.getResolution().height / 2, true, Color.BLACK, Game.assets.font50);
            else 
                Text.drawString(g, "Connecting", game.getResolution().width / 2, game.getResolution().height / 2, true, Color.BLACK, Game.assets.font50);
        } else {
            Text.drawString(g, "No server found!", game.getResolution().width / 2, game.getResolution().height / 2, true, Color.BLACK, Game.assets.font50);
            uiManager.render(g);
        }  
    }   

    public void click(MouseEvent e) {
        if (!client.isConnecting() && !client.isConnected())
            uiManager.click(e);
    }
}
