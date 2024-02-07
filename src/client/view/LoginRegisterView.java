package client.view;

import utilities.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * TODO: Documentation
 */
public class LoginRegisterView extends JFrame {
    /**
     * The panel that holds different component panels.
     */
    private JPanel pnlCards;
    /**
     * The text field for the user's first name in the signup page.
     */
    private JTextField txtFirstName;
    /**
     * The text field for the user's last name in the signup page.
     */
    private JTextField txtLastName;
    /**
     * The text field for the username in the login page.
     */
    private JTextField txtUsername;
    /**
     * The text field for the username in the signup page.
     */
    private JTextField txtSignupUsername;
    /**
     * The text field for the user's phone number in the signup page.
     */
    private JTextField txtPhoneNo;
    /**
     * The password field for the user's password in the login page.
     */
    private JPasswordField txtPassword;
    /**
     * The password field for the user's password in the signup page.
     */
    private JPasswordField txtSignupPassword;
    /**
     * The password field for the user's password confirmation in the signup page.
     */
    private JPasswordField txtConfirmPassword;
    /**
     * The checkbox to show password in the login page.
     */
    private JCheckBox chkShowPassword;
    /**
     * The checkbox to show password in the signup page.
     */
    private JCheckBox chkShowSignupPassword;
    /**
     * The checkbox to show password confirmation in the signup page.
     */
    private JCheckBox chkShowConfirmPassword;
    /**
     * The button to return to the login page from the signup page.
     */
    private JButton btnBack;
    /**
     * The button to log the user in the system.
     */
    private JButton btnLogin;
    /**
     * The button to go to the signup page.
     */
    private JButton btnSignup;
    /**
     * The button to create an account.
     */
    private JButton btnCreateAccount;
    /**
     * The error message displayed in the login page.
     */
    private JLabel lblLoginErrorMessage;
    /**
     * The error message displayed in the signup page.
     */
    private JLabel lblSignupErrorMessage;
    /**
     * Instance variable of resources to invoke stylesheet and UI resources.
     */
    private final Resources res = new Resources();
    /**
     * The GridBagConstraints to position components using GridBagLayout.
     */
    private GridBagConstraints gbc;
    /**
     * Control components of the Container by switching panels.
     */
    private CardLayout cardLayout = new CardLayout();

