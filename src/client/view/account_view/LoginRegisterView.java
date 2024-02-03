package client.view.account_view;

import utilities.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * TODO: Documentation
 */
public class LoginRegisterView extends JFrame {
    /**
     * The text field for the user's first name.
     */
    private JTextField txtFirstName;
    /**
     * The text field for the user's last name.
     */
    private JTextField txtLastName;
    /**
     * The text field for the username.
     */
    private JTextField txtUsername;
    /**
     * The text field for the user's phone number.
     */
    private JTextField txtPhoneNo;
    /**
     * The password field for the user's password.
     */
    private JPasswordField txtPassword;
    /**
     * The password field for the user's password confirmation.
     */
    private JPasswordField txtConfirmPassword;
    /**
     * The checkbox to show password.
     */
    private JCheckBox chkShowPassword;
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
     * Instance variable of resources to invoke stylesheet and UI resources.
     */
    private Resources res = new Resources();
    /**
     * The GridBagConstraints to position components using GridBagLayout.
     */
    private GridBagConstraints gbc;
    /**
     * Control components of the Container by switching panels.
     */
    public CardLayout cardLayout = new CardLayout();

    /**
     * Constructs a RegisterView frame.
     */
    public LoginRegisterView() {
        super("PARCS");

        // Body panel acting as a container to hold all UI components
        Container contentArea = new JPanel(new BorderLayout());
        JPanel pnlCards = new JPanel(cardLayout);

        JPanel pnlLogin = new LoginPanel();
        pnlCards.add(pnlLogin, "login");

        JPanel pnlSignup = new SignupPanel();
        pnlCards.add(pnlSignup, "signup");

        contentArea.add(pnlCards, BorderLayout.CENTER);
        cardLayout.show(pnlCards, "signup");

        this.setContentPane(contentArea);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(950,560);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * TODO: Documentation
     */
    class LoginPanel extends JPanel {
        /**
         * Constructs a LoginaPanel panel.
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
            txtPassword.setText("Password"); // Omit text when model is set up
            pnlRight.add(txtPassword, gbc);

            gbc.gridy = 7;
            JLabel lblWrongPassword = res.createLblP("Wrong credentials. Try again.", res.red);
            pnlRight.add(lblWrongPassword, gbc);

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
     * TODO: Documentation
     */
    class SignupPanel extends JPanel {
        /**
         * Constructs a SignupPanel panel.
         */
        public SignupPanel() {
            setLayout(new GridBagLayout());
            setBackground(res.white);

            gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 3;
            gbc.ipady = 40;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(5,5,5,5);
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
            txtUsername = res.createTxtRounded("Username", res.lightGray, res.gray, 20);
            add(txtUsername, gbc);

            gbc.gridx = 2;
            gbc.gridwidth = 1;
            txtPhoneNo = res.createTxtRounded("Phone Number", res.lightGray, res.gray, 15);
            add(txtPhoneNo, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.gridwidth = 100;
            txtPassword = res.createPwdRounded(res.lightGray, res.gray, 35);
            add(txtPassword, gbc);

            gbc.gridx = 0;
            gbc.gridy = 6;
            txtConfirmPassword = res.createPwdRounded(res.lightGray, res.gray, 35);
            add(txtConfirmPassword, gbc);

            gbc.gridy = 7;
            gbc.fill = GridBagConstraints.NONE;
            JLabel lblWrongPassword = res.createLblP("Passwords do not match. Try again", res.red);
            add(lblWrongPassword, gbc);

            gbc.gridy = 8;
            btnCreateAccount = res.createBtnRounded("Create Account", res.celadon, res.eerieBlack, 10);
            add(btnCreateAccount, gbc);

            this.setPreferredSize(new Dimension(950,560));
        }
    }

    // Temporary main method to view and debug frame
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginRegisterView();
            }
        });
    }

    public void setSignupListener(ActionListener signupListener) {
        btnSignup.addActionListener(signupListener);
    }

    public void setShowPasswordListener(ActionListener actionListener) {
        chkShowPassword.addActionListener(actionListener);
    }
}
