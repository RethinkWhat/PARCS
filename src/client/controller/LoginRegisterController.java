package client.controller;

import client.model.Client;
import client.model.LoginRegisterModel;
import client.view.ApplicationView;
import client.view.account_view.LoginRegisterView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Template for LoginRegisterController.
 * The LoginRegisterController processes the user requests for creating an account and logging in.
 * Based on the user request, the LoginRegisterController defines methods and invokes methods in the View and Model
 * to accomplish the requested action.
 */
public class LoginRegisterController {
    /**
     * The client connecting to the server.
     */
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

    public LoginRegisterController(LoginRegisterView view, LoginRegisterModel model) {
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
     * Creates an object that will be sent to the server to verify whether the user's credentials are matching an
     * existing account.
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
     * Creates an object that will be sent to the server for processing.
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
     * Shows the password of a specified JPasswordField.
     */
    class ShowPasswordListener implements ActionListener {
        /**
         * The specified checkbox of show password.
         */
        private JCheckBox checkBox;
        /**
         * The specified password field.
         */
        private JPasswordField passwordField;

        /**
         * Constructs an object of ShowPasswordField listener with a specified JCheckBox and JPasswordField.
         * @param checkBox The specified "show password" checkbox.
         * @param passwordField The specified password field.
         */
        public ShowPasswordListener(JCheckBox checkBox, JPasswordField passwordField) {
            this.checkBox = checkBox;
            this.passwordField = passwordField;
        }

        /**
         * Processes the user request.
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
     * Changes the Cursor when hovered to a specific UI component.
     */
    class CursorChanger extends MouseAdapter {
        /**
         * The specified button.
         */
        private JButton button;

        /**
         * Constructs an object of CursorChanger with a specified JButton.
         * @param button The specified button.
         */
        public CursorChanger(JButton button) {
            this.button = button;
        }

        /**
         * When the mouse hovers inside the vicinity of the UI component.
         * @param e the event to be processed
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        /**
         * When the mouse hovers outside the UI component.
         * @param e the event to be processed
         */
        @Override
        public void mouseExited(MouseEvent e) {
            button.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * Clears the text in a specified JTextField when it is focused, and inserts a specified placeholder
     * text when unfocused.
     */
    class TextFieldFocus implements FocusListener {
        /**
         * The specified text field.
         */
        private JTextField textField;
        /**
         * The specified placeholder text.
         */
        private String placeholder;

        /**
         * Constructs an object of TextFieldFocus with a specified text field and placeholder text.
         * @param textField The specified text field.
         * @param placeholder The specified placeholder text.
         */
        public TextFieldFocus(JTextField textField, String placeholder) {
            this.textField = textField;
            this.placeholder = placeholder;
        }

        /**
         * Processes the event when focused. The text field contents are cleared to accommodate user input.
         * @param e the event to be processed
         */
        @Override
        public void focusGained(FocusEvent e) {
            if (textField.getText().equals(placeholder)) {
                textField.setText("");
            }
        }

        /**
         * Processes the event when unfocused. A placeholder text is inserted in the text field.
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
     * Clears the text in a specified JPasswordField when it is focused, and inserts a specified placeholder
     * text when unfocused.
     */
    class PasswordFocus implements FocusListener {
        /**
         * The specified password field.
         */
        private JPasswordField passwordField;
        /**
         * The specified show password checkbox.
         */
        private JCheckBox chkShowPassword;
        /**
         * The specified placeholder text.
         */
        private String placeholder;

        /**
         * Constructs an object of PasswordFocus with a specified password field, show password text box and
         * placeholder text.
         * @param passwordField The specified password field.
         * @param chkShowPassword The specified show password checkbox.
         * @param placeholder The specified placeholder text.
         */

        public PasswordFocus(JPasswordField passwordField, JCheckBox chkShowPassword, String placeholder) {
            this.passwordField = passwordField;
            this.passwordField.setText(placeholder);
            this.chkShowPassword = chkShowPassword;
        }

        /**
         * Processes the event when focused. The checkbox is overridden and hides the password input, and clears
         * the password field of its placeholder text.
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
         * Processes the event when focused. The checkbox is overridden and displays the password in plain text, and
         * adds a placeholder text.
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

    // temporary main method for debugging
    public static void main(String[] args) {
        new LoginRegisterController(new LoginRegisterView(), new LoginRegisterModel());
    }
}

