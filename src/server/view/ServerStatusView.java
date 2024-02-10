package server.view;

import utilities.Resources;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;


public class ServerStatusView extends JFrame {


    private JButton btnNavMenu;
    private JButton btnNavHome;
    private JButton btnNavTicket;
    private JButton btnNavAccount;
    private JButton btnNavLogout;
    private JButton btnAvailCar;
    private JButton btnAvailMotor;
    private JButton btnTotalBookings;
    private JButton serverSwitch;


    private JTextField txtSearchBar;


    private JLabel lblName;
    private JLabel lblDate;
    private JLabel lblLocation;
    private JLabel serverPrompt;
    private JLabel serverStatus;

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
        this.pack();
        this.setSize(1300,800);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }






    class HeaderPanel extends JPanel{
        public HeaderPanel(){
            setBackground(res.celadon);
            setLayout(new FlowLayout(FlowLayout.LEFT));


            btnNavMenu = res.createBtnIconOnly(res.logoParcs, 40, 30);
            add(btnNavMenu);

            lblLocation = res.createLblH3("Home", res.white);
            add(lblLocation);


            this.setPreferredSize(new Dimension(1300, 50));


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
            gbc.ipadx = 475;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;
            String userFirstName = "Ramon Emmiel";
            lblName = res.createLblH2("Hello, " + userFirstName + "!", res.eerieBlack);
            pnlInformation.add(lblName,gbc);

            gbc.gridy = 1;
            gbc.gridwidth = 3;
            String date = "February 1, 2024 | Thursday 8:41 PM";
            lblDate = res.createLblH4(date, res.eerieBlack);
            pnlInformation.add(lblDate,gbc);

            gbc.gridy = 0;
            gbc.gridx = 1;
            gbc.gridwidth = 5;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.fill = GridBagConstraints.BOTH;
            txtSearchBar = res.createTxtRounded("Search date", res.white, res.gray,30);
            pnlInformation.add(txtSearchBar,gbc);

            GridLayout gridLayout = new GridLayout(0,3);
            gridLayout.setHgap(10);

            JPanel pnlButtons = new JPanel(gridLayout);
            pnlButtons.setPreferredSize(new Dimension(1300,100));
            pnlButtons.setBorder(new EmptyBorder(10,0,10,0));
            pnlButtons.setBackground(res.lightGray);
            add(pnlButtons, BorderLayout.SOUTH);

            ButtonPanel pnlAvailCar = new ButtonPanel(
                    btnAvailCar = res.createBtnIconOnly(res.iconSolidCar,50,50),
                    res.createLblH1("13",res.eerieBlack),
                    res.createLblP("Available Car Slots", res. eerieBlack)
            );
            pnlButtons.add(pnlAvailCar);

            ButtonPanel pnlAvailMotor = new ButtonPanel(
                    btnAvailMotor = res.createBtnIconOnly(res.iconSolidMotor,50,50),
                    res.createLblH1("10", res.eerieBlack),
                    res.createLblP("Available Motor Slots", res.eerieBlack)
            );
            pnlButtons.add(pnlAvailMotor);

            ButtonPanel pnlTotalBookings = new ButtonPanel(
                    btnTotalBookings = res.createBtnIconOnly(res.iconSolidTicket,50,50),
                    res.createLblH1("3",res.eerieBlack),
                    res.createLblP("Your Total Bookings", res.eerieBlack)
            );
            pnlButtons.add(pnlTotalBookings);

            this.setPreferredSize(new Dimension(1300,150));
        }
    }


    class MainBottomPanel extends JPanel{
        public MainBottomPanel(){
            setBackground(res.white);
            setForeground(res.white);
            setBorder(new Resources.RoundedBorder(20));
            setLayout(new GridBagLayout());

            serverPrompt = res.createLblH1("SERVER STATUS", res.eerieBlack);
            serverPrompt.setHorizontalAlignment(SwingConstants.CENTER);
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(serverPrompt, gbc);

            //To be dynamically changed in the controller
            serverStatus = res.createLblH1("OFFLINE",res.red);
            gbc.gridx = 0;
            gbc.gridy = 2;
            add(serverStatus,gbc);

            serverSwitch = res.createBtnRounded("Start Server", res.white, res.celadon, 15);
            serverSwitch.setPreferredSize(new Dimension(200,50));
            gbc.gridx = 0;
            gbc.gridy = 3;
            add(serverSwitch,gbc);



            setPreferredSize(new Dimension(1300,650));
        }
    }

    class ButtonPanel extends JPanel{
        public ButtonPanel(JButton button, JLabel number, JLabel title){
            setBackground(res.white);
            setLayout(new GridLayout());

            gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,5,5,5);

            gbc.gridx = 0;
            gbc.gridy = 0;
            add(button,gbc);

            gbc.gridx = 1;
            add(number,gbc);

            gbc.gridx = 2;
            add(title,gbc);

            this.setPreferredSize(new Dimension(200,100));
        }
    }

    public JButton getServerSwitch() {
        return serverSwitch;
    }

    public JLabel getServerPrompt() {
        return serverPrompt;
    }

    public JLabel getServerStatus() {
        return serverStatus;
    }

    public void setServerListener(ActionListener actionListener){
        serverSwitch.addActionListener(actionListener);
    }

    public void setOnline(){
        serverStatus.setText("ONLINE");
        serverStatus.setForeground(res.celadon);

        serverSwitch.setText("Terminate Server");
        serverSwitch.setBackground(res.red);
    }

    public void setOffline(){
        serverStatus.setText("OFFLINE");
        serverStatus.setForeground(res.red);

        serverSwitch.setText("Start Server");
        serverSwitch.setBackground(res.celadon);
    }

    public static void main(String[] args) {
        ServerStatusView obj = new ServerStatusView();
    }

}

