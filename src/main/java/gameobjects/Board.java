package gameobjects;

import sudoku.GameGUI;
import sudoku.Main;
import sudoku.SudokuGenerator;

import java.util.Arrays;

/**
 * Represents the game board.
 *
 * @author Cullen St. Clair
 */
public class Board {

    private final Tile[][] tileGrid = new Tile[Main.GRID_SIZE][Main.GRID_SIZE];
    private SudokuPuzzle currentPuzzle = null;
    public final static String[][] defaultGrid = {{"-", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-", "-"}, {"-", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-", "-"}, {"-", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-", "-"}, {"-", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-", "-"}, {"-", "-", "-", "-", "-", "-", "-", "-", "-"}};

    public Board() {

        // initialize tile array
        for (int i = 0; i < Main.GRID_SIZE; i++) {
            for (int j = 0; j < Main.GRID_SIZE; j++) {
                tileGrid[i][j] = new Tile();
            }
        }
    }

    /**
     * Initialize the board with a new puzzle.
     */
    public void initializeBoard() {

        // get desired difficulty from user
        int difficulty = GameGUI.getInstance().getNewPuzzleDifficulty();
        if (difficulty == 0) {
            return;
        }

        // clear previous board, generate and display new puzzle
        currentPuzzle = SudokuGenerator.generateNew(difficulty);
        for (int i = 0; i < Main.GRID_SIZE; i++) {
            for (int j = 0; j < Main.GRID_SIZE; j++) {
                tileGrid[i][j].setTileValue(currentPuzzle.initial()[i][j]);
                tileGrid[i][j].getTileButton().setEnabled("-".equals(tileGrid[i][j].getTileValue()));
            }
        }
    }

    /**
     * Get the current tile board.
     */
    public Tile[][] getCurrentTileBoard() {
        return tileGrid;
    }

    /**
     * Check if the current puzzle has been solved and display a message.
     */
    public void checkSolution() {

        // check if there is no puzzle
        if (currentPuzzle == null) {
            return;
        }

        String[][] gridValues = new String[Main.GRID_SIZE][Main.GRID_SIZE];

        // copy values from tile grid to string grid
        for (int i = 0; i < Main.GRID_SIZE; i++) {
            for (int j = 0; j < Main.GRID_SIZE; j++) {
                gridValues[i][j] = tileGrid[i][j].getTileValue();
            }
        }

        // compare the arrays
        if (Arrays.deepEquals(gridValues, currentPuzzle.solution())) {
            GameGUI.getInstance().alertSolved();
        } else {
            GameGUI.getInstance().alertUnsolved();
        }
    }

    /**
     * Display the correct solution on the board.
     */
    public void showSolution() {

        // check if there is no puzzle
        if (currentPuzzle == null) {
            return;
        }

        // loop over all tiles
        for (int i = 0; i < Main.GRID_SIZE; i++) {
            for (int j = 0; j < Main.GRID_SIZE; j++) {
                // update board
                tileGrid[i][j].setTileValue(currentPuzzle.solution()[i][j]);
                tileGrid[i][j].getTileButton().setEnabled(false);
            }
        }
    }

}
