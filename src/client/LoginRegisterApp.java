package client;

import client.controller.LoginRegisterController;
import client.model.LoginRegisterModel;
import client.view.LoginRegisterView;

public class LoginRegisterApp implements Runnable{
    @Override
    public void run() {
        LoginRegisterModel model = new LoginRegisterModel();
        LoginRegisterView view = new LoginRegisterView();
        new LoginRegisterController(view, model);
    }
}
