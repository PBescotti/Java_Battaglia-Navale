package game.board.ships;

import java.awt.Graphics;
import java.awt.Point;

import game.board.Board;

public class ShipsManager {
    
    public enum ShipType {
        SHIP_1(1), SHIP_2(2), SHIP_3(3), SHIP_4(4);

        private final int length;

        ShipType(int length) {
            this.length = length;
        }

        public int getLength() {
            return length;
        }
    }

    private final ShipType[] setup = { ShipType.SHIP_1, ShipType.SHIP_1, ShipType.SHIP_2, ShipType.SHIP_2, ShipType.SHIP_2, ShipType.SHIP_3, ShipType.SHIP_3, ShipType.SHIP_3, ShipType.SHIP_4 };
    private Ship[] ships = new Ship[setup.length];
    private Board board;

    public ShipsManager(Board board) {
        this.board = board;
        for (int i = 0; i < setup.length; i++) {
            ShipType shipType = setup[i];
            ships[i] = new Ship(board, shipType.getLength(), new Point(i, 0),   // Piazzamento navi temporaneo
                    board.getXOffset() + (i + 1) * board.getCellSize().width + board.getCellSize().width / 2 - Ship.DEFAULT_SHIP_HEIGHT / 2,
                    board.getYOffset() + board.getCellSize().height + board.getCellSize().height / 2 - Ship.DEFAULT_SHIP_HEIGHT / 2,
                    Ship.ROTATION_SOUTH, board.getCellSize().width, board.getCellSize().height);
        }
    }

    public void render(Graphics g) {
        for (Ship s : ships)
            s.render(g);
    }

    public Ship[] getShips() {
        return ships;
    }

    public Board getBoard() {
        return board;
    }

}
