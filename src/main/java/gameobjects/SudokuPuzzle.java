package main.java.gameobjects;

/**
 * Represents a sudoku puzzle.
 *
 * @author Cullen St. Clair
 */
public final class SudokuPuzzle {

    public final String[][] initial, solution;

    /**
     * Create a new sudoku puzzle object.
     *
     * @param initial  Starting grid to show user
     * @param solution Fully filled grid
     */
    public SudokuPuzzle(String[][] initial, String[][] solution) {

        this.initial = initial;
        this.solution = solution;
    }

}
