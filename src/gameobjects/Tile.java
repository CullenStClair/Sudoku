package gameobjects;

import application.SudokuMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Represents a tile on the board.
 *
 * @author Cullen St. Clair
 */
public final class Tile {

    private final JButton tileButton;

    private String value = "-";

    public Tile() {

        // create tile button
        tileButton = new JButton();
        tileButton.setIcon(SudokuMain.imageAssets.blank);
        tileButton.setBackground(Color.WHITE);
        tileButton.setFont(new Font("Calibri", Font.PLAIN, 60));
        tileButton.setEnabled(false);
        tileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // prompt user for tile value on click
                String newValue = SudokuMain.application.getNewSquareValue();
                if (newValue == null) {
                    return;
                }
                setTileValue(newValue);
            }
        });

    }

    /**
     * Get the tile's JButton object.
     *
     * @return the JButton
     */
    public JButton getTileButton() {
        return tileButton;
    }

    /**
     * Get the tile's value.
     *
     * @return value
     */
    public String getTileValue() {
        return value;
    }

    /**
     * Set the value and image of the tile.
     *
     * @param val New tile value.
     */
    public void setTileValue(String val) {

        switch (val) {
            case "-":
                tileButton.setIcon(SudokuMain.imageAssets.blank);
                break;
            case "1":
                tileButton.setIcon(SudokuMain.imageAssets.img1);
                break;
            case "2":
                tileButton.setIcon(SudokuMain.imageAssets.img2);
                break;
            case "3":
                tileButton.setIcon(SudokuMain.imageAssets.img3);
                break;
            case "4":
                tileButton.setIcon(SudokuMain.imageAssets.img4);
                break;
            case "5":
                tileButton.setIcon(SudokuMain.imageAssets.img5);
                break;
            case "6":
                tileButton.setIcon(SudokuMain.imageAssets.img6);
                break;
            case "7":
                tileButton.setIcon(SudokuMain.imageAssets.img7);
                break;
            case "8":
                tileButton.setIcon(SudokuMain.imageAssets.img8);
                break;
            case "9":
                tileButton.setIcon(SudokuMain.imageAssets.img9);
                break;
        }
        value = val;
    }

}
