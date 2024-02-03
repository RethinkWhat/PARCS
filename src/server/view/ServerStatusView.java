package server.view;

import server.model.Server;
import utilities.Resources;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class ServerStatusView extends JFrame {


    private JButton btnNavMenu;
    private JButton btnNavHome;
    private JButton btnNavTicket;
    private JButton btnNavAccount;
    private JButton btnNavLogout;
    private JButton btnAvailCar;
    private JButton btnAvailMotor;
    private JButton btnTotalBookings;


    private JTextField txtSearchBar;


    private JLabel lblName;
    private JLabel lblDate;
    private JLabel lblLocation;

    private GridBagConstraints gbc;


    Resources res = new Resources();


    public ServerStatusView(){
        super("Dashboard");


        Container contentArea = new JPanel(new BorderLayout());


        HeaderPanel pnlHeader = new HeaderPanel();
        contentArea.add(pnlHeader, BorderLayout.NORTH);

        NavbarPanel pnlNavbar = new NavbarPanel();
        contentArea.add(pnlNavbar,BorderLayout.WEST);


        JPanel pnlMain = new JPanel(new BorderLayout());
        pnlMain.setPreferredSize(new Dimension(1100,700));
        pnlMain.setBackground(res.lightGray);
        pnlMain.setBorder(new EmptyBorder(25,25,25,25));
        contentArea.add(pnlMain, BorderLayout.CENTER);


        MainTopPanel pnlMainTop = new MainTopPanel();
        pnlMain.add(pnlMainTop, BorderLayout.NORTH);


        MainBottomPanel pnlMainBottom = new MainBottomPanel();
        pnlMain.add(pnlMainBottom, BorderLayout.SOUTH);


        this.setContentPane(contentArea);
        this.setLocationRelativeTo(null);
        this.setSize(1300,800);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }






    class HeaderPanel extends JPanel{
        public HeaderPanel(){
            setBackground(res.celadon);
            setLayout(new FlowLayout(FlowLayout.LEFT));


            btnNavMenu = res.createBtnIconOnly(res.logoParcs, 20, 20);
            add(btnNavMenu);

            lblLocation = res.createLblH3("Home", res.white);
            add(lblLocation);


            this.setPreferredSize(new Dimension(1300, 40));


        }
    }

    class NavbarPanel extends JPanel{
        public NavbarPanel(){
            setBackground(res.feldgrau);

            btnNavHome = res.createBtnIconOnly(res.iconHome,30,30);
            btnNavHome.setHorizontalAlignment(SwingConstants.LEFT);
            add(btnNavHome);

            btnNavTicket = res.createBtnIconOnly(res.iconTicket,30,30);
            btnNavTicket.setHorizontalAlignment(SwingConstants.LEFT);
            add(btnNavTicket);

            btnNavAccount = res.createBtnIconOnly(res.iconAccount,30,30);
            btnNavAccount.setHorizontalAlignment(SwingConstants.LEFT);
            add(btnNavAccount);

            btnNavLogout = res.createBtnIconOnly(res.iconLogout,30,30);
            btnNavLogout.setHorizontalAlignment(SwingConstants.LEFT);
            add(btnNavLogout);

            this.setPreferredSize(new Dimension(60,800));
        }
    }


    class MainTopPanel extends JPanel{
        public MainTopPanel(){
            setBackground(res.lightGray);
            setLayout(new BorderLayout());

            JPanel pnlInformation = new JPanel(new GridBagLayout());
            pnlInformation.setPreferredSize(new Dimension(1300,50));
            add(pnlInformation, BorderLayout.NORTH);
            pnlInformation.setBackground(res.lightGray);

            gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;

            String userFirstName = "Ramon Emmiel";
            lblName = new JLabel("Hello, " + userFirstName);
            add(lblName);


            JPanel pnlButtons = new JPanel(new GridLayout(0,3));
            pnlButtons.setBackground(res.lightGray);
            add(pnlButtons, BorderLayout.SOUTH);


            JPanel pnlAvailCar = new JPanel();
            pnlAvailCar.setBackground(res.white);
            pnlButtons.add(pnlAvailCar);


            this.setPreferredSize(new Dimension(1300,300));
        }
    }


    class MainBottomPanel extends JPanel{
        public MainBottomPanel(){
            setBackground(res.white);
            setForeground(res.white);
            setBorder(new Resources.RoundedBorder(20));


            setPreferredSize(new Dimension(1300,500));
        }
    }






    public static void main(String[] args) {
        new ServerStatusView();
    }
}

