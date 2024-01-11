package gameobjects;

import app.Assets;
import app.GameGUI;
import app.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a tile on the board.
 *
 * @author Cullen St. Clair
 */
public class Tile {

    private final JButton tileButton;

    private String value = "-";
    private final int buttonSize = GameGUI.SIDE_LENGTH / Main.GRID_SIZE;

    public Tile() {

        // create tile button
        tileButton = new JButton();
        tileButton.setIcon(Assets.getIcon("blank", buttonSize, buttonSize));
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
        tileButton.setIcon(Assets.getIcon(val, buttonSize, buttonSize));
        this.value = val;
    }

}
