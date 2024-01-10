package app;

import gameobjects.Board;
import gameobjects.SudokuPuzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Generates sudoku puzzles.
 *
 * @author Cullen St. Clair
 */
public final class SudokuGenerator {

    private static int counter = 0;
    private static String[] numList = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    /**
     * Generates and returns a new sudoku puzzle of specified difficulty.
     *
     * @param difficulty Integer 1-3 representing easy, normal, or hard.
     * @return SudokuPuzzle
     */
    public static SudokuPuzzle generateNew(int difficulty) {

        String[][] solution = copyGrid(Board.defaultGrid);
        createPuzzle(solution);

        String[][] initial = copyGrid(solution);
        removeNumbers(initial, difficulty);

        return new SudokuPuzzle(initial, solution);
    }

    /**
     * Checks if a value is in the array.
     *
     * @param value String to check for
     * @param arr   List to check in
     * @return true if value is in array, else false
     */
    private static boolean arrayContainsVal(String value, String[] arr) {
        for (String val : arr) {
            if (val.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a grid is full of non-default values.
     *
     * @param grid Array to be checked
     * @return true if the grid is full, false if not
     */
    private static boolean isGridFull(String[][] grid) {

        // loop over each cell and check if it is empty
        for (String[] row : grid) {
            for (String cell : row) {
                if ("-".equals(cell)) {
                    return false;
                }
            }
        }
        // the grid is full
        return true;
    }

    /**
     * Counts the total number of solutions for a sudoku grid using a recursive
     * backtracking solver algorithm.
     *
     * @param grid Array to be checked.
     * @return true when complete
     */
    private static boolean countSolutions(String[][] grid) {
        int row = 0;
        int col = 0;
        // for each cell in grid
        for (int i = 0; i < 81; i++) {
            row = i / 9;
            col = i % 9;

            // proceed if cell is empty
            if ("-".equals(grid[row][col])) {

                // for each value from 1-9
                for (String val : numList) {

                    // check if value not used already in row
                    if (!arrayContainsVal(val, grid[row])) {

                        // check if value not used already in column
                        if (!arrayContainsVal(val, new String[]{grid[0][col], grid[1][col], grid[2][col],
                                grid[3][col], grid[4][col], grid[5][col], grid[6][col], grid[7][col], grid[8][col]})) {

                            // find what 3x3 sub-square this cell is in
                            String[][] subgrid = getSubSquare(grid, row, col);

                            // merge subgrid into single array
                            List<String> combined = new ArrayList<>(Arrays.asList(subgrid[0]));
                            combined.addAll(Arrays.asList(subgrid[1]));
                            combined.addAll(Arrays.asList(subgrid[2]));
                            String[] merged = Arrays.copyOf(combined.toArray(), combined.size(), String[].class);

                            // check if value not already used 3x3 sub-square
                            if (!arrayContainsVal(val, merged)) {
                                grid[row][col] = val;
                                if (isGridFull(grid)) {
                                    counter++;
                                    break;
                                } else {
                                    if (countSolutions(grid)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            }
        }
        grid[row][col] = "-";
        return false;
    }

    /**
     * Creates a new sudoku puzzle within the given empty grid. Uses the
     * backtracking algorithm.
     *
     * @param grid Array with new puzzle
     * @return true if puzzle is complete
     */
    private static boolean createPuzzle(String[][] grid) {
        int row = 0;
        int col = 0;
        // for each cell in grid
        for (int i = 0; i < 81; i++) {
            row = i / 9;
            col = i % 9;

            // proceed if cell is empty
            if ("-".equals(grid[row][col])) {

                // randomize list of numbers
                numList = shuffleArray(numList);

                // for each value from 1-9
                for (String val : numList) {

                    // check if value not used already in row
                    if (!arrayContainsVal(val, grid[row])) {

                        // check if value not used already in column
                        if (!arrayContainsVal(val, new String[]{grid[0][col], grid[1][col], grid[2][col],
                                grid[3][col], grid[4][col], grid[5][col], grid[6][col], grid[7][col], grid[8][col]})) {

                            // find what 3x3 sub-square this cell is in
                            String[][] subgrid = getSubSquare(grid, row, col);

                            // merge subgrid into single array
                            List<String> combined = new ArrayList<>(Arrays.asList(subgrid[0]));
                            combined.addAll(Arrays.asList(subgrid[1]));
                            combined.addAll(Arrays.asList(subgrid[2]));
                            String[] merged = Arrays.copyOf(combined.toArray(), combined.size(), String[].class);

                            // check if value not already used 3x3 sub-square
                            if (!arrayContainsVal(val, merged)) {
                                grid[row][col] = val;
                                if (isGridFull(grid)) {
                                    return true;
                                } else {
                                    if (createPuzzle(grid)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            }
        }
        grid[row][col] = "-";
        return false;
    }

    /**
     * Removes numbers from full grid according to difficulty.
     *
     * @param grid       Array to remove from
     * @param difficulty Integer 1-3 representing easy, medium or hard
     */
    private static void removeNumbers(String[][] grid, int difficulty) {

        // determine number of tries based on difficulty
        int tries;
        switch (difficulty) {
            // easy
            case 1:
                tries = 6;
                break;
            // normal
            case 2:
                tries = 12;
                break;
            // hard
            case 3:
                tries = 20;
                break;
            default:
                return;
        }
        counter = 1;

        // remove numbers while tries remain
        while (tries > 0) {

            // select random non-empty cell
            int row, col;
            Random rand = new Random();
            do {
                row = rand.nextInt(9);
                col = rand.nextInt(9);
            } while ("-".equals(grid[row][col]));

            // store cell initial value
            String backup = grid[row][col];
            grid[row][col] = "-";

            // count number of puzzle solutions
            counter = 0;
            countSolutions(copyGrid(grid));

            // if there is more than one unique solution, undo
            if (counter != 1) {
                grid[row][col] = backup;
                tries -= 1;
            }
        }
    }

    /**
     * Gets the 3x3 sub-square in which the current cell is located.
     *
     * @param grid Master grid to read from
     * @param row  First index of current cell
     * @param col  Second index of current cell
     * @return 2d array of values in sub-square
     */
    private static String[][] getSubSquare(String[][] grid, int row, int col) {
        String[][] subgrid = new String[3][3];
        if (row < 3) {
            if (col < 3) {
                subgrid[0] = Arrays.copyOfRange(grid[0], 0, 3);
                subgrid[1] = Arrays.copyOfRange(grid[1], 0, 3);
                subgrid[2] = Arrays.copyOfRange(grid[2], 0, 3);
            } else if (col < 6) {
                subgrid[0] = Arrays.copyOfRange(grid[0], 3, 6);
                subgrid[1] = Arrays.copyOfRange(grid[1], 3, 6);
                subgrid[2] = Arrays.copyOfRange(grid[2], 3, 6);
            } else {
                subgrid[0] = Arrays.copyOfRange(grid[0], 6, 9);
                subgrid[1] = Arrays.copyOfRange(grid[1], 6, 9);
                subgrid[2] = Arrays.copyOfRange(grid[2], 6, 9);
            }
        } else if (row < 6) {
            if (col < 3) {
                subgrid[0] = Arrays.copyOfRange(grid[3], 0, 3);
                subgrid[1] = Arrays.copyOfRange(grid[4], 0, 3);
                subgrid[2] = Arrays.copyOfRange(grid[5], 0, 3);
            } else if (col < 6) {
                subgrid[0] = Arrays.copyOfRange(grid[3], 3, 6);
                subgrid[1] = Arrays.copyOfRange(grid[4], 3, 6);
                subgrid[2] = Arrays.copyOfRange(grid[5], 3, 6);
            } else {
                subgrid[0] = Arrays.copyOfRange(grid[3], 6, 9);
                subgrid[1] = Arrays.copyOfRange(grid[4], 6, 9);
                subgrid[2] = Arrays.copyOfRange(grid[5], 6, 9);
            }
        } else {
            if (col < 3) {
                subgrid[0] = Arrays.copyOfRange(grid[6], 0, 3);
                subgrid[1] = Arrays.copyOfRange(grid[7], 0, 3);
                subgrid[2] = Arrays.copyOfRange(grid[8], 0, 3);
            } else if (col < 6) {
                subgrid[0] = Arrays.copyOfRange(grid[6], 3, 6);
                subgrid[1] = Arrays.copyOfRange(grid[7], 3, 6);
                subgrid[2] = Arrays.copyOfRange(grid[8], 3, 6);
            } else {
                subgrid[0] = Arrays.copyOfRange(grid[6], 6, 9);
                subgrid[1] = Arrays.copyOfRange(grid[7], 6, 9);
                subgrid[2] = Arrays.copyOfRange(grid[8], 6, 9);
            }
        }
        return subgrid;
    }

    /**
     * Randomly shuffle the elements of the array.
     *
     * @param array String[] to shuffle
     * @return Shuffled array
     */
    private static String[] shuffleArray(String[] array) {
        ArrayList<String> temp = new ArrayList<>(Arrays.asList(array));
        Collections.shuffle(temp);
        String[] shuffled = new String[temp.size()];
        System.arraycopy(temp.toArray(), 0, shuffled, 0, temp.size());
        return shuffled;
    }

    /**
     * Create a shallow copy of a grid.
     * This is fine since String is immutable
     *
     * @param grid 2d string array to be copied
     * @return a copy of the grid
     */
    private static String[][] copyGrid(String[][] grid) {
        String[][] copy = new String[grid.length][grid.length];
        for (int i = 0; i < grid.length; i++) {
            copy[i] = grid[i].clone();
        }
        return copy;
    }
}
