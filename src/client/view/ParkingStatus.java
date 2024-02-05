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

        ParkingStatus.NavbarPanel pnlNavbar = new ParkingStatus.NavbarPanel();
        contentArea.add(pnlNavbar, BorderLayout.WEST);

        JPanel pnlMain = new JPanel(new BorderLayout());
        pnlMain.setPreferredSize(new Dimension(1100,700));
        pnlMain.setBackground(res.lightGray);
        pnlMain.setBorder(new EmptyBorder(25,25,25,25));
        contentArea.add(pnlMain, BorderLayout.CENTER);

        MainTopPanel pnlMainTop = new MainTopPanel();
        pnlMain.add(pnlMainTop, BorderLayout.NORTH);

        MainBottomPanel pnlMainBottom = new MainBottomPanel();
        pnlMain.add(pnlMainBottom, BorderLayout.SOUTH);


        this.setContentPane(contentArea);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(1300,800);
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

    class NavbarPanel extends JPanel {
        public NavbarPanel() {
            setBackground(res.feldgrau);

            btnNavHome = res.createBtnIconOnly(res.iconHome, 30,30);
            add(btnNavHome);

            btnNavTicket = res.createBtnIconOnly(res.iconTicket,30,30);
            add(btnNavTicket);

            btnNavAccount = res.createBtnIconOnly(res.iconAccount,30,30);
            add(btnNavAccount);

            btnNavLogout = res.createBtnIconOnly(res.iconLogout,30,30);
            add(btnNavLogout);

            this.setPreferredSize(new Dimension(60, 800));
        }
    }

    class MainTopPanel extends JPanel {
        public MainTopPanel() {
            setBackground(res.white);
            setLayout(new BorderLayout());

            this.setPreferredSize(new Dimension(1300,160));
        }
    }

    class MainBottomPanel extends JPanel {
        public MainBottomPanel() {
            setBackground(res.white);
            setForeground(res.white);
            setBorder(new Resources.RoundedBorder(20));

            setPreferredSize(new Dimension(1300,515));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ParkingStatus();
            }
        });
    }
}


