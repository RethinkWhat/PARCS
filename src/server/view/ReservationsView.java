package server.view;

import utilities.Resources;

import javax.swing.*;
import java.awt.*;

public class ReservationsView extends JFrame {

    private JButton btnNavMenu;
    private JButton btnNavHome;
    private JButton btnNavAccount;
    private JButton btnNavLogout;
    private JButton btnAvailCar;
    private JButton btnAvailMotor;
    private JButton btnTotalBookings;

    private JTextField txtSearchBar;

    private JLabel lblName;
    private JLabel lblDate;

    Resources res = new Resources();

    public ReservationsView(){
        super("Dashboard");

        Container contentArea = new JPanel(new BorderLayout());

        HeaderPanel pnlHeader = new HeaderPanel();
        contentArea.add(pnlHeader, BorderLayout.NORTH);



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

    class MainScreenPanel extends JPanel{
        public MainScreenPanel(){

        }
    }

    class HeaderPanel extends JPanel{
        public HeaderPanel(){

        }
    }

    class ParkingSlotNumbersPanel extends JPanel{
        public ParkingSlotNumbersPanel(){

        }
    }

    class ServerStatusPanel extends JPanel{
        public ServerStatusPanel(){

        }
    }

    public static void main(String[] args) {
        new ReservationsView();
    }
}