    /**
     * Constructs a RegisterView frame.
     */
    public LoginRegisterView() {
        super("PARCS");

        // Body panel acting as a container to hold all UI components
        Container contentArea = new JPanel(new BorderLayout());
        pnlCards = new JPanel(cardLayout);

        JPanel pnlLogin = new LoginPanel();
        pnlCards.add(pnlLogin, "login");

        JPanel pnlSignup = new SignupPanel();
        pnlCards.add(pnlSignup, "signup");

        contentArea.add(pnlCards, BorderLayout.CENTER);
        cardLayout.show(pnlCards, "login");

        this.setContentPane(contentArea);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(950,560);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Contains the UI components for the login page.
     */
    class LoginPanel extends JPanel {
        /**
         * Constructs a LoginPanel panel.
         */
        public LoginPanel() {
            // Main Panel
            setLayout(new GridLayout(0,2));

            // GridBagConstraints to position components using the GB layout
            gbc = new GridBagConstraints();

            // Left Panel
            JPanel pnlLeft = new JPanel(new GridBagLayout());
            pnlLeft.setBackground(res.feldgrau);
            add(pnlLeft);

            gbc.gridy = 0;
            gbc.gridwidth = 1;
            JLabel lblLogo = new JLabel("");
            lblLogo.setIcon(res.logoParcs);
            pnlLeft.add(lblLogo, gbc);

            gbc.gridy = 1;
            JLabel lblTagline = res.createLblH2("Parking made easy.", res.white);
            pnlLeft.add(lblTagline, gbc);

            // Right Panel
            JPanel pnlRight = new JPanel(new GridBagLayout());
            pnlRight.setBackground(res.white);
            add(pnlRight);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.ipady = 40;
            gbc.insets = new Insets(5,0,5,0);
            JLabel lblTitle = res.createLblH1("Log In", res.eerieBlack);
            pnlRight.add(lblTitle, gbc);

            gbc.gridy = 3;
            gbc.ipady = 3;
            txtUsername = res.createTxtRounded("Username", res.lightGray, res.gray,20);
            pnlRight.add(txtUsername, gbc);

            gbc.gridy = 4;
            txtPassword = res.createPwdRounded(res.lightGray, res.gray, 20);
            txtPassword.setEchoChar((char)0);
            pnlRight.add(txtPassword, gbc);

            gbc.gridy = 7;
            lblLoginErrorMessage = res.createLblP("", res.red);
            pnlRight.add(lblLoginErrorMessage, gbc);

            gbc.gridy = 6;
            gbc.gridx = 0;
            chkShowPassword = new JCheckBox("Show Password");
            chkShowPassword.setFont(new Font("Arial", Font.PLAIN, 14));
            chkShowPassword.setHorizontalAlignment(SwingConstants.LEFT);
            chkShowPassword.setBackground(res.white);
            pnlRight.add(chkShowPassword, gbc);

            gbc.gridy = 8;
            gbc.ipady = 40;
            JPanel pnlButtons = new JPanel(new FlowLayout());
            pnlButtons.setBackground(res.white);
            pnlRight.add(pnlButtons, gbc);

            // !!! Buttons Panel components
            btnSignup = res.createBtnRounded("Sign Up", res.gray, res.eerieBlack, 10);
            pnlButtons.add(btnSignup);

            btnLogin = res.createBtnRounded("Log In", res.celadon, res.eerieBlack, 10);
            pnlButtons.add(btnLogin);

            this.setPreferredSize(new Dimension(950,560));
        }
    }

    /**
     * Contains the different signup UI components.
     */
    class SignupPanel extends JPanel {
        /**
         * Constructs a SignupPanel panel.
         */
        public SignupPanel() {
            setLayout(new GridBagLayout());
            setBackground(res.white);

            gbc = new GridBagConstraints();

            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(5,5,5,5);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            btnBack = res.createBtnIconOnly(res.iconLeftArrow,30,30);
            add(btnBack, gbc);

            gbc.gridy = 1;
            gbc.ipady = 40;
            gbc.gridwidth = 3;
            JLabel lblTitle = res.createLblH1("Sign Up", res.eerieBlack);
            lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblTitle, gbc);

            gbc.gridy = 3;
            gbc.ipadx = 5;
            gbc.ipady = 3;
            gbc.gridwidth = 2;
            txtFirstName = res.createTxtRounded("First Name", res.lightGray, res.gray, 20);
            add(txtFirstName, gbc);

            gbc.gridx = 2;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            txtLastName = res.createTxtRounded("Last Name", res.lightGray, res.gray, 15);
            add(txtLastName, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            txtSignupUsername = res.createTxtRounded("Username", res.lightGray, res.gray, 20);
            add(txtSignupUsername, gbc);

            gbc.gridx = 2;
            gbc.gridwidth = 1;
            txtPhoneNo = res.createTxtRounded("Phone Number", res.lightGray, res.gray, 15);
            add(txtPhoneNo, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.gridwidth = 100;
            txtSignupPassword = res.createPwdRounded(res.lightGray, res.gray, 35);
            txtSignupPassword.setEchoChar((char)0);
            add(txtSignupPassword, gbc);

            gbc.gridy = 6;
            chkShowSignupPassword = new JCheckBox("Show Password");
            chkShowSignupPassword.setFont(new Font("Arial", Font.PLAIN, 14));
            chkShowSignupPassword.setHorizontalAlignment(SwingConstants.LEFT);
            chkShowSignupPassword.setBackground(res.white);
            add(chkShowSignupPassword, gbc);

            gbc.gridx = 0;
            gbc.gridy = 7;
            txtConfirmPassword = res.createPwdRounded(res.lightGray, res.gray, 35);
            txtConfirmPassword.setEchoChar((char)0);
            add(txtConfirmPassword, gbc);

            gbc.gridy = 8;
            chkShowConfirmPassword = new JCheckBox("Show Password");
            chkShowConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 14));
            chkShowConfirmPassword.setHorizontalAlignment(SwingConstants.LEFT);
            chkShowConfirmPassword.setBackground(res.white);
            add(chkShowConfirmPassword, gbc);

            gbc.gridy = 9;
            gbc.fill = GridBagConstraints.NONE;
            lblSignupErrorMessage = res.createLblP("", res.red);
            add(lblSignupErrorMessage, gbc);

            gbc.gridy = 10;
            btnCreateAccount = res.createBtnRounded("Create Account", res.celadon, res.eerieBlack, 10);
            add(btnCreateAccount, gbc);

            this.setPreferredSize(new Dimension(950,560));
        }
    }

