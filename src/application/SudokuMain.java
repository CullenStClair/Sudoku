package application;

import gameobjects.Assets;
import gameobjects.Board;
import gameobjects.Tile;

public class SudokuMain {

    public final static int GRID_SIZE = 9;

    // initialize game board and application
    public final static Assets imageAssets = new Assets();
    public final static Board gameBoard = new Board();
    public final static GameGUI application = new GameGUI();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        // add board tiles to application
        for (Tile[] row : gameBoard.getCurrentTileBoard()) {
            for (Tile item : row) {
                application.addComponent(item.getTileButton());
            }
        }
        application.refreshGUI();
    }

}
