package game.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import display.Display;
import game.Game;
import game.attackboard.AttackCell;
import game.board.ships.ShipsManager;
import gfx.Text;

public class Board {
    
    private static final int BOARD_SIZE = 10;
    private static final int CELL_WIDTH = AttackCell.DEFAULT_CELL_WIDTH / 2;
    private static final int CELL_HEIGHT = AttackCell.DEFAULT_CELL_HEIGHT / 2;
    private static final int BOARD_X_OFFSET = AttackCell.DEFAULT_CELL_WIDTH * 11 + 100;
    private static final int BOARD_Y_OFFSET = 3 * Display.resolution.height / 4 - CELL_HEIGHT * 11 / 2 - 2 * CELL_WIDTH + 20;

    private Game game;
    private ShipsManager shipsManager;

    private Cell[][] board = new Cell[BOARD_SIZE][BOARD_SIZE];

    public Board(Game game) {
        this.game = game;
        
        for (int col = 0; col < BOARD_SIZE; col++) 
            for (int row = 0; row < BOARD_SIZE; row++) {
                int cellX = BOARD_X_OFFSET + CELL_WIDTH + col * CELL_WIDTH;
                int cellY = BOARD_Y_OFFSET + CELL_HEIGHT + row * CELL_HEIGHT;
                board[col][row] = new Cell(cellX, cellY, CELL_WIDTH, CELL_HEIGHT);
            }
                
        shipsManager = new ShipsManager(this);
    }

    public void update() {
        
    }

    public void render(Graphics g) {
        
        g.setColor(Color.BLACK);
        for (int i = 1; i <= BOARD_SIZE; i++) {      // Renderizza le celle delle lettere e numeri
            int xPosition = BOARD_X_OFFSET + i * CELL_WIDTH;
            int yPosition = BOARD_Y_OFFSET + i * CELL_HEIGHT;

            g.drawRect(xPosition, BOARD_Y_OFFSET, CELL_WIDTH, CELL_HEIGHT);
            Text.drawString(g, String.valueOf((char)('A' + i - 1)), xPosition + CELL_WIDTH / 2,
                BOARD_Y_OFFSET + CELL_HEIGHT / 2, true, Color.BLACK, Game.assets.font25);

            g.drawRect(BOARD_X_OFFSET, yPosition, CELL_WIDTH, CELL_HEIGHT);
            Text.drawString(g, String.valueOf(i - 1), BOARD_X_OFFSET + CELL_WIDTH / 2,
                yPosition + CELL_HEIGHT / 2, true, Color.BLACK, Game.assets.font25);
        }

        shipsManager.render(g);

        for (Cell[] row : board)
            for (Cell c : row) 
                c.render(g);

    }

    public String hit(Point hitIndices) {
        return board[hitIndices.x][hitIndices.y].hit();
    }

    public int getXOffset() {
        return BOARD_X_OFFSET;
    }

    public int getYOffset() {
        return BOARD_Y_OFFSET;
    }

    public Dimension getCellSize() {
        return new Dimension(CELL_WIDTH, CELL_HEIGHT);
    }

    public Cell[][] getBoard() {
        return board;
    }

    public Game getGame() {
        return game;
    }

    public void reset() {
        for (Cell[] row : board)
            for (Cell c : row) 
                c.reset();
    }

}
