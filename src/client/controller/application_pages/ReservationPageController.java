package client.controller.application_pages;

import client.model.application_pages.ReservationPageModel;
import client.view.application_pages.ReservationPageView;

public class ReservationPageController {
    private ReservationPageView view;
    private ReservationPageModel model;
    public ReservationPageController(ReservationPageView view, ReservationPageModel model) {
        this.view = view;
        this.model = model;

        view.setUserFullName(model.getFullName());

        // constants/variables
        // model.setTime();
       // view.getLblDate().setText(model.getTime());

        //System.out.println("Username" + model.getFullName());

        // action listeners

        // mouse listeners

        // focus listeners
    }


}

