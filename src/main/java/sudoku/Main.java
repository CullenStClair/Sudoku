package sudoku;

import gameobjects.Board;
import gameobjects.Tile;


public class Main {

    public final static int GRID_SIZE = 9; // does not currently work with any other board size
    public final static Board gameBoard = new Board();

    /**
     * Launch the main.java.application.
     */
    public static void main(String[] args) {

        GameGUI gui = GameGUI.getInstance();

        // add board tiles to main.java.application
        for (Tile[] row : gameBoard.getCurrentTileBoard()) {
            for (Tile item : row) {
                gui.addComponent(item.getTileButton());
            }
        }
        gui.refreshGUI();
    }

}
