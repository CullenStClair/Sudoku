package main.java.app;

import main.java.gameobjects.Board;
import main.java.gameobjects.Tile;

public class Main {

    public final static int GRID_SIZE = 9;

    public final static Assets imageAssets = new Assets();
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
