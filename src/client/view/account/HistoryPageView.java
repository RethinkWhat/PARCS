package client.view.account;

import utilities.Resources;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the history page view in the application.
 */
public class HistoryPageView extends JFrame {
    private Resources resources = new Resources();

    private JButton homeButton;
    private JButton nxtPage;
    private JButton prevPage;
    private JButton logoutButton;

    /**
     * Constructs a new HistoryPageView.
     */
    public HistoryPageView() {
        super("HISTORY PAGE");

        Container contentArea = getContentPane();
        JPanel mainPanel = new JPanel();
        contentArea.add(mainPanel);
        mainPanel.setBackground(resources.lightGray);

        // Left side panel for home and logout buttons
        JPanel leftSidePanel = new JPanel(new BorderLayout());
        leftSidePanel.setBackground(resources.feldgrau);
        leftSidePanel.setPreferredSize(new Dimension(60, 560));
        contentArea.add(leftSidePanel, BorderLayout.WEST);

        // Top panel for additional content
        JPanel topPanel = new JPanel();
        topPanel.setBackground(resources.celadon);
        topPanel.setPreferredSize(new Dimension(820, 30));
        contentArea.add(topPanel, BorderLayout.NORTH);

        // Center panel for main content
        JPanel whitePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add insets for spacing

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.lightGray);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1; // Allow panel to resize horizontally
        gbc.weighty = 1; // Allow panel to resize vertically
        gbc.fill = GridBagConstraints.BOTH; // Allow panel to resize in both directions
        whitePanel.add(panel1, gbc);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.lightGray);
        gbc.gridx = 1;
        panel2.setPreferredSize(new Dimension(100,100));
        whitePanel.add(panel2, gbc);

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.lightGray);
        panel3.setPreferredSize(new Dimension(100,100));
        gbc.gridx = 0;
        gbc.gridy = 1;
        whitePanel.add(panel3, gbc);

        JPanel panel4 = new JPanel();
        panel4.setBackground(Color.lightGray);
        panel4.setPreferredSize(new Dimension(100,100));
        gbc.gridx = 1;
        whitePanel.add(panel4, gbc);


        whitePanel.setPreferredSize(new Dimension(820, 445));
        whitePanel.setBackground(resources.white);
        mainPanel.add(whitePanel, BorderLayout.CENTER);

        // Home button
        homeButton = new JButton();
        ImageIcon homeIcon = new ImageIcon("res/drawable/icons/home-white-outline.png");
        ImageIcon resizedHomeIcon = new ImageIcon(homeIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        homeButton.setIcon(resizedHomeIcon);
        homeButton.setContentAreaFilled(false);
        homeButton.setPreferredSize(new Dimension(70, 70));
        leftSidePanel.add(homeButton, BorderLayout.NORTH);

        // Logout button
        logoutButton = new JButton();
        ImageIcon logoutIcon = new ImageIcon("res/drawable/icons/exit-white-outline.png");
        ImageIcon resizedLogoutIcon = new ImageIcon(logoutIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        logoutButton.setIcon(resizedLogoutIcon);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setPreferredSize(new Dimension(70, 70));
        leftSidePanel.add(logoutButton, BorderLayout.SOUTH);

        // Previous and Next buttons
        prevPage = resources.createBtnRounded("PREV", resources.gray, resources.eerieBlack, 3);
        nxtPage = resources.createBtnRounded("NEXT", resources.celadon, resources.eerieBlack, 3);
        mainPanel.add(prevPage);
        mainPanel.add(nxtPage);

        setSize(950, 560);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * For testing the GUI
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(HistoryPageView::new);
    }
}
