package client;

import client.controller.account.LoginRegisterController;
import client.model.account.LoginRegisterModel;
import client.view.account.LoginRegisterView;

public class LoginRegisterApp implements Runnable{
    @Override
    public void run() {
        LoginRegisterModel model = new LoginRegisterModel();
        LoginRegisterView view = new LoginRegisterView();
        new LoginRegisterController(view, model);
    }
}
