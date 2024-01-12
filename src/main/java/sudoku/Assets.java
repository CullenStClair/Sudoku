package sudoku;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Represents image assets for the program.
 *
 * @author Cullen St. Clair
 */
public class Assets {

    public static Icon getIcon(String id, int width, int height) {

        if ("-".equals(id)) {
            id = "blank";
        }

        ImageIcon icon;
        try {
            icon = new ImageIcon(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("image/" + id + ".png")));
        } catch (Exception e) {
            throw new IllegalArgumentException("no corresponding icon \"" + id + "\"");
        }

        if (!"background".equals(id)) {
            icon = new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        }

        return (Icon) icon;
    }
}
