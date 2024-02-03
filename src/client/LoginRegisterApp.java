package client;

import client.controller.LoginRegisterController;
import client.model.LoginRegisterModel;
import client.view.account_view.LoginRegisterView;

public class LoginRegisterApp {
    public static void main(String[] args) {
        LoginRegisterModel model = new LoginRegisterModel();
        LoginRegisterView view = new LoginRegisterView();
        LoginRegisterController controller = new LoginRegisterController(view, model);
    }
}
