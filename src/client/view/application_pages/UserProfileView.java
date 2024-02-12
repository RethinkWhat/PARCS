package client.view.application_pages;

import client.model.application_pages.UserProfileModel;
import com.sun.source.tree.EmptyStatementTree;
import utilities.Resources;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

/**
 * The UserProfileView is where the user views pertinent information regarding their account
 * and transaction in the client application.
 */
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
     * The panel for Edit profile.
     */
    private EditProfile pnlEditProfile;
    /**
     * The panel for profile options (account navigation)
     */
    private ProfileOptionsPanel pnlProfileOptions;
    /**
     * The panel for Edit cars.
     */
    private EditCars pnlEditCars;
    /**
     * The panel for History.
     */
    private HistoryPage pnlHistoryPage;
    /**
     * The panel for Security.
     */
    private SecurityPage pnlSecurityPage;
    /**
     * Constructs a panel of UserProfileView.
     */
    public UserProfileView() {
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();

        JPanel pnlRight = new JPanel(new BorderLayout());
        pnlRight.setPreferredSize(new Dimension(750, 700));
        add(pnlRight, BorderLayout.EAST);

        pnlCards = new JPanel(cardLayout);
        pnlCards.setPreferredSize(new Dimension(750, 560));
        // pnlCards.setBounds(220,0,750,560);
        pnlRight.add(pnlCards, BorderLayout.NORTH);

        pnlCards.add(pnlEditProfile = new EditProfile(), "profile");
        // edit cars page
        pnlCards.add(pnlEditCars = new EditCars(), "vehicles");
        // security page
        pnlCards.add(pnlSecurityPage = new SecurityPage(), "security");
        // history page
        pnlCards.add(pnlHistoryPage = new HistoryPage(), "history");
        //shows edit profile first
        cardLayout.show(pnlCards, "profile");

        // account navigation panel
        pnlProfileOptions = new ProfileOptionsPanel(cardLayout, pnlCards);
        add(pnlProfileOptions, BorderLayout.WEST);

        this.setPreferredSize(new Dimension(1100, 700));
    }

    /**
     * The panel that contains the sidebar options.
     */
    class ProfileOptionsPanel extends JPanel {
        /**
         * Constructs a panel of ProfileOptions with a specified layout manager and main panel to hold the components.
         *
         * @param cardLayout The specified CardLayout layout manager.
         * @param pnlCards   The specified main panel.
         */
        public ProfileOptionsPanel(CardLayout cardLayout, JPanel pnlCards) {
            this.setLayout(new BorderLayout()); // Use BorderLayout for easier component placement
            this.setBackground(res.white);

            JPanel buttonsPanel = new JPanel(new GridLayout(5, 1, 0, 0));
            buttonsPanel.setBackground(res.white);

            Font buttonFont = new Font("Arial", Font.BOLD, 20);

            ImageIcon editProfile = new ImageIcon("res/drawable/icons/edit.png");
            btnNavEditProfile = res.createBtnTxtOnly(" Edit Profile", res.gray);
            btnNavEditProfile.setFont(buttonFont);
            btnNavEditProfile.setHorizontalAlignment(SwingConstants.LEFT);
            ImageIcon editProfileResized = new ImageIcon(editProfile.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            btnNavEditProfile.setIcon(editProfileResized);
            buttonsPanel.add(btnNavEditProfile);

            ImageIcon editCars = new ImageIcon("res/drawable/icons/edit-cars.png");
            btnNavEditCars = res.createBtnTxtOnly(" Edit Vehicles", res.gray);
            btnNavEditCars.setFont(buttonFont);
            btnNavEditCars.setHorizontalAlignment(SwingConstants.LEFT);
            ImageIcon editCarsResized = new ImageIcon(editCars.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            btnNavEditCars.setIcon(editCarsResized);
            buttonsPanel.add(btnNavEditCars);

            ImageIcon security = new ImageIcon("res/drawable/icons/security.png");
            btnNavSecurity = res.createBtnTxtOnly(" Security", res.gray);
            btnNavSecurity.setFont(buttonFont);
            btnNavSecurity.setHorizontalAlignment(SwingConstants.LEFT);
            ImageIcon securityResized = new ImageIcon(security.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            btnNavSecurity.setIcon(securityResized);
            buttonsPanel.add(btnNavSecurity);

            ImageIcon historyIcon = new ImageIcon("res/drawable/icons/history.png");
            btnNavHistory = res.createBtnTxtOnly(" History", res.gray);
            btnNavHistory.setFont(buttonFont);
            btnNavHistory.setHorizontalAlignment(SwingConstants.LEFT);
            ImageIcon helpResized = new ImageIcon(historyIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            btnNavHistory.setIcon(helpResized);
            buttonsPanel.add(btnNavHistory);

            ImageIcon exitIcon = new ImageIcon("res/drawable/icons/exit-grey-outline.png");
            btnNavExit = res.createBtnTxtOnly(" Logout", res.gray);
            btnNavExit.setFont(buttonFont);
            btnNavExit.setHorizontalAlignment(SwingConstants.LEFT);
            ImageIcon exitResized = new ImageIcon(exitIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            btnNavExit.setIcon(exitResized);
            buttonsPanel.add(btnNavExit);

            // Add buttons panel to the center of the layout with some padding
            this.add(buttonsPanel, BorderLayout.CENTER);
            this.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add padding to the panel
            this.setPreferredSize(new Dimension(300, 700)); // Set preferred size
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
            this.setLayout(new GridLayout(8, 1));
            this.setBorder(new EmptyBorder(10, 20, 10, 60));

            JLabel lblEditProfile = res.createLblH1("Edit Profile", res.eerieBlack);
            lblEditProfile.setBorder(new EmptyBorder(25, 0, 0, 0));
            lblEditProfile.setFont(new Font("Arial", Font.BOLD, 32));
            this.add(lblEditProfile);

            JPanel pnlNameLabels = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JLabel lblFirstName = res.createLblH3("First Name", res.eerieBlack);
            lblFirstName.setBorder(new EmptyBorder(30, 0, 0, 0));

            pnlNameLabels.add(lblFirstName);

            // nameLabelsPanel.add(firstNameLabel);
            JLabel lastNameLabel = res.createLblH3("Last Name", res.eerieBlack);
            lastNameLabel.setBorder(new EmptyBorder(30, 180, 0, 0));
            pnlNameLabels.add(lastNameLabel);

            this.add(pnlNameLabels);

            JPanel pnlNameField = new JPanel(new FlowLayout(FlowLayout.LEFT));

            txtFirstName = res.createTxtRounded("First Name", res.white, res.gray, 20);
            txtFirstName.setPreferredSize(new Dimension(10, 50));

            txtLastName = res.createTxtRounded("Last Name", res.white, res.gray, 20);
            txtLastName.setPreferredSize(new Dimension(10, 50));

            pnlNameField.add(txtFirstName);
            pnlNameField.add(txtLastName);

            this.add(pnlNameField);

            JLabel lblUsername = res.createLblH3("Username", res.eerieBlack);
            lblUsername.setBorder(new EmptyBorder(30, 0, 0, 0));
            this.add(lblUsername);

            txtUsername = res.createTxtRounded("Username", res.lightGray, res.gray, 20);
            txtUsername.setSize(new Dimension(50, 50));
            txtUsername.setFocusable(false);
            this.add(txtUsername);

            JLabel lblContact = res.createLblH3("Contact", res.eerieBlack);
            lblContact.setBorder(new EmptyBorder(30, 0, 0, 0));
            this.add(lblContact);

            txtContact = res.createTxtRounded("#############", res.white, res.gray, 20);
            this.add(txtContact);

            JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.LEADING));
            pnlButtons.setBorder(new EmptyBorder(0, 0, 5, 0));

            btnCancel = res.createBtnRounded("Cancel", res.feldgrau, res.eerieBlack, 30);
            btnCancel.setPreferredSize(new Dimension(130, 40));
            pnlButtons.add(btnCancel);

            btnContinue = res.createBtnRounded("Continue", res.feldgrau, res.eerieBlack, 30);
            btnContinue.setPreferredSize(new Dimension(130, 40));
            pnlButtons.add(btnContinue);
            this.add(pnlButtons);
        }

        /**
         * Sets a specified action listener for btnContinue.
         *
         * @param actionListener The specified action listener.
         */
        public void setContinueListener(ActionListener actionListener) {
            btnContinue.addActionListener(actionListener);
        }

        /**
         * Sets a specified action listener for btnCancel.
         *
         * @param actionListener The specified action listener.
         */
        public void setCancelListener(ActionListener actionListener) {
            btnCancel.addActionListener(actionListener);
        }

        /**
         * Retrieves the current JTextField of txtFirstName.
         *
         * @return The current txtFirstName.
         */
        public JTextField getTxtFirstName() {
            return txtFirstName;
        }

        /**
         * Retrieves the current JTextField of txtLastName.
         *
         * @return The current txtLastName.
         */
        public JTextField getTxtLastName() {
            return txtLastName;
        }

        /**
         * Retrieves the current JTextField of txtUsername.
         *
         * @return The current txtUsername.
         */
        public JTextField getTxtUsername() {
            return txtUsername;
        }

        /**
         * Retrieves the current JTextField of txtContact.
         *
         * @return The current txtContact
         */
        public JTextField getTxtContact() {
            return txtContact;
        }

        /**
         * Retrieves the current value of txtFirstName.
         *
         * @return The current firstName.
         */
        public String getFirstName() {
            return txtFirstName.getText();
        }

        /**
         * Retrieves the current value of txtLastName.
         *
         * @return The current LastName.
         */
        public String getLastName() {
            return txtLastName.getText();
        }

        /**
         * Retrieves the current value of txtUsername.
         *
         * @return The current Username.
         */
        public String getUsername() {
            return txtUsername.getText();
        }

        /**
         * Retrieves the current value of txtContact.
         *
         * @return The current Contact
         */
        public String getContact() {
            return txtContact.getText();
        }

        /**
         * Retrieves the current JButton of btnCancel.
         * @return The current btnCancel.
         */
        public JButton getBtnCancel() {
            return btnCancel;
        }

        /**
         * Retrieves the current JButton of btnContinue.
         * @return The current btnContinue.
         */
        public JButton getBtnContinue() {
            return btnContinue;
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
            this.setBounds(220, 0, 750, 560);
            this.setVisible(true);
            this.setLayout(new GridBagLayout());

            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            JLabel lblEditProfile = res.createLblH1("Edit Cars", res.eerieBlack);
            lblEditProfile.setBorder(new EmptyBorder(30, 20, 0, 0));
            this.add(lblEditProfile, gbc);

            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.WEST;

            // TODO: Create method in UserProfileModel to create information of the cars.
            JPanel pnlCar1 = createCarsLayout("A-0130934023", "Sedan", "Honda Civic");
            JPanel pnlCar2 = createCarsLayout("A-0130934023", "Sedan", "Honda Civic");
            this.add(pnlCar1, gbc);
            this.add(pnlCar2, gbc);

            JPanel pnlCar3 = createCarsLayout("A-0130934023", "Sedan", "Honda Civic");
            JPanel pnlCar4 = createCarsLayout("A-0130934023", "Sedan", "Honda Civic");
            gbc.gridy = 2;
            gbc.insets = new Insets(20, 30, 0, 30);
            this.add(pnlCar3, gbc);
            this.add(pnlCar4, gbc);

            gbc.gridy = 5;
            gbc.gridx = 1;
            JButton btnBack = new JButton();
            ImageIcon backIcon = new ImageIcon("res/drawable/icons/forward.png");
            btnBack.setBorder(new EmptyBorder(0, 0, 0, 20));
            ImageIcon resizedBackIcon = new ImageIcon(backIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            btnBack.setIcon(resizedBackIcon);
            this.add(btnBack, gbc);

            this.setVisible(true);
        }

        /**
         * Creates a panel of cars with a specified plate number, type of vehicle, and model of the vehcile.
         *
         * @param plateNumber The specified license plate number.
         * @param vehicleType The specified type of vehicle.
         * @param model       The specified model of the vehicle.
         * @return JPanel with vehicle information.
         */
        public JPanel createCarsLayout(String plateNumber, String vehicleType, String model) {//String PlateNumber, String vehicle, String model){
            JPanel pnlCarInformation = res.createPnlRounded(290, 150, res.feldgrau, res.gray);
            //pnlCarInformation.setBackground(res.feldgrau);
            pnlCarInformation.setLayout(new GridBagLayout());

            JPanel innerContent = new JPanel(new GridBagLayout());
            innerContent.setBackground(res.feldgrau);
            //innerContent.setBackground(res.feldgrau);

            gbc = new GridBagConstraints();
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;

            gbc.insets = new Insets(0, 20, 0, 0);
            JLabel lblPlateNumber = res.createLblP("Plate Number", res.eerieBlack);
            lblPlateNumber.setMaximumSize(new Dimension(150, 20));
            innerContent.add(lblPlateNumber, gbc);

            gbc.gridy = 1;
            JLabel lblPlateNumberInfo = res.createLblP2(plateNumber, res.white);
            lblPlateNumberInfo.setMaximumSize(new Dimension(150, 30));
            innerContent.add(lblPlateNumberInfo, gbc);

            gbc.gridy = 2;
            JLabel lblEmpty = new JLabel(" ");
            innerContent.add(lblEmpty, gbc);

            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.WEST;
            JLabel lblVehicle = res.createLblP("Vehicle", res.eerieBlack);
            lblVehicle.setMaximumSize(new Dimension(120, 12));
            innerContent.add(lblVehicle, gbc);

            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.insets = new Insets(0, 0, 0, 20);
            JLabel lblModel = res.createLblP("Model", res.eerieBlack);
            lblModel.setMaximumSize(new Dimension(110, 12));
            innerContent.add(lblModel, gbc);

            gbc.insets = new Insets(0, 20, 0, 0);
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.WEST;
            JLabel lblVehicleInfo = res.createLblP(vehicleType, res.white);
            lblVehicleInfo.setMaximumSize(new Dimension(95, 12));
            innerContent.add(lblVehicleInfo, gbc);

            gbc.insets = new Insets(0, 0, 0, 20);
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.EAST;
            JLabel lblModelInfo = res.createLblP(model, res.white);
            lblModelInfo.setMaximumSize(new Dimension(100, 20));
            innerContent.add(lblModelInfo, gbc);

            gbc.insets = new Insets(20, 30, 20, 30);
            pnlCarInformation.add(innerContent);

            return pnlCarInformation;
        }
    }

    /**
     * This class represents a panel for changing account passwords.
     * It provides fields for entering current password, new password, and confirming the new password.
     */
    public class SecurityPage extends JPanel {
        /**
         * The password field where the current password is inputted.
         */
        private JPasswordField txtCurrentPassword;
        /**
         * The password field where the new password is entered.
         */
        private JPasswordField txtNewPassword;
        /**
         * The password field where the new password is confirmed.
         */
        private JPasswordField txtConfirmNewPassword;
        /**
         * The continue button.
         */
        private JButton btnConfirm;
        /**
         * The success or error message.
         */
        private JLabel lblMessage;

        /**
         * This constructs a new SecurityPage panel with fields for changing account passwords.
         * Initializes layout and components such as labels, text fields, and buttons.
         */
        public SecurityPage() {
            //sets a layout and borders
            this.setLayout(new GridLayout(9, 2));
            this.setBorder(new EmptyBorder(10, 20, 10, 60));

            //creates and adds labels for the password change section
            JLabel lblChangePassword = res.createLblH1("Change Password", res.eerieBlack);
            lblChangePassword.setBorder(new EmptyBorder(25, 0, 0, 0));
            lblChangePassword.setFont(new Font("Arial", Font.BOLD, 32));
            this.add(lblChangePassword);

            //adds label and text field for entering the current password
            JPanel pnlNameLabels = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel currentPassword = res.createLblH3("Current Password", res.eerieBlack);
            currentPassword.setBorder(new EmptyBorder(30, 0, 0, 0));
            pnlNameLabels.add(currentPassword);
            this.add(pnlNameLabels);

            JPanel pnlCurrentPasswordField = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pnlCurrentPasswordField.setPreferredSize(new Dimension(750,50));
            this.add(pnlCurrentPasswordField);
            txtCurrentPassword = res.createPwdRounded(res.white, res.gray, 50);
            txtCurrentPassword.setPreferredSize(new Dimension(750,50));
            txtCurrentPassword.setEchoChar((char)0);
            pnlCurrentPasswordField.add(txtCurrentPassword);

            //adds a label and text field for entering the new password
            JLabel newPassword = res.createLblH3("New Password", res.eerieBlack);
            newPassword.setBorder(new EmptyBorder(30, 0, 0, 0));
            this.add(newPassword);
            JPanel pnlNewPasswordField = new JPanel(new FlowLayout(FlowLayout.LEFT));
            this.add(pnlNewPasswordField);
            txtNewPassword = res.createPwdRounded(res.white, res.gray, 50);
            txtNewPassword.setPreferredSize(new Dimension(750,50));
            txtNewPassword.setEchoChar((char)0);
            pnlNewPasswordField.add(txtNewPassword);

            //adds a label and text field for confirming the new password
            JLabel confirmNewPassword = res.createLblH3("Confirm New Password", res.eerieBlack);
            confirmNewPassword.setBorder(new EmptyBorder(30, 0, 0, 0));
            this.add(confirmNewPassword);
            JPanel pnlConfirmNewPasswordField = new JPanel(new FlowLayout(FlowLayout.LEFT));
            this.add(pnlConfirmNewPasswordField);
            txtConfirmNewPassword = res.createPwdRounded(res.white, res.gray, 50);
            txtConfirmNewPassword.setPreferredSize(new Dimension(750,50));
            txtConfirmNewPassword.setEchoChar((char)0);
            pnlConfirmNewPasswordField.add(txtConfirmNewPassword);

            //adds a button for confirming the password change
            JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
            pnlButtons.setBorder(new EmptyBorder(10, 0, 7, 0));
            btnConfirm = res.createBtnRounded("Change Password", res.celadon, res.eerieBlack, 40);
            btnConfirm.setPreferredSize(new Dimension(250,35 ));
            pnlButtons.add(btnConfirm);
            this.add(pnlButtons);
            
            lblMessage = res.createLblP("", res.red); // empty message
            this.add(lblMessage);
        }

        /**
         * Retrieves the current JPasswordField of txtCurrentPassword in the security page.
         * @return The current txtCurrentPassword.
         */
        public JPasswordField getTxtCurrentPassword() {
            return txtCurrentPassword;
        }

        /**
         * Retrieves the current JPasswordField of txtNewPassword in the security page.
         * @return The current txtNewPassword.
         */
        public JPasswordField getTxtNewPassword() {
            return txtNewPassword;
        }

        /**
         * Retrieves the current txtConfirmNewPassword in the security page.
         * @return The current txtConfirmPassword.
         */
        public JPasswordField getTxtConfirmNewPassword() {
            return txtConfirmNewPassword;
        }

        /**
         * Retrieves the current JButton of btnConfirm.
         * @return The current btnConfirm.
         */
        public JButton getBtnConfirm() {
            return btnConfirm;
        }

        /**
         * Retrieves the current JLabel of lblMessage.
         * @return The current lblMessage.
         */
        public JLabel getLblMessage() {
            return lblMessage;
        }
    }

    /**
     * The panel that contains the booking history of the user.
     */
    class HistoryPage extends JPanel {
        /**
         * The panel that holds the first transaction details of the user.
         */
        private JPanel pnlHistoryOne;
        /**
         * The panel that holds the second transaction details of the user.
         */
        private JPanel pnlHistoryTwo;
        /**
         * The panel that holds the third transaction details of the user.
         */
        private JPanel pnlHistoryThree;
        /**
         * The panel that holds the fourth transaction details of the user.
         */
        private JPanel pnlHistoryFour;
        /**
         * The next button.
         */
        private JButton btnNext;
        /**
         * The previous button.
         */
        private JButton btnPrev;

        /**
         * Constructs a panel of HistoryPage.
         */
        public HistoryPage() {
            setBackground(res.lightGray);
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(1100, 700));

            // Center panel for main content
            JPanel whitePanel = new JPanel(new GridBagLayout());
            whitePanel.setPreferredSize(new Dimension(1100, 700));
            whitePanel.setBackground(res.white);
            add(whitePanel, BorderLayout.CENTER);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10); // Add insets for spacing

            pnlHistoryOne = new JPanel();
            pnlHistoryOne.setBackground(Color.lightGray);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1; // Allow panel to resize horizontally
            gbc.weighty = 1; // Allow panel to resize vertically
            gbc.fill = GridBagConstraints.BOTH; // Allow panel to resize in both directions
            whitePanel.add(pnlHistoryOne, gbc);

            pnlHistoryTwo = new JPanel();
            pnlHistoryTwo.setBackground(Color.lightGray);
            gbc.gridx = 1;
            pnlHistoryTwo.setPreferredSize(new Dimension(100, 100));
            whitePanel.add(pnlHistoryTwo, gbc);

            pnlHistoryThree = new JPanel();
            pnlHistoryThree.setBackground(Color.lightGray);
            pnlHistoryThree.setPreferredSize(new Dimension(100, 100));
            gbc.gridx = 0;
            gbc.gridy = 1;
            whitePanel.add(pnlHistoryThree, gbc);

            pnlHistoryFour = new JPanel();
            pnlHistoryFour.setBackground(Color.lightGray);
            pnlHistoryFour.setPreferredSize(new Dimension(100, 100));
            gbc.gridx = 1;
            whitePanel.add(pnlHistoryFour, gbc);

            JPanel pnlButtons = new JPanel(new FlowLayout());
            pnlButtons.setBackground(res.lightGray);
            add(pnlButtons, BorderLayout.SOUTH);

            // Previous and Next buttons
            btnPrev = res.createBtnRounded("PREV", res.gray, res.eerieBlack, 3);
            pnlButtons.add(btnPrev);

            btnNext = res.createBtnRounded("NEXT", res.celadon, res.eerieBlack, 3);
            pnlButtons.add(btnNext);

            setVisible(true);
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

    /**
     * Sets a specified action listener for btnNavExit.
     * @param actionListener The specified action listener.
     */
    public void setNavExitListener(ActionListener actionListener) {
        btnNavExit.addActionListener(actionListener);
    }

    /**
     * Sets a specified action listener for btnNavSecurity.
     * @param actionListener The specified action listener.
     */
    public void setNavSecurityListener(ActionListener actionListener) {
        btnNavSecurity.addActionListener(actionListener);
    }

    /**
     * Retrieves the current JButton of btnNavEditProfile.
     * @return The current btnNavEditProfile.
     */
    public JButton getBtnNavEditProfile() {
        return btnNavEditProfile;
    }

    /**
     * Retrieves the current JButton of btnNavEditCars.
     * @return The current btnNavEditCars.
     */
    public JButton getBtnNavEditCars() {
        return btnNavEditCars;
    }

    /**
     * Retrieves the current JButton of btnNavSecurity.
     * @return The current btnNavSecurity.
     */
    public JButton getBtnNavSecurity() {
        return btnNavSecurity;
    }

    /**
     * Retrieves the current JButton of btnNavHistory.
     * @return The current btnNavHistory.
     */
    public JButton getBtnNavHistory() {
        return btnNavHistory;
    }

    /**
     * Retrieves the current JButton of btnNavExit.
     * @return The current btnNavExit.
     */
    public JButton getBtnNavExit() {
        return btnNavExit;
    }

    /**
     * Provides access to the CardLayout used for managing the panels.
     * @return The CardLayout object used for managing panels.
     */
    public CardLayout getCardLayout() {
        return cardLayout;
    }

    /**
     * Provides access to the main panel containing cards managed by the CardLayout.
     * @return The JPanel containing cards managed by the CardLayout.
     */
    public JPanel getPnlCards() {
        return pnlCards;
    }

    /**
     * Provides access to the panel for editing profile information.
     * @return The EditProfile panel for editing profile information.
     */
    public EditProfile getPnlEditProfile() {
        return pnlEditProfile;
    }

    /**
     * Provides access to the panel containing profile options.
     * @return The ProfileOptionsPanel containing profile options buttons.
     */
    public ProfileOptionsPanel getPnlProfileOptions() {
        return pnlProfileOptions;
    }

    /**
     * Provides access to the panel for editing cars information.
     * @return The EditCars panel for editing cars information.
     */
    public EditCars getPnlEditCars() {
        return pnlEditCars;
    }

    /**
     * Provides access to the panel for managing security settings.
     * @return The SecurityPage panel for managing security settings.
     */
    public SecurityPage getPnlSecurityPage(){
        return pnlSecurityPage;
    }

    //This is for testing the GUI.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create an instance of UserProfileView
                UserProfileView userProfileView = new UserProfileView();

                // Create a JFrame to hold the UserProfileView
                JFrame frame = new JFrame("User Profile");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(userProfileView);
                frame.pack();
                frame.setLocationRelativeTo(null); // Center the frame on the screen
                frame.setVisible(true);
            }
        });
    }
}

