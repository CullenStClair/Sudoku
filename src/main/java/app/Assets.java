package app;

import javax.swing.*;
import java.util.Objects;

/**
 * Represents image assets for the program.
 *
 * @author Cullen St. Clair
 */
public class Assets {

    private static Assets instance;
    private final Icon[] icons;

    private Assets() {

        icons = new Icon[11];

        // load images from assets
        icons[0] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/1.png")));
        icons[1] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/2.png")));
        icons[2] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/3.png")));
        icons[3] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/5.png")));
        icons[4] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/4.png")));
        icons[5] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/6.png")));
        icons[6] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/7.png")));
        icons[7] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/8.png")));
        icons[8] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/9.png")));
        icons[9] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/blank.png")));
        icons[10] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/background.png")));
    }

    public static Assets getInstance() {
        if (instance == null) {
            instance = new Assets();
        }
        return instance;
    }

    public Icon getIcon(String id) {
        int index;
        try {
            index = Integer.parseInt(id) - 1;
        } catch (NumberFormatException e) {
            index = switch (id) {
                case "-", "blank" -> 9;
                case "background" -> 10;
                case null, default -> throw new IllegalArgumentException("no corresponding icon \"" + id + "\"");
            };
        }
        return icons[index];
    }
}
