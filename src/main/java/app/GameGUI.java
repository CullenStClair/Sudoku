package main.java.app;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the game window.
 *
 * @author Cullen St. Clair
 */
public final class GameGUI {

    private final JFrame gameFrame;
    private final static int SIDE_LENGTH = 900;

    public GameGUI() {

        // create main window
        gameFrame = new JFrame("Sudoku");
        gameFrame.setSize(SIDE_LENGTH, SIDE_LENGTH);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.setContentPane(new JLabel(Main.imageAssets.background));

        // add menu bar and items
        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        gameFrame.setJMenuBar(menuBar);

        // add grid layout to window
        final GridLayout GRID = new GridLayout(Main.GRID_SIZE, Main.GRID_SIZE, 2, 2);
        gameFrame.setLayout(GRID);

        // display the window
        gameFrame.setVisible(true);

    }

    /**
     * Create the "File" menu.
     *
     * @return the "File" JMenuItem.
     */
    private JMenu createFileMenu() {

        final JMenu fileMenu = new JMenu("File");

        // "exit" button
        final JMenuItem fileMenuItemExit = new JMenuItem("Exit");
        fileMenuItemExit.addActionListener(e -> System.exit(0));
        fileMenu.add(fileMenuItemExit);

        // "generate" button
        final JMenuItem fileMenuItemGenerate = new JMenuItem("Generate Puzzle");
        fileMenuItemGenerate.addActionListener(e -> Main.gameBoard.initializeBoard());
        fileMenu.add(fileMenuItemGenerate);

        // "verify solution" button
        final JMenuItem fileMenuItemCheck = new JMenuItem("Verify Solution");
        fileMenuItemCheck.addActionListener(e -> Main.gameBoard.checkSolution());
        fileMenu.add(fileMenuItemCheck);

        // "show solution" button
        final JMenuItem fileMenuItemShow = new JMenuItem("Show Solution");
        fileMenuItemShow.addActionListener(e -> Main.gameBoard.showSolution());
        fileMenu.add(fileMenuItemShow);

        // "about" button
        final JMenuItem fileMenuItemAbout = new JMenuItem("About");
        fileMenuItemAbout.addActionListener(e -> JOptionPane.showMessageDialog(Main.application.gameFrame,
                "This main.java.application was made by Cullen St. Clair.\nSudoku is a simple game, in which the objective is to fill the entire 9x9 grid with the numbers 1-9.\nThe rules are that no number may be repeated within a row or column, or within a sub-grid of 3x3 squares.\nThis program generates new puzzles automatically.\nThere are 3 difficulty levels to chose from which determine how many starting squares are filled in for you.\nHave fun!",
                "About Sudoku", JOptionPane.PLAIN_MESSAGE));
        fileMenu.add(fileMenuItemAbout);

        return fileMenu;
    }

    /**
     * Add a component to the main.java.application GUI.
     *
     * @param element Component to be added.
     */
    public void addComponent(Component element) {
        gameFrame.add(element);
    }

    /**
     * Refresh the GUI.
     */
    public void refreshGUI() {
        gameFrame.revalidate();
        gameFrame.repaint();
    }

    /**
     * Request new tile value from the user.
     *
     * @return User response value.
     */
    public String getNewSquareValue() {
        final Object[] options = {"-", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        return (String) JOptionPane.showInputDialog(gameFrame, "Type or select a new value:", "Change Value",
                JOptionPane.PLAIN_MESSAGE, null, options, "-");
    }

    /**
     * Request puzzle difficulty from the user.
     *
     * @return User response value.
     */
    public int getNewPuzzleDifficulty() {

        final Object[] options = {"Easy", "Normal", "Hard"};
        String answer = (String) JOptionPane.showInputDialog(gameFrame, "Choose a difficulty:", "Puzzle Difficulty",
                JOptionPane.PLAIN_MESSAGE, null, options, "Normal");

        if (answer == null) {
            return 0;
        }

        return switch (answer) {
            case "Easy" -> 1;
            case "Normal" -> 2;
            case "Hard" -> 3;
            default -> 0;
        };
    }

    public void alertSolved() {
        JOptionPane.showMessageDialog(gameFrame, "This puzzle is solved!", "Solved", JOptionPane.PLAIN_MESSAGE);
    }

    public void alertUnsolved() {
        JOptionPane.showMessageDialog(gameFrame, "Sorry, this puzzle is not solved.", "Not Solved",
                JOptionPane.PLAIN_MESSAGE);
    }

}
