package server.view;

import utilities.Resources;

import javax.swing.*;

public class ReservationsView extends JFrame {

    Resources res = new Resources();

    public ReservationsView(){
        super("Dashboard");

        this.setLocationRelativeTo(null);
        this.setSize(950,560);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class SideBarPanel extends JPanel{
        public SideBarPanel(){

        }
    }

    public static void main(String[] args) {
        new ReservationsView();
    }
}
