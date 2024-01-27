package view.client_view.account_view;

import view.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * TODO: Documentation
 */
public class RegisterView extends JFrame {
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

    private Resources res = new Resources();

    /**
     * Constructs a RegisterView frame
     */
    public RegisterView() {
        super("PARCS");

        // Body panel acting as a container to hold all UI components
        Container contentArea = new JPanel(new BorderLayout());

        // Main Panel
        JPanel pnlMain = new JPanel(new GridLayout(0,2));
        contentArea.add(pnlMain, BorderLayout.CENTER);

        // Left Panel
        JPanel pnlLeft = new JPanel();
        pnlLeft.setBackground(new Resources().feldgrau);
        pnlMain.add(pnlLeft);

        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(res.logoParcs);
        pnlLeft.add(lblLogo);

        JLabel lblTagline = res.createLblH2("PARKING MADE EASY", res.white);
        pnlLeft.add(lblTagline);

        // Right Panel
        JPanel pnlRight = new JPanel();
        pnlRight.setLayout(new GridBagLayout());
        pnlRight.setBackground(new Resources().white);
        pnlMain.add(pnlRight);

        // GridBagConstraints to position components using the GB layout
        GridBagConstraints gbc = new GridBagConstraints();

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

        gbc.gridy = 5;
        gbc.gridx = 0;
        chkShowPassword = new JCheckBox("Show Password");
        chkShowPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        chkShowPassword.setHorizontalAlignment(SwingConstants.LEFT);
        pnlRight.add(chkShowPassword, gbc);

        gbc.gridy = 11;
        gbc.ipady = 40;
        JPanel pnlButtons = new JPanel(new FlowLayout());
        pnlButtons.setBackground(new Resources().white);
        pnlRight.add(pnlButtons, gbc);

        // !!! Buttons Panel components
        btnSignup = res.createBtnRounded("Sign Up", res.gray, res.gray, 10);
        pnlButtons.add(btnSignup);

        btnLogin = res.createBtnRounded("Log In", res.celadon, res.celadon, 10);
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
                new RegisterView();
            }
        });
    }

}
