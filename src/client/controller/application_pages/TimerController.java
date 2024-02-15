package client.controller.application_pages;

import client.model.application_pages.TimerModel;
import client.view.application_pages.TimerPageView;
import utilities.Resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The TimerController processes the user requests for viewing the current booking.
 * Based on the user request, the TimerController defines methods and invokes methods in the View and Model
 * to accomplish the requested action.
 */
public class TimerController {
    /**
     * The view TimerPageView.
     */
    private TimerPageView view;
    /**
     * The model TimerModel.
     */
    private TimerModel model;

    /**
     * Constructs a TimerController with a specified view and model.
     * @param view The specified view.
     * @param model The specified model.
     */
    public TimerController(TimerPageView view, TimerModel model) {
        this.view = view;
        this.model = model;

        // constants / variables
        // TODO: add method to check if the time today is equal to the time of the booking.
        //  Use the code below to start it:
        // view.getPnlTimerGraphics().getSwingTimer().start();

        // action listeners
        view.getPnlTimer().setEndTimerListener(e -> { // opens a JDialog to show a message
            view.getDlgEndTimer().setLocationRelativeTo(view);
            view.getDlgEndTimer().setVisible(true);
            view.getDlgEndTimer().setAlwaysOnTop(true);
            view.getDlgEndTimer().requestFocusInWindow();
        });
        view.getDlgEndTimer().setConfirmListener(new ConfirmListener()); // actual ending of the ticket timer.
        view.getDlgEndTimer().setCancelListener(e -> view.getDlgEndTimer().dispose());

        // mouse listeners
        view.getPnlTimer().getBtnEndTimer().addMouseListener(new Resources.CursorChanger(view.getPnlTimer().getBtnEndTimer()));
        view.getDlgEndTimer().getBtnConfirm().addMouseListener(new Resources.CursorChanger(view.getDlgEndTimer().getBtnConfirm()));
        view.getDlgEndTimer().getBtnCancel().addMouseListener(new Resources.CursorChanger(view.getDlgEndTimer().getBtnCancel()));
    }

    /**
     * Processes the request for the end timer confirmation.
     */
    class ConfirmListener implements ActionListener {
        /**
         * TODO: Documentation
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Implementation of cancelling the ticket.
        }
    }
}
