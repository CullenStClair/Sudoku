package main.java.gameobjects;

import main.java.app.GameGUI;
import main.java.app.Main;

import javax.swing.*;
import java.awt.*;

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
        tileButton.setIcon(Main.imageAssets.blank);
        tileButton.setBackground(Color.WHITE);
        tileButton.setFont(new Font("Calibri", Font.PLAIN, 60));
        tileButton.setEnabled(false);
        tileButton.addActionListener(e -> {

            // prompt user for tile value on click
            String newValue = GameGUI.getInstance().getNewSquareValue();
            if (newValue == null) {
                return;
            }
            setTileValue(newValue);
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
                tileButton.setIcon(Main.imageAssets.blank);
                break;
            case "1":
                tileButton.setIcon(Main.imageAssets.img1);
                break;
            case "2":
                tileButton.setIcon(Main.imageAssets.img2);
                break;
            case "3":
                tileButton.setIcon(Main.imageAssets.img3);
                break;
            case "4":
                tileButton.setIcon(Main.imageAssets.img4);
                break;
            case "5":
                tileButton.setIcon(Main.imageAssets.img5);
                break;
            case "6":
                tileButton.setIcon(Main.imageAssets.img6);
                break;
            case "7":
                tileButton.setIcon(Main.imageAssets.img7);
                break;
            case "8":
                tileButton.setIcon(Main.imageAssets.img8);
                break;
            case "9":
                tileButton.setIcon(Main.imageAssets.img9);
                break;
        }
        value = val;
    }

}
