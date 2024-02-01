package client.view;

import utilities.Resources;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReservationPage extends JFrame {
    /**
     * The button that expands the navigation bar.
     */
    private JButton btnNavMenu;
    /**
     * The home button in the navigation bar.
     */
    private JButton btnNavHome;
    /**
     * The ticket button in the navigation.
     */
    private JButton btnNavTicket;
    /**
     * The account button in the navigation.
     */
    private JButton btnNavAccount;
    /**
     * The logout button in the navigation.
     */
    private JButton btnNavLogout;
    /**
     * The button that shows the current number of available car slots.
     */
    private JButton btnAvailCar;
    /**
     * The button that shows the current number of available motorcycle slots.
     */
    private JButton btnAvailMotor;
    /**
     * The button that shows the current number of total bookings.
     */
    private JButton btnTotalBookings;
    /**
     * The search bar.,
     */
    private JTextField txtSearchbar;
    /**
     * The first name of the current user.
     */
    private JLabel lblName;
    /**
     * The current date.
     */
    private JLabel lblDate;
    /**
     * Location of the current user.
     */
    private JLabel lblLocation;
    /**
     * The stylesheet.
     */
    private Resources res = new Resources();

    public ReservationPage() {
        super("PARCS");

        // Body panel acting as a container to hold all UI components
        Container contentArea = new JPanel(new BorderLayout());

        HeaderPanel pnlHeader = new HeaderPanel();
        contentArea.add(pnlHeader, BorderLayout.NORTH);

        NavbarPanel pnlNavbar = new NavbarPanel();
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
            setLayout(new FlowLayout(FlowLayout.LEFT));

            btnNavMenu = res.createBtnIconOnly(res.logoParcs, 20,20);
            add(btnNavMenu);

            this.setPreferredSize(new Dimension(1300, 40));
        }
    }

    class NavbarPanel extends JPanel {
        public NavbarPanel() {
            setBackground(res.feldgrau);

            btnNavHome = res.createBtnIconOnly(res.iconHome,30,30);
            add(btnNavHome);

            btnNavTicket = res.createBtnIconOnly(res.iconTicket, 30,30);
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
            setBackground(res.lightGray);
            setLayout(new BorderLayout());

            String userFirstName = "Ramon Emmiel";
            lblName = new JLabel("Hello, " + userFirstName);
            add(lblName);

            JPanel pnlButtons = new JPanel(new GridLayout(0,3));
            pnlButtons.setBackground(res.lightGray);
            add(pnlButtons, BorderLayout.SOUTH);

            JPanel pnlAvailCar = new JPanel();
            pnlAvailCar.setBackground(res.white);
            pnlButtons.add(pnlAvailCar);

            this.setPreferredSize(new Dimension(1300,300));
        }
    }

    class MainBottomPanel extends JPanel {
        public MainBottomPanel() {
            setBackground(res.white);
            setForeground(res.white);
            setBorder(new Resources.RoundedBorder(20));

            setPreferredSize(new Dimension(1300,500));
        }
    }

    // Temporary main method for testing. Omit before production.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ReservationPage();
            }
        });
    }
}
