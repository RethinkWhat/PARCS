package view.client_view.account_view;

import view.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
     * Forgot password button
     */
    private JButton btnForgotPassword;
    /**
     * Signup for an account button
     */
    private JButton btnSignup;

    /**
     * Constructs a RegisterView frame
     */
    public RegisterView() {
        super("PARCS");

        // Body panel acting as a container to hold all UI components
        JPanel pnlContentArea = new JPanel(new BorderLayout());
        pnlContentArea.setLayout(new BorderLayout());

        // Header Panel
        /*
        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setPreferredSize(new Dimension(1138,75));
        pnlHeader.setBackground(new Resources().celadon);
        pnlContentArea.add(pnlHeader, BorderLayout.NORTH);
         */

        // Main Panel
        JPanel pnlMain = new JPanel(new GridLayout(0,2));
        pnlContentArea.add(pnlMain, BorderLayout.CENTER);

        // ! Main Panel Components
        JPanel pnlLeft = new JPanel();
        pnlLeft.setBackground(new Resources().feldgrau);
        pnlMain.add(pnlLeft);

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

        JLabel lblTitle = new JLabel("Log In");
        pnlRight.add(lblTitle, gbc);

        gbc.gridy = 3;

        txtUsername = new JTextField(20);
        txtUsername.setText("Username"); // Omit text when model is set up
        pnlRight.add(txtUsername, gbc);

        gbc.gridy = 4;

        txtPassword = new JPasswordField(20);
        txtPassword.setText("Password"); // Omit text when model is set up
        pnlRight.add(txtPassword, gbc);

        gbc.gridy = 5;
        chkShowPassword = new JCheckBox("Show Password");
        pnlRight.add(chkShowPassword, gbc);

        gbc.gridy = 8;
        btnForgotPassword = new JButton("Forgot Password?");
        pnlRight.add(btnForgotPassword, gbc);

        gbc.gridy = 11;
        JPanel pnlButtons = new JPanel(new FlowLayout());
        pnlButtons.setBackground(new Resources().white);
        pnlRight.add(pnlButtons, gbc);

        // !!! Buttons Panel components
        btnSignup = new JButton("Sign Up");
        pnlButtons.add(btnSignup);

        btnLogin = new JButton("Log In");
        pnlButtons.add(btnLogin);

        this.setContentPane(pnlContentArea);
        this.setLocationRelativeTo(null);
        this.setSize(950,560);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    // Temporary main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegisterView();
            }
        });
    }


    public void setActionListener(ActionListener listener) {

    }
}
