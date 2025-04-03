package states;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import server.GameServer;
import game.EnemyShips;
import game.Game;
import game.attackboard.AttackBoard;
import game.board.Board;

public class GameState extends State {
    
    public static volatile boolean canAttack = true;
    public static volatile EnemyShips enemyShips;
    
    private GameServer client;
    private AttackBoard attackBoard;
    private Board board;

    public GameState(Game game) {
        super(game);
        this.client = game.getClient();
        attackBoard = new AttackBoard(game);
        board = new Board(game);
        enemyShips = new EnemyShips();
    }

    @Override
    public void update() {
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
    public void click(MouseEvent e) {
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
    }

    private void sendMessageToServer(int col, int row) {
        GameServer client = game.getClient();
        String message = col + " " + row;
        client.sendMessage(message);
    }

    public GameServer getCLient() {
        return client;
    }

    public void reset() {
        board.reset();
        attackBoard.reset();
    }
}
