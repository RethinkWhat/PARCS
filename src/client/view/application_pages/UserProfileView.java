package client.view.application_pages;

import utilities.Resources;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserProfileView extends JPanel {
    /**
     * The stylesheet
     */
    Resources res = new Resources();
    /**
     * Instance variable of grid bag constraints used in grid bag layout.
     */
    GridBagConstraints gbc = new GridBagConstraints();
    /**
     * The 
     */
    private JLabel btnHome;
    /**
     * The edit profile button.
     */
    private JButton btnNavEditProfile;
    /**
     * The edit cars button.
     */
    private JButton btnNavEditCars;
    /**
     * The security button.
     */
    private JButton btnNavSecurity;
    /**
     * The history button.
     */
    private JButton btnNavHistory;
    /**
     * The exit/logout button.
     */
    private JButton btnNavExit;
    /**
     * The CardLayout that controls the components of pnlCards.
     */
    private CardLayout cardLayout;
    /**
     * The panel that holds different component panels.
     */
    private JPanel pnlCards;
    /**
     * The panel for edit profile.
     */
    private EditProfile pnlEditProfile;
    /**
     * The panel for profile options (account navigation)
     */
    private ProfileOptionsPanel pnlProfileOptions;
    /**
     * The panel for edit cars.
     */
    private EditCars pnlEditCars;

    /**
     * Constructs a panel of UserProfileView.
     */
    public UserProfileView() {
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();

        JPanel pnlRight = new JPanel(new BorderLayout());
        pnlRight.setPreferredSize(new Dimension(750,700));
        add(pnlRight, BorderLayout.EAST);
        
        pnlCards = new JPanel(cardLayout);
        pnlCards.setPreferredSize(new Dimension(750,560));
        // pnlCards.setBounds(220,0,750,560);
        pnlRight.add(pnlCards, BorderLayout.NORTH);

        // edit profile page
        pnlCards.add(pnlEditProfile = new EditProfile(), "profile");

        // edit cars page
        pnlCards.add(pnlEditCars = new EditCars(), "vehicles");

        //shows edit profile first
        cardLayout.show(pnlCards, "profile");

        // account navigation panel
        pnlProfileOptions = new ProfileOptionsPanel(cardLayout, pnlCards);
        add(pnlProfileOptions, BorderLayout.WEST);

        this.setPreferredSize(new Dimension(1100,700));
    }

    /**
     * The panel that contains the sidebar options.
     */
    class ProfileOptionsPanel extends JPanel {
        /**
         * Constructs a panel of ProfileOptions with a specified layout manager and main panel to hold the components.
         * @param cardLayout The specified CardLayout layout manager.
         * @param pnlCards The specified main panel.
         */
        public ProfileOptionsPanel(CardLayout cardLayout, JPanel pnlCards) {
            this.setLayout(null);
            this.setBackground(res.white);
            this.setVisible(true);

            btnHome = new JLabel();
            btnHome.setIcon(new ImageIcon("res/drawable/icons/return.png"));
            btnHome.setBounds(40, 5, 100, 100);
            this.add(btnHome);

            ImageIcon profilePicture = new ImageIcon("res/drawable/icons/pfp.png");
            ImageIcon profileImage = new ImageIcon(profilePicture.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            JLabel lblPFP = new JLabel(profileImage);
            lblPFP.setBounds(75, 25, 100, 100);
            this.add(lblPFP);

            JPanel buttonsPanel = new JPanel(new GridLayout(5, 1));
            buttonsPanel.setBackground(res.white);
            buttonsPanel.setBounds(50, 150, 150, 300);
            this.add(buttonsPanel);

            ImageIcon editProfile = new ImageIcon("res/drawable/icons/edit.png");
            btnNavEditProfile = res.createBtnTxtOnly("Edit Profile", res.gray);
            btnNavEditProfile.setHorizontalAlignment(SwingConstants.LEFT);
            ImageIcon editProfileResized = new ImageIcon(editProfile.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            btnNavEditProfile.setIcon(editProfileResized);
            buttonsPanel.add(btnNavEditProfile);

            ImageIcon editCars = new ImageIcon("res/drawable/icons/edit-cars.png");
            btnNavEditCars = res.createBtnTxtOnly("Edit Vehicles", res.gray);
            btnNavEditCars.setHorizontalAlignment(SwingConstants.LEFT);
            ImageIcon editCarsResized = new ImageIcon(editCars.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            btnNavEditCars.setIcon(editCarsResized);
            buttonsPanel.add(btnNavEditCars);

            ImageIcon security = new ImageIcon("res/drawable/icons/security.png");
            btnNavSecurity = res.createBtnTxtOnly("Security", res.gray);
            btnNavSecurity.setHorizontalAlignment(SwingConstants.LEFT);
            ImageIcon securityResized = new ImageIcon(security.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            btnNavSecurity.setIcon(securityResized);
            buttonsPanel.add(btnNavSecurity);

            ImageIcon historyIcon = new ImageIcon("res/drawable/icons/history.png");
            btnNavHistory = res.createBtnTxtOnly("History", res.gray);
            btnNavHistory.setHorizontalAlignment(SwingConstants.LEFT);
            ImageIcon helpResized = new ImageIcon(historyIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            btnNavHistory.setIcon(helpResized);
            buttonsPanel.add(btnNavHistory);

            ImageIcon exitIcon = new ImageIcon("res/drawable/icons/exit-grey-outline.png");
            btnNavExit = res.createBtnTxtOnly("Logout", res.gray);
            btnNavExit.setHorizontalAlignment(SwingConstants.LEFT);
            ImageIcon exitResized = new ImageIcon(exitIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            btnNavExit.setIcon(exitResized);
            buttonsPanel.add(btnNavExit);

            this.setPreferredSize(new Dimension(350,700));
        }
    }

    /**
     * The panel that contains pertinent information of the user. The user can edit or simply view their information
     * on this page.
     */
    public class EditProfile extends JPanel {
        /**
         * The text field where the first name of the user can be viewed and edited.
         */
        private JTextField txtFirstName;
        /**
         * The text field where the last name of the user can be viewed and edited.
         */
        private JTextField txtLastName;
        /**
         * The text field where the username of the user can be viewed and edited.
         */
        private JTextField txtUsername;
        /**
         * The text field where the contact number of the user can be viewed and edited.
         */
        private JTextField txtContact;
        /**
         * The cancel button.
         */
        private JButton btnCancel;
        /**
         * The continue button.
         */
        private JButton btnContinue;
        
        /**
         * Constructs a panel of EditProfile.
         */
        public EditProfile() {
            // this.setPreferredSize(new Dimension(650,490));
            this.setLayout(new GridLayout(8,1));
            this.setBorder(new EmptyBorder(10,20,10,60));

            JLabel lblEditProfile = res.createLblH1("Edit Profile", res.eerieBlack);
            lblEditProfile.setBorder(new EmptyBorder(25,0,0,0));
            lblEditProfile.setFont(new Font("Arial", Font.BOLD, 32));
            this.add(lblEditProfile);

            JPanel pnlNameLabels = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JLabel lblFirstName = res.createLblH3("First Name", res.eerieBlack);
            lblFirstName.setBorder(new EmptyBorder(30,0,0,0));

            pnlNameLabels.add(lblFirstName);

           // nameLabelsPanel.add(firstNameLabel);
            JLabel lastNameLabel = res.createLblH3("Last Name", res.eerieBlack);
            lastNameLabel.setBorder(new EmptyBorder(30,180,0,0));
            pnlNameLabels.add(lastNameLabel);

            this.add(pnlNameLabels);

            JPanel pnlNameField = new JPanel(new FlowLayout(FlowLayout.LEFT));

            // TODO: Add attributes of first name, last name, email, and contact number in the UserProfileModel.
            txtFirstName = res.createTxtRounded("Patrick", res.white, res.gray,20);
            txtFirstName.setPreferredSize(new Dimension(10,50));

            txtLastName = res.createTxtRounded("Bozorgi", res.white, res.gray,20);
            txtLastName.setPreferredSize(new Dimension(10,50));

            pnlNameField.add(txtFirstName);
            pnlNameField.add(txtLastName);

            this.add(pnlNameField);

            JLabel lblUsername = res.createLblH3("Username", res.eerieBlack);
            lblUsername.setBorder(new EmptyBorder(30,0,0,0));
            this.add(lblUsername);

            txtUsername = res.createTxtRounded("Username", res.lightGray, res.gray,20);
            txtUsername.setSize(new Dimension(50,50));
            txtUsername.setFocusable(false);
            this.add(txtUsername);

            JLabel lblContact = res.createLblH3("Contact", res.eerieBlack);
            lblContact.setBorder(new EmptyBorder(30,0,0,0));
            this.add(lblContact);

            txtContact = res.createTxtRounded("#############", res.white, res.gray,20);
            this.add(txtContact);

            JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.LEADING));
            pnlButtons.setBorder(new EmptyBorder(0,0,5,0));

            btnCancel = res.createBtnRounded("Cancel", res.feldgrau,res.eerieBlack,30);
            btnCancel.setPreferredSize(new Dimension(130,40));
            pnlButtons.add(btnCancel);

            btnContinue = res.createBtnRounded("Continue", res.feldgrau,res.eerieBlack,30);
            btnContinue.setPreferredSize(new Dimension(130,40));
            pnlButtons.add(btnContinue);
            this.add(pnlButtons);
        }

        /**
         * Sets a specified action listener for btnContinue.
         * @param actionListener The specified action listener.
         */
        public void setContinueListener(ActionListener actionListener) {
            btnContinue.addActionListener(actionListener);
        }

        /**
         * Sets a specified action listener for btnCancel.
         * @param actionListener The specified action listener.
         */
        public void setCancelListener(ActionListener actionListener) {
            btnCancel.addActionListener(actionListener);
        }

        /**
         * Retrieves the current JTextField of txtFirstName.
         * @return The current txtFirstName.
         */
        public JTextField getTxtFirstName() {
            return txtFirstName;
        }

        /**
         * Retrieves the current JTextField of txtLastName.
         * @return The current txtLastName.
         */
        public JTextField getTxtLastName() {
            return txtLastName;
        }

        /**
         * Retrieves the current JTextField of txtUsername.
         * @return The current txtUsername.
         */
        public JTextField getTxtUsername() {
            return txtUsername;
        }

        /**
         * Retrieves the current JTextField of txtContact.
         * @return The current txtContact
         */
        public JTextField getTxtContact() {
            return txtContact;
        }

        /**
         * Retrieves the current value of txtFirstName.
         * @return The current firstName.
         */
        public String getFirstName() {
            return txtFirstName.getText();
        }

        /**
         * Retrieves the current value of txtLastName.
         * @return The current LastName.
         */
        public String getLastName() {
            return txtLastName.getText();
        }

        /**
         * Retrieves the current value of txtUsername.
         * @return The current Username.
         */
        public String getUsername() {
            return txtUsername.getText();
        }

        /**
         * Retrieves the current value of txtContact.
         * @return The current Contact
         */
        public String getContact() {
            return txtContact.getText();
        }
    }

    /**
     * The panel that contains the information on the vehicle of the users that can be edited or add new vehicles.
     */
    class EditCars extends JPanel {
        /**
         * Constructs a panel of EditCars.
         */
        public EditCars() {
            this.setBounds(220,0,750,560);
            this.setVisible(true);
            this.setLayout(new GridBagLayout());

            gbc.gridy=0;
            gbc.anchor = GridBagConstraints.WEST;
            JLabel lblEditProfile = res.createLblH1("Edit Cars", res.eerieBlack);
            lblEditProfile.setBorder(new EmptyBorder(30,20,0,0));
            this.add(lblEditProfile,gbc);

            gbc.gridy=1;
            gbc.anchor = GridBagConstraints.WEST;
            
            // TODO: Create method in UserProfileModel to create information of the cars.
            JPanel pnlCar1 = createCarsLayout("A-0130934023", "Sedan", "Honda Civic");
            JPanel pnlCar2 = createCarsLayout("A-0130934023", "Sedan", "Honda Civic");
            this.add(pnlCar1,gbc);
            this.add(pnlCar2,gbc);

            JPanel pnlCar3 = createCarsLayout("A-0130934023", "Sedan", "Honda Civic");
            JPanel pnlCar4 = createCarsLayout("A-0130934023", "Sedan", "Honda Civic");
            gbc.gridy=2;
            gbc.insets = new Insets(20,30,0,30);
            this.add(pnlCar3,gbc);
            this.add(pnlCar4,gbc);

            gbc.gridy=5;
            gbc.gridx = 1;
            JButton btnBack = new JButton();
            ImageIcon backIcon = new ImageIcon("res/drawable/icons/forward.png");
            btnBack.setBorder(new EmptyBorder(0,0,0,20));
            ImageIcon resizedBackIcon = new ImageIcon(backIcon.getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH));
            btnBack.setIcon(resizedBackIcon);
            this.add(btnBack,gbc);

            this.setVisible(true);
        }

        /**
         * Creates a panel of cars with a specified plate number, type of vehicle, and model of the vehcile.
         * @param plateNumber The specified license plate number.
         * @param vehicleType The specified type of vehicle.
         * @param model The specified model of the vehicle.
         * @return JPanel with vehicle information.
         */
        public JPanel createCarsLayout(String plateNumber, String vehicleType, String model){//String PlateNumber, String vehicle, String model){
            JPanel pnlCarInformation = res.createPnlRounded(290,150, res.feldgrau, res.gray);
            //pnlCarInformation.setBackground(res.feldgrau);
            pnlCarInformation.setLayout(new GridBagLayout());

            JPanel innerContent = new JPanel(new GridBagLayout());
            innerContent.setBackground(res.feldgrau);
            //innerContent.setBackground(res.feldgrau);

            gbc = new GridBagConstraints();
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;

            gbc.insets = new Insets(0,20,0,0);
            JLabel lblPlateNumber = res.createLblP("Plate Number",res.eerieBlack);
            lblPlateNumber.setMaximumSize(new Dimension(150,20));
            innerContent.add(lblPlateNumber,gbc);

            gbc.gridy = 1;
            JLabel lblPlateNumberInfo = res.createLblP2(plateNumber, res.white);
            lblPlateNumberInfo.setMaximumSize(new Dimension(150,30));
            innerContent.add(lblPlateNumberInfo,gbc);

            gbc.gridy = 2;
            JLabel lblEmpty = new JLabel(" ");
            innerContent.add(lblEmpty,gbc);

            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.WEST;
            JLabel lblVehicle = res.createLblP("Vehicle",res.eerieBlack);
            lblVehicle.setMaximumSize(new Dimension(120,12));
            innerContent.add(lblVehicle,gbc);

            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.insets = new Insets(0,0,0,20);
            JLabel lblModel = res.createLblP("Model",res.eerieBlack);
            lblModel.setMaximumSize(new Dimension(110,12));
            innerContent.add(lblModel,gbc);

            gbc.insets = new Insets(0,20,0,0);
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.WEST;
            JLabel lblVehicleInfo = res.createLblP(vehicleType,res.white);
            lblVehicleInfo.setMaximumSize(new Dimension(95,12));
            innerContent.add(lblVehicleInfo,gbc);

            gbc.insets = new Insets(0,0,0,20);
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.EAST;
            JLabel lblModelInfo = res.createLblP(model,res.white);
            lblModelInfo.setMaximumSize(new Dimension(100,20));
            innerContent.add(lblModelInfo,gbc);

            gbc.insets = new Insets(20,30,20,30);
            pnlCarInformation.add(innerContent);

            return pnlCarInformation;
        }
    }

    /**
     * Sets a specified action listener for btnNavEditProfile.
     * @param actionListener The specified action listener.
     */
    public void setNavEditProfileListener(ActionListener actionListener) {
        btnNavEditProfile.addActionListener(actionListener);
    }

    /**
     * Sets a specified action listener for btnNavEditCars.
     * @param actionListener The specified action listener.
     */
    public void setNavEditCarsListener(ActionListener actionListener) {
        btnNavEditCars.addActionListener(actionListener);
    }

    /**
     * Sets a specified action listener for btnNavHistory.
     * @param actionListener The specified action listener.
     */
    public void setNavHistoryListener(ActionListener actionListener) {
        btnNavHistory.addActionListener(actionListener);
    }

    public JButton getBtnNavEditProfile() {
        return btnNavEditProfile;
    }

    public JButton getBtnNavEditCars() {
        return btnNavEditCars;
    }

    public JButton getBtnNavSecurity() {
        return btnNavSecurity;
    }

    public JButton getBtnNavHistory() {
        return btnNavHistory;
    }

    public JButton getBtnNavExit() {
        return btnNavExit;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getPnlCards() {
        return pnlCards;
    }

    public EditProfile getPnlEditProfile() {
        return pnlEditProfile;
    }

    public ProfileOptionsPanel getPnlProfileOptions() {
        return pnlProfileOptions;
    }

    public EditCars getPnlEditCars() {
        return pnlEditCars;
    }

    public static void main(String[] args) {
        UserProfileView obj = new UserProfileView();
    }
}
