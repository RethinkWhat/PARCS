package view;

import javax.swing.*;
import java.awt.*;

/**
 * The stylesheet of the different view classes.
 */
public class Resources {

    /**
     * Main brand color
     */
    public final Color celadon = new Color(128,207,168);
    /**
     * Secondary brand color
     */
    public final Color feldgrau = new Color(76,102,99);
    /**
     * Solid color
     */
    public final Color eerieBlack = new Color(17,29, 19);
    /**
     * Solid color
     */
    public final Color white = new Color(255,255,255);
    /**
     * I/O color
     */
    public final Color lightGray = new Color(239,239,239);
    /**
     * I/O color
     */
    public final Color gray = new Color(133,133,133);

    public Resources() {
    }

    public final int resTitleLocY = 25;

    public ImageIcon logoParcs = new ImageIcon("res/drawable/parcs-logo.png");

    /**
     * Creates a new JLabel with a specified text and color.
     * The JLabel is a heading (h1).
     * @param text The specified text.
     * @param color The specified color.
     * @return JLabel with specified text and color in an H1 format.
     */
    public JLabel createLblH1(String text, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 26));
        label.setForeground(color);
        return label;
    }

    /**
     * Creates a new JLabel with a specified text and color.
     * The JLabel is a heading (h2).
     * @param text The specified text.
     * @param color The specified color.
     * @return JLabel with specified text and color in an H2 format.
     */
    public JLabel createLblH2(String text, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 22));
        label.setForeground(color);
        return label;
    }

    /**
     * Creates a new JLabel with a specified text and color.
     * The JLabel is a heading (h3).
     * @param text The specified text.
     * @param color The specified color.
     * @return JLabel with specified text and color in an H3 format.
     */
    public JLabel createLblH3(String text, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(color);
        return label;
    }

    /**
     * Creates a new JLabel with a specified text and color.
     * The JLabel is a paragraph.
     * @param text The specified text.
     * @param color The specified color.
     * @return JLabel with specified text and color in a paragraph format.
     */
    public JLabel createLblP(String text, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(color);
        return label;
    }
}
