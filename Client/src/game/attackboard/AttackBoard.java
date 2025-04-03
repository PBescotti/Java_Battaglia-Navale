package game.attackboard;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

import game.Game;
import gfx.Text;

public class AttackBoard {
    
    private static final int BOARD_SIZE = 10;
    
    private int boardXOffset = 25;
    private int boardYOffset = 0;

    private Game game;
    private AttackCell[][] board = new AttackCell[BOARD_SIZE][BOARD_SIZE];

    public AttackBoard(Game game) {
        this.game = game;
        boardYOffset = game.getResolution().height / 2 - AttackCell.DEFAULT_CELL_HEIGHT * 11 / 2;

        for (int col = 0; col < BOARD_SIZE; col++) {
            for (int row = 0; row < BOARD_SIZE; row++) { // Crea tutte le celle in modo tale che il lati si tocchino e non si sovrappongono
                int cellX = 1 + boardXOffset + col * (AttackCell.DEFAULT_CELL_WIDTH + 1) + AttackCell.DEFAULT_CELL_WIDTH;
                int cellY = 1 + boardYOffset + row * (AttackCell.DEFAULT_CELL_HEIGHT + 1) + AttackCell.DEFAULT_CELL_HEIGHT;
                board[col][row] = new AttackCell(game, cellX, cellY, AttackCell.DEFAULT_CELL_WIDTH, AttackCell.DEFAULT_CELL_HEIGHT);
            }              
        }
    }

    public void update() {
        for (AttackCell[] row : board)
            for (AttackCell c : row)
                c.update();
        
    }

    public Point click(MouseEvent e) {
        for (int row = 0; row < BOARD_SIZE; row++) 
            for (int col = 0; col < BOARD_SIZE; col++)
                if (board[col][row].click(e)) 
                    return new Point(col, row);
                
        return null;  
        
    }

    public void render(Graphics g) {

        g.setColor(Color.BLACK);
        for (int i = 1; i <= BOARD_SIZE; i++) {      // Renderizza le celle dei numeri e delle lettere
            int xPosition = boardXOffset + i * (AttackCell.DEFAULT_CELL_WIDTH + 1);
            int yPosition = boardYOffset + i * (AttackCell.DEFAULT_CELL_HEIGHT + 1);

            g.drawRect(xPosition, boardYOffset, AttackCell.DEFAULT_CELL_WIDTH, AttackCell.DEFAULT_CELL_HEIGHT);
            Text.drawString(g, String.valueOf((char)('A' + i - 1)), xPosition + AttackCell.DEFAULT_CELL_WIDTH / 2,
                            boardYOffset + AttackCell.DEFAULT_CELL_HEIGHT / 2, true, Color.BLACK, Game.assets.font50);

            g.drawRect(boardXOffset, yPosition, AttackCell.DEFAULT_CELL_WIDTH, AttackCell.DEFAULT_CELL_HEIGHT);
            Text.drawString(g, String.valueOf(i - 1), boardXOffset + AttackCell.DEFAULT_CELL_WIDTH / 2,
                            yPosition + AttackCell.DEFAULT_CELL_HEIGHT / 2, true, Color.BLACK, Game.assets.font50);
        }

        for (AttackCell[] row : board)
            for (AttackCell c : row)
                c.render(g);
    }

    public void reset() {
        for (AttackCell[] row : board) {
            for (AttackCell c : row)
                c.reset();
        }
    }

    public Game getGame() {
        return game;
    }
}
