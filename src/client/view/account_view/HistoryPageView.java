package client.view.account_view;

import utilities.Resources;

import javax.swing.*;
import java.awt.*;

public class HistoryPageView extends JFrame {
    private Resources resources = new Resources();


    private JButton homeButton;
    private JButton nxtPage;
    private JButton prevPage;


    public HistoryPageView() {
        super("HISTORY PAGE");


        Container contentArea = getContentPane();
        JPanel mainPanel = new JPanel();
        contentArea.add(mainPanel);
        mainPanel.setBackground(resources.lightGray);

        JPanel leftSidePanel = new JPanel();

        leftSidePanel.setBackground(resources.feldgrau);
        leftSidePanel.setPreferredSize(new Dimension(75,560));
        contentArea.add(leftSidePanel,BorderLayout.WEST);

        JPanel whitePanel = new JPanel(); // Create the white panel
        whitePanel.setBackground(Color.WHITE); // Set the background color to white

        whitePanel.setPreferredSize(new Dimension(820, 500));
        mainPanel.add(whitePanel, BorderLayout.CENTER);

        homeButton = new JButton("Home");
        leftSidePanel.add(homeButton);

        nxtPage = new JButton("NEXT");
        prevPage = new JButton("PREVIOUS");

        whitePanel.add(prevPage);
        whitePanel.add(nxtPage);

        setSize(950, 560);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HistoryPageView();
            }
        });
    }
}







