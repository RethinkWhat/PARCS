package client.view;

import utilities.Resources;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ParkingStatus extends JFrame {
    private JButton btnNavHome;
    private JButton btnNavTicket;
    private JButton btnNavAccount;
    private JButton btnNavLogout;
    private JButton btnbtnParkingSlotOne;
    private JButton btnReserveSlot;

    private Resources res = new Resources();

    public ParkingStatus() {
        super("PARCS");

        // Body panel acting as a container to hold all UI components
        Container contentArea = new JPanel(new BorderLayout());

        ParkingStatus.HeaderPanel pnlHeader = new ParkingStatus.HeaderPanel();
        contentArea.add(pnlHeader, BorderLayout.NORTH);


        JPanel pnlMain = new JPanel(new BorderLayout());
        pnlMain.setPreferredSize(new Dimension(1100, 700));
        pnlMain.setBackground(res.lightGray);
        pnlMain.setBorder(new EmptyBorder(25, 25, 25, 25));
        contentArea.add(pnlMain, BorderLayout.CENTER);


        this.setContentPane(contentArea);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(1300, 800);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    class HeaderPanel extends JPanel {
        public HeaderPanel() {
            setBackground(res.celadon);

            this.setPreferredSize(new Dimension(1300, 40));
        }
    }

}