    /**
     * Sets a specified action listener for btnLogin.
     * @param loginListener The specified action listener.
     */
    public void setLoginListener(ActionListener loginListener) {
        btnLogin.addActionListener(loginListener);
    }

    /**
     * Sets a specified action listener for btnSignup.
     * @param signupListener The specified action listener.
     */
    public void setSignupListener(ActionListener signupListener) {
        btnSignup.addActionListener(signupListener);
    }

    /**
     * Sets a specified action listener for btnCreateAccount.
     * @param createAccountListener The specified action listener.
     */
    public void setCreateAccountListener(ActionListener createAccountListener) {
        btnCreateAccount.addActionListener(createAccountListener);
    }

    /**
     * Sets a specified action listener for btnBack.
     * @param backListener The specified action listener.
     */
    public void setBackListener(ActionListener backListener) {
        btnBack.addActionListener(backListener);
    }

    /**
     * Sets a specified error message for lblLoginErrorMessage.
     * @param message The specified error.
     */
    public void displayLoginErrorMessage(String message) {
        lblLoginErrorMessage.setText(message);
    }

    /**
     * Sets a specified error message for lblSignupErrorMessage
     * @param message The specified error.
     */
    public void displaySignupErrorMessage(String message) {
        lblSignupErrorMessage.setText(message);
    }

    /**
     * Retrieves the current username input in the txtUsername of the login page.
     * @return The current username input.
     */
    public String getUsername() {
        return txtUsername.getText();
    }

    /**
     * Retrieves the current password input in the txtPassword of the login page.
     * @return The current password input.
     */
    public String getPassword() {
        return txtPassword.getText();
    }

    /**
     * Retrieves the current first name input in the txtFirstName of the signup page.
     * @return The current first name input.
     */
    public String getSignupFirstName() {
        return txtFirstName.getText();
    }

    /**
     * Retrieves the current last name input in the txtLastName of the signup page.
     * @return The current last name input.
     */
    public String getSignupLastName() {
        return txtLastName.getText();
    }

    /**
     * Retrieves the current username input in the txtSignupUsername of the signup page.
     * @return The current username input.
     */
    public String getSignupUsername() {
        return txtSignupUsername.getText();
    }

    /**
     * Retrieves the current phone number input in the txtPhoneNo of the signup page.
     * @return The current phone number input.
     */
    public String getSignupPhoneNo() {
        return txtPhoneNo.getText();
    }

    /**
     * Retrieves the current password input in the txtSignupPassword of the signup page.
     * @return The current password input.
     */
    public String getSignupPassword() {
        return String.valueOf(txtSignupPassword.getPassword());
    }

    /**
     * Retrieves the current password input in the txtConfirmPassword of the signup page.
     * @return The current password input.
     */
    public String getConfirmPassword() {
        return String.valueOf(txtConfirmPassword.getPassword());
    }

    /**
     * Retrieves the current card layout panel containing the login and signup panels..
     * @return The current card layout panel.
     */
    public JPanel getPnlCards() {
        return pnlCards;
    }

    /**
     * Retrieves the current JTextField object of txtSignupUsername.
     * @return The current txtSignupUsername.
     */
    public JTextField getTxtSignupUsername() {
        return txtSignupUsername;
    }

    public JTextField getTxtFirstName() {
        return txtFirstName;
    }

    public JTextField getTxtLastName() {
        return txtLastName;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JTextField getTxtPhoneNo() {
        return txtPhoneNo;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JPasswordField getTxtSignupPassword() {
        return txtSignupPassword;
    }

    public JPasswordField getTxtConfirmPassword() {
        return txtConfirmPassword;
    }

    public JCheckBox getChkShowPassword() {
        return chkShowPassword;
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnSignup() {
        return btnSignup;
    }

    public JButton getBtnCreateAccount() {
        return btnCreateAccount;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JCheckBox getChkShowSignupPassword() {
        return chkShowSignupPassword;
    }

    public JCheckBox getChkShowConfirmPassword() {
        return chkShowConfirmPassword;
    }
}
