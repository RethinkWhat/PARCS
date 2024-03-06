package server.view;

import utilities.Resources;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The panel of DashboardView.
 * Holds all the information of the car and motor bookings and number of each booking.
 */
public class DashboardView extends JPanel {
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
     * The full name of the current admin.
     */
    private JLabel lblName;
    /**
     * The button to refresh the lists of car bookings and motor bookings.
     */
    private JButton btnRefresh;
    /**
     * The stylesheet.
     */
    private static Resources res = new Resources();
    /**
     * Instance variable of GridBagConstraints used for JPanels using GridBagLayout.
     */
    private GridBagConstraints gbc;
    /**
     * The CardLayout that controls the components of the MainTopPanel.
     */
    private CardLayout topCardLayout = new CardLayout();
    /**
     * The top panel of the main page.
     */
    private MainTopPanel pnlMainTop;
    /**
     * The bottom panel of the main page.
     */
    private MainBottomPanel pnlMainBottom;

    /**
     * Constructs a panel of DashboardView.
     */
    public DashboardView() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(25,25,25,25));

        // Main Panel
        add(pnlMainTop = new MainTopPanel(), BorderLayout.NORTH);
        add(pnlMainBottom = new MainBottomPanel(), BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(1100,700));
    }

    /**
     * Retrieves the current JButton of btnAvailCar.
     * @return The current btnAvailCar.
     */
    public JButton getBtnAvailCar() {
        return btnAvailCar;
    }

    /**
     * Retrieves the current JButton of btnAvailMotor.
     * @return The current btnAvailMotor.
     */
    public JButton getBtnAvailMotor() {
        return btnAvailMotor;
    }

    /**
     * Retrieves the current btnTot
     * @return
     */
    public JButton getBtnTotalBookings() {
        return btnTotalBookings;
    }

    public MainTopPanel getPnlMainTop() {
        return pnlMainTop;
    }

    public MainBottomPanel getPnlMainBottom() {
        return pnlMainBottom;
    }

    /**
     * Retrieves the current JButton of btnRefresh.
     * @return The current btnRefresh.
     */
    public JButton getBtnRefresh() {
        return btnRefresh;
    }

    /**
     * Sets a specified action listener for btnRefresh.
     * @param actionListener The specified action listener.
     */
    public void setRefreshListener(ActionListener actionListener) {
        btnRefresh.addActionListener(actionListener);
    }

    /**
     * The panel that contains the parking slots.
     */
    public class MainBottomPanel extends JPanel {
        /**
         * The panel that holds the different components.
         */
        private JPanel container;

        /**
         * Constructs a panel of MainBottomPanel.
         */
        public MainBottomPanel() {
            setLayout(new BorderLayout());
            setBackground(res.lightGray);

            GridLayout gridLayout = new GridLayout(0,2);
            gridLayout.setHgap(20);

            container = new JPanel(gridLayout);
            container.setPreferredSize(new Dimension(1300,510));
            container.setBackground(res.lightGray);
            add(container, BorderLayout.CENTER);

            setPreferredSize(new Dimension(1300, 510));
            setVisible(true);
        }
    }

    /**
     * The panel that contains multiple objects of the ButtonsPanel.
     */
    public class MainTopPanel extends JPanel {
        /**
         * The label for the total car bookings.
         */
        private JLabel lblCarCount;
        /**
         * The label for car.
         */
        private JLabel lblAvailCar;
        /**
         * The label for the total motor bookings.
         */
        private JLabel lblMotorCount;
        /**
         * The label for motor.
         */
        private JLabel lblAvailMotor;
        /**
         * The label for the total bookings (car and motor).
         */
        private JLabel lblTotalCount;
        /**
         * The label for total
         */
        private JLabel lblTotal;
        /**
         * Constructs a panel of MainTopPanel.
         */
        public MainTopPanel() {
            setBackground(res.lightGray);
            setLayout(new BorderLayout());

            JPanel pnlInformation = new JPanel(new GridBagLayout());
            pnlInformation.setPreferredSize(new Dimension(1300,50));
            add(pnlInformation, BorderLayout.NORTH);
            pnlInformation.setBackground(res.lightGray);

            gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.ipadx = 900;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;
            lblName = res.createLblH2("Hello, " + "Admin"+ "!", res.eerieBlack);
            pnlInformation.add(lblName, gbc);

            gbc.gridx = 1;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.EAST;
            btnRefresh = res.createBtnIconOnly(res.iconRefresh, 30,30);
            pnlInformation.add(btnRefresh, gbc);


            GridLayout gridLayout = new GridLayout(0,3);
            gridLayout.setHgap(10);

            JPanel pnlButtons = new JPanel(gridLayout);
            pnlButtons.setPreferredSize(new Dimension(1300,100));
            pnlButtons.setBorder(new EmptyBorder(10,0,10,0));
            pnlButtons.setBackground(res.lightGray);
            add(pnlButtons, BorderLayout.SOUTH);


            // Update parameters as models from controller when backend has commenced.
            ButtonPanel pnlAvailCar = new ButtonPanel(
                    btnAvailCar = res.createBtnIconOnly(res.iconSolidCar, 50, 50),
                    lblCarCount = res.createLblH1("13", res.eerieBlack),
                    lblAvailCar = res.createLblP("<html>Total<br> Car Bookings</html>", res.eerieBlack)
            );
            pnlButtons.add(pnlAvailCar);

            ButtonPanel pnlAvailMotor = new ButtonPanel(
                    btnAvailMotor = res.createBtnIconOnly(res.iconSolidMotor, 50, 50),
                    lblMotorCount = res.createLblH1("10", res.eerieBlack),
                    lblAvailMotor = res.createLblP("<html>Total<br> Motor Bookings</html>", res.eerieBlack)
            );
            pnlButtons.add(pnlAvailMotor);

            ButtonPanel pnlTotalBookings = new ButtonPanel(
                    btnTotalBookings = res.createBtnIconOnly(res.iconSolidTicket, 50,50),
                    lblTotalCount = res.createLblH1("3", res.eerieBlack),
                    lblTotal = res.createLblP("<html>Total<br> Bookings</html>", res.eerieBlack)
            );
            pnlButtons.add(pnlTotalBookings);

            this.setPreferredSize(new Dimension(1300,150));
        }

        /**
         * Retrieves the current JLabel of lblCarCount.
         * @return The current lblCarCount.
         */
        public JLabel getLblCarCount() {
            return lblCarCount;
        }

        /**
         * Retrieves the current JLabel of lblTotalCount.
         * @return The current lblTotalCount.
         */
        public JLabel getLblTotalCount() {
            return lblTotalCount;
        }

        /**
         * Retrieves the current JLabel of lblAvailCar.
         * @return The current lblAvailCar.
         */
        public JLabel getLblAvailCar() {
            return lblAvailCar;
        }

        /**
         * Retrieves the current JLabel of lblTotal.
         * @return The current lblTotal.
         */
        public JLabel getLblTotal() {
            return lblTotal;
        }
    }

    /**
     * The panel that creates the useful buttons of the home page.
     */
    public class ButtonPanel extends JPanel {
        /**
         * The rounded panel.
         */
        private JPanel container;

        /**
         * Constructs a panel with a specified button, number label, and title label.
         */
        public ButtonPanel(JButton button, JLabel number, JLabel title) {
            setLayout(new BorderLayout());

            container = res.createPnlRounded(100, 80, res.white, res.lightGray);
            container.setLayout(new GridLayout(0, 3));
            add(container, BorderLayout.NORTH);

            container.add(button);
            container.add(number);
            container.add(title);

            this.setPreferredSize(new Dimension(100, 100));
        }
    }
}
