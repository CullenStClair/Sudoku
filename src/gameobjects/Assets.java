package gameobjects;

import javax.swing.*;

/**
 * Represents image assets for the program.
 *
 * @author Cullen St. Clair
 */
public final class Assets {

    public final Icon img1, img2, img3, img4, img5, img6, img7, img8, img9, blank, background;

    public Assets() {

        // load images from assets
        img1 = new ImageIcon(getClass().getResource("/resources/1.png"));
        img2 = new ImageIcon(getClass().getResource("/resources/2.png"));
        img3 = new ImageIcon(getClass().getResource("/resources/3.png"));
        img4 = new ImageIcon(getClass().getResource("/resources/4.png"));
        img5 = new ImageIcon(getClass().getResource("/resources/5.png"));
        img6 = new ImageIcon(getClass().getResource("/resources/6.png"));
        img7 = new ImageIcon(getClass().getResource("/resources/7.png"));
        img8 = new ImageIcon(getClass().getResource("/resources/8.png"));
        img9 = new ImageIcon(getClass().getResource("/resources/9.png"));
        blank = new ImageIcon(getClass().getResource("/resources/blank.png"));
        background = new ImageIcon(getClass().getResource("/resources/background.png"));
    }

}
