package client.view.account_view;

import utilities.Resources;

import javax.swing.*;
import java.awt.*;

/**
 * TODO: Documentation
 */
public class LoginRegisterView extends JFrame {
    /**
     * Username text field
     */
    private JTextField txtUsername;
    /**
     * Password text field
     */
    private JPasswordField txtPassword;
    /**
     * Checkbox to show password
     */
    private JCheckBox chkShowPassword;
    /**
     * Login button
     */
    private JButton btnLogin;

    /**
     * Signup for an account button
     */
    private JButton btnSignup;

    /**
     * Instance variable of resources to invoke stylesheet and UI resources.
     */
    private Resources res = new Resources();

    /**
     * Constructs a RegisterView frame
     */
    public LoginRegisterView() {
        super("PARCS");

        // Body panel acting as a container to hold all UI components
        Container contentArea = new JPanel(new BorderLayout());

        // GridBagConstraints to position components using the GB layout
        GridBagConstraints gbc = new GridBagConstraints();

        // Control components of the Container
        CardLayout cardLayout = new CardLayout();

        // Main Panel
        JPanel pnlMain = new JPanel(new GridLayout(0,2));
        contentArea.add(pnlMain, BorderLayout.CENTER);

        // Left Panel
        JPanel pnlLeft = new JPanel(new GridBagLayout());
        pnlLeft.setBackground(res.feldgrau);
        pnlMain.add(pnlLeft);

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
        pnlMain.add(pnlRight);

        // !! Right Panel Components
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
        txtUsername.setText("Username"); // Omit text when model is set up
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

        this.setContentPane(contentArea);
        this.setLocationRelativeTo(null);
        this.setSize(950,560);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
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

}
