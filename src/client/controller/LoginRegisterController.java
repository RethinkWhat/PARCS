package client.controller;

import client.model.Client;
import client.model.LoginRegisterModel;
import client.view.LoginRegisterView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Template for LoginRegisterController.
 */
public class LoginRegisterController {

    private Client client;
    /**
     * The view LoginRegisterView object.
     */
    private LoginRegisterView view;
    /**
     * The model LoginRegisterModel object.
     */
    private LoginRegisterModel model;

    /**
     * Constructs a LoginRegisterController with a specified view and model.
     *
     * @param view  The specified LoginRegisterView.
     * @param model The specified LoginRegisterModel.
     */
    public LoginRegisterController(Client client, LoginRegisterView view, LoginRegisterModel model) {
        this.client = client;
        this.view = view;
        this.model = model;

        // action listeners
        view.setLoginListener(new LoginListener());
        view.setSignupListener(e -> view.getCardLayout().show(view.getPnlCards(), "signup"));
        view.setBackListener(e -> view.getCardLayout().show(view.getPnlCards(), "login"));
        view.setShowPasswordListener(new ShowPasswordListener(view.getChkShowPassword(),view.getTxtPassword()));
        view.setCreateAccountListener(e -> new CreateAccountListener());

        // mouse listeners
        view.getBtnSignup().addMouseListener(new CursorChanger(view.getBtnSignup()));
        view.getBtnLogin().addMouseListener(new CursorChanger(view.getBtnLogin()));
        view.getBtnBack().addMouseListener(new CursorChanger(view.getBtnBack()));
        view.getBtnCreateAccount().addMouseListener(new CursorChanger(view.getBtnCreateAccount()));

        // focus listeners
        view.getTxtUsername().addFocusListener(new TextFieldFocus(view.getTxtUsername(), "Username"));
        view.getTxtPassword().addFocusListener(new PasswordFocus(view.getTxtPassword(),
                view.getChkShowPassword(), "Password"));
        view.getTxtFirstName().addFocusListener(new TextFieldFocus(view.getTxtFirstName(), "First Name"));
        view.getTxtLastName().addFocusListener(new TextFieldFocus(view.getTxtLastName(), "Last Name"));
        view.getTxtSignupUsername().addFocusListener(new TextFieldFocus(view.getTxtSignupUsername(), "Username"));
        view.getTxtPhoneNo().addFocusListener(new TextFieldFocus(view.getTxtPhoneNo(), "Phone Number"));
        view.getTxtSignupPassword().addFocusListener(new PasswordFocus(view.getTxtSignupPassword(),
                view.getChkShowSignupPassword(), "Password"));
        view.getTxtConfirmPassword().addFocusListener(new PasswordFocus(view.getTxtConfirmPassword(),
                view.getChkShowConfirmPassword(), "Confirm Password"));

        view.repaint();
        view.revalidate();
    }

    /**
     * TODO: Documentation
     */
    class LoginListener implements ActionListener {
        /**
         * TODO: Documentation
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            client.writeString(view.getUsername());
            client.writeString(view.getPassword());

            // Authentication success
            if (client.readString().equals("true")) {
                System.out.println("success");
            } else {
                System.out.println("fail");
            }
        }
    }

    /**
     * TODO: Documentation
     */
    class CreateAccountListener implements ActionListener {
        /**
         * TODO: Documentation
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    /**
     * TODO: Documentation
     */
    class ShowPasswordListener implements ActionListener {
        /**
         * TODO: Documentation
         */
        private JCheckBox checkBox;
        /**
         * TODO: Documentation
         */
        private JPasswordField passwordField;

        /**
         * TODO: Documentation
         * @param checkBox
         * @param passwordField
         */
        public ShowPasswordListener(JCheckBox checkBox, JPasswordField passwordField) {
            this.checkBox = checkBox;
            this.passwordField = passwordField;
        }

        /**
         * TODO: Documentation
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (checkBox.isSelected()) {
                passwordField.setEchoChar((char) 0); // shows password in characters
            } else {
                passwordField.setEchoChar('●');
            }
        }
    }

    /**
     * TODO: Documentation
     */
    class CursorChanger extends MouseAdapter {
        /**
         * TODO: Documentation
         */
        private JButton button;

        /**
         * TODO: Documentation
         * @param button
         */
        public CursorChanger(JButton button) {
            this.button = button;
        }

        /**
         * TODO: Documentation
         * @param e the event to be processed
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        /**
         * TODO: Documentation
         * @param e the event to be processed
         */
        @Override
        public void mouseExited(MouseEvent e) {
            button.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * TODO: Documentation
     */
    class TextFieldFocus implements FocusListener {
        /**
         * TODO: Documentation
         */
        private JTextField textField;
        /**
         * TODO: Documentation
         */
        private String placeholder;

        /**
         * TODO: Documentation
         * @param textField
         */
        public TextFieldFocus(JTextField textField, String placeholder) {
            this.textField = textField;
            this.placeholder = placeholder;
        }

        /**
         * TODO: Documentation
         * @param e the event to be processed
         */
        @Override
        public void focusGained(FocusEvent e) {
            if (textField.getText().equals(placeholder)) {
                textField.setText("");
            }
        }

        /**
         * TODO: Documentation
         * @param e the event to be processed
         */
        @Override
        public void focusLost(FocusEvent e) {
            if (textField.getText().equals("")) {
                textField.setText(placeholder);
            }
        }
    }

    /**
     * TODO: Documentation
     */
    class PasswordFocus implements FocusListener {
        /**
         * TODO: Documentation
         */
        private JPasswordField passwordField;
        /**
         * TODO: Documentation
         */
        private JCheckBox chkShowPassword;
        /**
         * TODO: Documentation
         */
        private String placeholder;

        /**
         * TODO: Documentation
         * @param passwordField
         * @param chkShowPassword
         */

        public PasswordFocus(JPasswordField passwordField, JCheckBox chkShowPassword, String placeholder) {
            this.passwordField = passwordField;
            this.passwordField.setText(placeholder);
            this.chkShowPassword = chkShowPassword;
        }

        /**
         * TODO: Documentation
         * @param e the event to be processed
         */
        @Override
        public void focusGained(FocusEvent e) {
            String password = String.valueOf(passwordField.getPassword());
            if (chkShowPassword.isSelected()) {
                passwordField.setEchoChar('●');
            }
            if (password.equals(placeholder)) {
                passwordField.setText("");
            }
        }

        /**
         * TODO: Documentation
         * @param e the event to be processed
         */
        @Override
        public void focusLost(FocusEvent e) {
            String password = String.valueOf(view.getTxtPassword().getPassword());
            if (password.equals(placeholder) || password.equals("")) {
                passwordField.setText(placeholder);
                passwordField.setEchoChar((char) 0);
            }
        }
    }
}

