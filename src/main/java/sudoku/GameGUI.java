package sudoku;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the game window.
 *
 * @author Cullen St. Clair
 */
public class GameGUI {

    private static GameGUI instance;
    private final JFrame gameFrame;
    public final static int SIDE_LENGTH = 900;

    private GameGUI() {

        // set FlatLaf theme
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.err.println("Failed to initialize LaF");
        }

        // create main window
        gameFrame = new JFrame("Sudoku");
        gameFrame.setSize(SIDE_LENGTH, SIDE_LENGTH);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.setContentPane(new JLabel());

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

    public static GameGUI getInstance() {
        if (instance == null) {
            instance = new GameGUI();
        }
        return instance;
    }

    /**
     * Create the "File" menu.
     *
     * @return the "File" JMenuItem.
     */
    private JMenu createFileMenu() {

        final JMenu mainMenu = new JMenu("Menu");

        // "exit" button
        final JMenuItem fileMenuItemExit = new JMenuItem("Exit");
        fileMenuItemExit.addActionListener(e -> System.exit(0));
        mainMenu.add(fileMenuItemExit);

        // "generate" button
        final JMenuItem fileMenuItemGenerate = new JMenuItem("Generate Puzzle");
        fileMenuItemGenerate.addActionListener(e -> Main.gameBoard.initializeBoard());
        mainMenu.add(fileMenuItemGenerate);

        // "verify solution" button
        final JMenuItem fileMenuItemCheck = new JMenuItem("Verify Solution");
        fileMenuItemCheck.addActionListener(e -> Main.gameBoard.checkSolution());
        mainMenu.add(fileMenuItemCheck);

        // "show solution" button
        final JMenuItem fileMenuItemShow = new JMenuItem("Show Solution");
        fileMenuItemShow.addActionListener(e -> Main.gameBoard.showSolution());
        mainMenu.add(fileMenuItemShow);

        // "about" button
        final JMenuItem fileMenuItemAbout = new JMenuItem("About");
        fileMenuItemAbout.addActionListener(e -> JOptionPane.showMessageDialog(getInstance().gameFrame,
                "This java application was made by Cullen St. Clair.\nSudoku is a simple game, in which the objective is to fill the entire 9x9 grid with the numbers 1-9.\nThe rules are that no number may be repeated within a row or column, or within a sub-grid of 3x3 squares.\nThis program generates new puzzles automatically.\nThere are 3 difficulty levels to chose from which determine how many starting squares are filled in for you.\nHave fun!",
                "About Sudoku", JOptionPane.PLAIN_MESSAGE));
        mainMenu.add(fileMenuItemAbout);

        return mainMenu;
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
