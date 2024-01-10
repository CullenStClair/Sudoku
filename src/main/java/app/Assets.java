package main.java.app;

import javax.swing.*;
import java.util.Objects;

/**
 * Represents image assets for the program.
 *
 * @author Cullen St. Clair
 */
public final class Assets {

    public final Icon img1, img2, img3, img4, img5, img6, img7, img8, img9, blank, background;

    public Assets() {

        // load images from assets
        img1 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/1.png")));
        img2 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/2.png")));
        img3 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/3.png")));
        img5 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/5.png")));
        img4 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/4.png")));
        img6 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/6.png")));
        img7 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/7.png")));
        img8 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/8.png")));
        img9 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/9.png")));
        blank = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/blank.png")));
        background = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/background.png")));
    }

}
