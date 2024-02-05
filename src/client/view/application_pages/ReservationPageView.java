package client.view.application_pages;

import utilities.Resources;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReservationPageView extends JPanel {
    /**
     *
     */
    private JPanel pnlCards;
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
     * The stylesheet.
     */
    private Resources res = new Resources();
    /**
     * Instance variable of GridBagConstraints used for JPanels using GridBagLayout.
     */
    private GridBagConstraints gbc;
    /**
     * The CardLayout that controls the components of the MainTopPanel.
     */
    private CardLayout topCardLayout = new CardLayout();

    /**
     * Constructs a panel of ReservationPageView.
     */
    public ReservationPageView() {
        setLayout(new BorderLayout());

        pnlCards = new JPanel(topCardLayout);
        add(pnlCards, BorderLayout.NORTH);

        // Top Panel
        pnlCards.add(new MainTopPanel(), "dashboard");
        pnlCards.add(new ParkingSlotButtonsView(), "buttons");

        // Bottom Panel
        add(new MainBottomPanel(), BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(1100,700));
    }

    /**
     * The panel that contains the parking slots.
     */
    class MainBottomPanel extends JPanel {
        /**
         * The rounded panel.
         */
        private JPanel container;

        /**
         * Constructs a panel of MainBottomPanel.
         */
        public MainBottomPanel() {
            setLayout(new BorderLayout());

            container = res.createPnlRounded(1300,520, res.white, res.lightGray);
            add(container, BorderLayout.CENTER);

            setPreferredSize(new Dimension(1300,520));
        }
    }

    /**
     * The panel that contains multiple objects of the ButtonsPanel.
     */
    class MainTopPanel extends JPanel {
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
            gbc.ipadx = 475;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;
            String userFirstName = "Ramon Emmiel";
            lblName = res.createLblH2("Hello, " + userFirstName + "!", res.eerieBlack);
            pnlInformation.add(lblName, gbc);

            gbc.gridy = 1;
            gbc.gridwidth = 3;
            String date = "January 28, 2023 | Wednesday 10:28 AM";
            lblDate = res.createLblH4(date, res.eerieBlack);
            pnlInformation.add(lblDate, gbc);

            gbc.gridy = 0;
            gbc.gridx = 1;
            gbc.gridwidth = 5;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.fill = GridBagConstraints.BOTH;
            txtSearchbar = res.createTxtRounded("Search date",res.white,res.gray, 30);
            pnlInformation.add(txtSearchbar, gbc);

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
                    res.createLblH1("13", res.eerieBlack),
                    res.createLblP("<html>Available<br> Car Slots</html>", res.eerieBlack)
            );
            pnlButtons.add(pnlAvailCar);

            ButtonPanel pnlAvailMotor = new ButtonPanel(
                    btnAvailMotor = res.createBtnIconOnly(res.iconSolidMotor, 50, 50),
                    res.createLblH1("10", res.eerieBlack),
                    res.createLblP("<html>Available<br> Motor Slots</html>", res.eerieBlack)
            );
            pnlButtons.add(pnlAvailMotor);

            ButtonPanel pnlTotalBookings = new ButtonPanel(
                    btnTotalBookings = res.createBtnIconOnly(res.iconSolidTicket, 50,50),
                    res.createLblH1("3", res.eerieBlack),
                    res.createLblP("<html>Your Total<br> Bookings</html>", res.eerieBlack)
            );
            pnlButtons.add(pnlTotalBookings);

            this.setPreferredSize(new Dimension(1300,150));
        }
    }

    /**
     * The panel that creates the useful buttons of the home page.
     */
    class ButtonPanel extends JPanel {
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
