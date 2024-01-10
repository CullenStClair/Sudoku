package main.java.app;

import main.java.gameobjects.Board;
import main.java.gameobjects.Tile;

public class Main {

    public final static int GRID_SIZE = 9;

    // initialize game board and main.java.application
    public final static Assets imageAssets = new Assets();
    public final static Board gameBoard = new Board();
    public final static GameGUI application = new GameGUI();

    /**
     * Launch the main.java.application.
     */
    public static void main(String[] args) {

        // add board tiles to main.java.application
        for (Tile[] row : gameBoard.getCurrentTileBoard()) {
            for (Tile item : row) {
                application.addComponent(item.getTileButton());
            }
        }
        application.refreshGUI();
    }

}
