package client.controller;

import client.model.LoginRegisterModel;
import client.view.account_view.LoginRegisterView;

import javax.swing.*;

/**
 * Template for LoginRegisterController.
 */
public class LoginRegisterController {
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
     * @param view The specified LoginRegisterView.
     * @param model The specified LoginRegisterModel.
     */
    public LoginRegisterController(LoginRegisterView view, LoginRegisterModel model) {
        this.view = view;
        this.model = model;

        // btnSignup action listener
        view.setSignupListener(e -> {
            view.getCardLayout().show(view.getPnlCards(), "signup");
        });

        // btnBack action listener
        view.setBackListener(e -> {
            view.getCardLayout().show(view.getPnlCards(), "login");
        });
    }
}
