package client.view.application_pages;

import utilities.Resources;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UserProfileView extends JFrame {

    Resources res = new Resources();
    GridBagConstraints gbc = new GridBagConstraints();

    public JLabel btnHome;
    public JButton btnEditProfile;
    public JButton btnEditCars;
    public JButton btnSecurity;
    public JButton historyButton;
    public JButton btnExit;
    public CardLayout cardLayout;
    public JPanel pnlMain;


    public UserProfileView() {

        super("User Profile");
//        this.add(new ProfileOptionsPanel(cardLayout, pnlMain));

        CardLayout cardLayout = new CardLayout();
        pnlMain = new JPanel(cardLayout);
        pnlMain.setBounds(220,0,750,560);
        pnlMain.add(new EditProfile(), "editProfile");
        pnlMain.add(new EditCars(), "editCars");

        JPanel cntnrMain = new JPanel();
        cntnrMain.setBounds(220,0,750,560);
        cntnrMain.add(pnlMain);
        this.add(cntnrMain);

        //shows edit profile first

        cardLayout.show(pnlMain, "editProfile");
//        cardLayout.show(pnlMain, "editCars");

        ProfileOptionsPanel profileOptionsPanel = new ProfileOptionsPanel(cardLayout, pnlMain);
        this.add(profileOptionsPanel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(950,560);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);


    }

    class ProfileOptionsPanel extends JPanel {

        public ProfileOptionsPanel(CardLayout cardLayout, JPanel pnlMain) {
            this.setLayout(null);
            this.setBackground(res.white);
            this.setVisible(true);


            btnHome = new JLabel();
            btnHome.setIcon(new ImageIcon("res/drawable/icons/return.png"));
            btnHome.setBounds(40, 5, 100, 100);
            this.add(btnHome);

            ImageIcon profilePicture = new ImageIcon("res/drawable/icons/pfp.png");
            ImageIcon profileImage = new ImageIcon(profilePicture.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            JLabel lblPFP = new JLabel(profileImage);
            lblPFP.setBounds(75, 25, 100, 100);
            this.add(lblPFP);

            JPanel buttonsPanel = new JPanel(new GridLayout(5, 1));
            buttonsPanel.setBackground(res.white);
            buttonsPanel.setBounds(50, 150, 150, 300);
            this.add(buttonsPanel);

            ImageIcon editProfile = new ImageIcon("res/drawable/icons/edit.png");
            btnEditProfile = res.createBtnTxtOnly("Edit Profile", res.gray);
            btnEditProfile.setHorizontalAlignment(SwingConstants.LEFT);
            ImageIcon editProfileResized = new ImageIcon(editProfile.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            btnEditProfile.setIcon(editProfileResized);
            buttonsPanel.add(btnEditProfile);

            ImageIcon editCars = new ImageIcon("res/drawable/icons/edit-cars.png");
            btnEditCars = res.createBtnTxtOnly("Edit Cars", res.gray);
            btnEditCars.setHorizontalAlignment(SwingConstants.LEFT);
            ImageIcon editCarsResized = new ImageIcon(editCars.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            btnEditCars.setIcon(editCarsResized);
            buttonsPanel.add(btnEditCars);

            ImageIcon security = new ImageIcon("res/drawable/icons/security.png");
            btnSecurity = res.createBtnTxtOnly("Security", res.gray);
            btnSecurity.setHorizontalAlignment(SwingConstants.LEFT);
            ImageIcon securityResized = new ImageIcon(security.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            btnSecurity.setIcon(securityResized);
            buttonsPanel.add(btnSecurity);

            ImageIcon historyIcon = new ImageIcon("res/drawable/icons/history.png");
            historyButton = res.createBtnTxtOnly("History", res.gray);
            historyButton.setHorizontalAlignment(SwingConstants.LEFT);
            ImageIcon helpResized = new ImageIcon(historyIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            historyButton.setIcon(helpResized);
            buttonsPanel.add(historyButton);

            ImageIcon exitIcon = new ImageIcon("res/drawable/icons/exit-grey-outline.png");
            btnExit = res.createBtnTxtOnly("Logout", res.gray);
            btnExit.setHorizontalAlignment(SwingConstants.LEFT);
            ImageIcon exitResized = new ImageIcon(exitIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            btnExit.setIcon(exitResized);
            buttonsPanel.add(btnExit);

            btnEditProfile.addActionListener(e -> {
                cardLayout.show(pnlMain, "editProfile");
            });

            btnEditCars.addActionListener(e -> {
                cardLayout.show(pnlMain, "editCars");
            });

        }
    }

    class EditProfile extends JPanel {
        public EditProfile() {
            this.setPreferredSize(new Dimension(650,490));
            this.setLayout(new GridLayout(8,1));
            //this.setBackground(res.celadon);
            Border border = new EmptyBorder(10,20,10,60);
            this.setBorder(border);
            JLabel lblEditProfile = res.createLblH1("Edit Profile", res.eerieBlack);
            lblEditProfile.setBorder(new EmptyBorder(25,0,0,0));
            lblEditProfile.setFont(new Font("Arial", Font.BOLD, 32));
            this.add(lblEditProfile);

            JPanel pnlNameLabels = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JLabel lblFirstName = res.createLblH3("First Name", res.eerieBlack);
            lblFirstName.setBorder(new EmptyBorder(30,0,0,0));

            pnlNameLabels.add(lblFirstName);

           // nameLabelsPanel.add(firstNameLabel);
            JLabel lastNameLabel = res.createLblH3("Last Name", res.eerieBlack);
            lastNameLabel.setBorder(new EmptyBorder(30,180,0,0));
            pnlNameLabels.add(lastNameLabel);

            this.add(pnlNameLabels);

            JPanel pnlNameField = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JTextField firstNameField = res.createTxtRounded("Patrick", res.white, res.gray,20);
            firstNameField.setPreferredSize(new Dimension(10,50));

            JTextField fieldLastName = res.createTxtRounded("Bozorgi", res.white, res.gray,20);
            fieldLastName.setPreferredSize(new Dimension(10,50));
            pnlNameField.add(firstNameField);
            pnlNameField.add(fieldLastName);

            this.add(pnlNameField);

            JLabel lblEmail = res.createLblH3("Email", res.eerieBlack);
            lblEmail.setBorder(new EmptyBorder(30,0,0,0));
            this.add(lblEmail);

            JTextField fieldEmail = res.createTxtRounded("Patrick@gmail.com", res.white, res.gray,20);
            fieldEmail.setSize(new Dimension(50,50));
            this.add(fieldEmail);

            JLabel lblContact = res.createLblH3("Contact", res.eerieBlack);
            lblContact.setBorder(new EmptyBorder(30,0,0,0));
            this.add(lblContact);

            JTextField fieldContact = res.createTxtRounded("+63 917 790 0153", res.white, res.gray,20);
            this.add(fieldContact);

            JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.LEADING));
            pnlButtons.setBorder(new EmptyBorder(0,0,5,0));
            JButton btnCancel = res.createBtnRounded("Cancel", res.feldgrau,res.eerieBlack,30);
            btnCancel.setPreferredSize(new Dimension(130,40));
            pnlButtons.add(btnCancel);
            JButton btnContinue = res.createBtnRounded("Continue", res.feldgrau,res.eerieBlack,30);
            btnContinue.setPreferredSize(new Dimension(130,40));
            pnlButtons.add(btnContinue);
            this.add(pnlButtons);
        }
    }

    class EditCars extends JPanel {
        public EditCars() {

            this.setBounds(220,0,750,560);
            this.setVisible(true);
            this.setLayout(new GridBagLayout());

            gbc.gridy=0;
            gbc.anchor = GridBagConstraints.WEST;
            JLabel lblEditProfile = res.createLblH1("Edit Cars", res.eerieBlack);
            lblEditProfile.setBorder(new EmptyBorder(30,20,0,0));
            this.add(lblEditProfile,gbc);

            gbc.gridy=1;
            gbc.anchor = GridBagConstraints.WEST;

            JPanel pnlCar1 = createCarsLayout("A-0130934023", "Sedan", "Honda Civic");
            JPanel pnlCar2 = createCarsLayout("A-0130934023", "Sedan", "Honda Civic");
            this.add(pnlCar1,gbc);
            this.add(pnlCar2,gbc);

            JPanel pnlCar3 = createCarsLayout("A-0130934023", "Sedan", "Honda Civic");
            JPanel pnlCar4 = createCarsLayout("A-0130934023", "Sedan", "Honda Civic");
            gbc.gridy=2;
            gbc.insets = new Insets(20,30,0,30);
            this.add(pnlCar3,gbc);
            this.add(pnlCar4,gbc);

            gbc.gridy=5;
            gbc.gridx = 1;
            JButton btnBack = new JButton();
            ImageIcon backIcon = new ImageIcon("res/drawable/icons/forward.png");
            btnBack.setBorder(new EmptyBorder(0,0,0,20));
            ImageIcon resizedBackIcon = new ImageIcon(backIcon.getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH));
            btnBack.setIcon(resizedBackIcon);
            this.add(btnBack,gbc);


            this.setVisible(true);
        }

        public JPanel createCarsLayout(String plateNumber, String vehicleType, String model){//String PlateNumber, String vehicle, String model){
            JPanel pnlCarInformation = res.createPnlRounded(290,150, res.feldgrau, res.gray);
            //pnlCarInformation.setBackground(res.feldgrau);
            pnlCarInformation.setLayout(new GridBagLayout());

            JPanel innerContent = new JPanel(new GridBagLayout());
            innerContent.setBackground(res.feldgrau);
            //innerContent.setBackground(res.feldgrau);


            gbc = new GridBagConstraints();
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;

            gbc.insets = new Insets(0,20,0,0);
            JLabel lblPlateNumber = res.createLblP("Plate Number",res.eerieBlack);
            lblPlateNumber.setMaximumSize(new Dimension(150,20));
            innerContent.add(lblPlateNumber,gbc);

            gbc.gridy = 1;
            JLabel lblPlateNumberInfo = res.createLblP2(plateNumber, res.white);
            lblPlateNumberInfo.setMaximumSize(new Dimension(150,30));
            innerContent.add(lblPlateNumberInfo,gbc);

            gbc.gridy = 2;
            JLabel lblEmpty = new JLabel(" ");
            innerContent.add(lblEmpty,gbc);

            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.WEST;
            JLabel lblVehicle = res.createLblP("Vehicle",res.eerieBlack);
            lblVehicle.setMaximumSize(new Dimension(120,12));
            innerContent.add(lblVehicle,gbc);

            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.insets = new Insets(0,0,0,20);
            JLabel lblModel = res.createLblP("Model",res.eerieBlack);
            lblModel.setMaximumSize(new Dimension(110,12));
            innerContent.add(lblModel,gbc);

            gbc.insets = new Insets(0,20,0,0);
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.WEST;
            JLabel lblVehicleInfo = res.createLblP(vehicleType,res.white);
            lblVehicleInfo.setMaximumSize(new Dimension(95,12));
            innerContent.add(lblVehicleInfo,gbc);


            gbc.insets = new Insets(0,0,0,20);
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.EAST;
            JLabel lblModelInfo = res.createLblP(model,res.white);
            lblModelInfo.setMaximumSize(new Dimension(100,20));
            innerContent.add(lblModelInfo,gbc);






            gbc.insets = new Insets(20,30,20,30);
            pnlCarInformation.add(innerContent);

            return pnlCarInformation;



        }
    }
    public static void main(String[] args) {
        UserProfileView obj = new UserProfileView();
    }




}
