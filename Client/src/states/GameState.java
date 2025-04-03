package states;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import client.GameClient;
import game.EnemyShips;
import game.Game;
import game.VictoryManager;
import game.attackboard.AttackBoard;
import game.board.Board;

public class GameState extends State {
    
    private volatile boolean canAttack = false; // Falsa GameClient aspetta mossa avversario e disabilità tabella attacco. Vera GameClient aspetta informazione attacco inviato
    private volatile EnemyShips enemyShips;     // Classe che si occupa di contare le navi nemiche e mostrarle a schermo
    
    private VictoryManager victoryManager;
    private GameClient client;
    private AttackBoard attackBoard;            // Tabella attacco, quando attiva invia le coordinate (tabella) del click all'avversario
    private Board board;                        // Tabella proprie navi

    public GameState(Game game) {
        super(game);
        this.client = game.getClient();
        attackBoard = new AttackBoard(game);
        board = new Board(game);
        enemyShips = new EnemyShips();
        victoryManager = new VictoryManager();
    }

    @Override
    public synchronized void update() {
        if (!client.isConnected()) {
            reset();
            StateManager.setState(Game.waitingState);
        }
        
        if (canAttack)
            attackBoard.update();

        if (client.isHitted()) {
            String message = board.hit(client.getHitIndices());
            client.sendMessage(message);
            canAttack = true;
            synchronized (client) {
                client.notify();
            }
        }
    }

    @Override
    public synchronized void click(MouseEvent e) {
        if (!canAttack)
            return;

        Point attackIndicies = attackBoard.click(e);
        if (attackIndicies != null) {
            canAttack = false;
            sendMessageToServer(attackIndicies.x, attackIndicies.y);
        }  
    }

    @Override
    public void render(Graphics g) {
        attackBoard.render(g);
        board.render(g);
        enemyShips.render(g);
        if (victoryManager.isWin())
            victoryManager.renderVictoryScreen(g);
        

    }

    private void sendMessageToServer(int col, int row) {
        GameClient client = game.getClient();
        String message = col + " " + row;
        client.sendMessage(message);
    }
    
    public void reset() {
        board.reset();
        attackBoard.reset();
        enemyShips.reset();
    }
    
    public VictoryManager getVictoryManager() {
        return victoryManager;
    }

    public GameClient getCLient() {
        return client;
    }

    public synchronized boolean canAttack() {
        return canAttack;
    }

    public EnemyShips getEnemyShips() {
        return enemyShips;
    }
}
