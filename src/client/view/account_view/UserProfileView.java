package client.view.account_view;

import utilities.Resources;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UserProfileView extends JFrame {

    Resources res = new Resources();
    GridBagConstraints gbc = new GridBagConstraints();


    public UserProfileView() {
        super("User Profile");
        this.add(new ProfileOptionsPanel());


        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        mainPanel.setBounds(220,0,750,560);
        mainPanel.add(new EditProfile(), "editProfile");
        mainPanel.add(new EditCars(), "editCars");

        JPanel mainContainer = new JPanel();
        mainContainer.setBounds(220,0,750,560);
        mainContainer.add(mainPanel);
        this.add(mainContainer);


        this.add(new ProfileOptionsPanel());


        cardLayout.show(mainPanel, "editProfile");

        this.setLocationRelativeTo(null);
        this.setSize(950,560);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);


    }

    class ProfileOptionsPanel extends JPanel {

        public ProfileOptionsPanel() {
            this.setLayout(null);
            this.setBackground(res.white);
            this.setVisible(true);

            JLabel homeButton = new JLabel();
            homeButton.setIcon(new ImageIcon("res/drawable/icons/return.png"));
            homeButton.setBounds(40, 5, 100, 100);
            this.add(homeButton);

            ImageIcon profilePicture = new ImageIcon("res/drawable/icons/pfp.png");
            ImageIcon profileImage = new ImageIcon(profilePicture.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            JLabel profileJLabel = new JLabel(profileImage);
            profileJLabel.setBounds(75, 25, 100, 100);
            this.add(profileJLabel);

            JPanel buttonsPanel = new JPanel(new GridLayout(5, 2));
            buttonsPanel.setBackground(res.white);
            buttonsPanel.setBounds(50, 150, 150, 300);
            this.add(buttonsPanel);

            ImageIcon editProfile = new ImageIcon("res/drawable/icons/edit.png");
            JLabel editProfileLabel = res.createLblP("Edit Profile", res.gray);
            ImageIcon editProfileResized = new ImageIcon(editProfile.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            editProfileLabel.setIcon(editProfileResized);
            buttonsPanel.add(editProfileLabel);

            ImageIcon editCars = new ImageIcon("res/drawable/icons/edit-cars.png");
            JLabel editCarsLabel = res.createLblP("Edit Cars", res.gray);
            ImageIcon editCarsResized = new ImageIcon(editCars.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            editCarsLabel.setIcon(editCarsResized);
            buttonsPanel.add(editCarsLabel);

            ImageIcon security = new ImageIcon("res/drawable/icons/security.png");
            JLabel securityLabel = res.createLblP("Security", res.gray);
            ImageIcon securityResized = new ImageIcon(security.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            securityLabel.setIcon(securityResized);
            buttonsPanel.add(securityLabel);

            ImageIcon helpIcon = new ImageIcon("res/drawable/icons/help.png");
            JLabel helpLabel = res.createLblP("Help", res.gray);
            ImageIcon helpResized = new ImageIcon(helpIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            helpLabel.setIcon(helpResized);
            buttonsPanel.add(helpLabel);

            ImageIcon exitIcon = new ImageIcon("res/drawable/icons/exit-grey-outline.png");
            JLabel exitLabel = res.createLblP("Logout", res.gray);
            ImageIcon exitResized = new ImageIcon(exitIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            exitLabel.setIcon(exitResized);
            buttonsPanel.add(exitLabel);

        }
    }

    class EditProfile extends JPanel {
        public EditProfile() {
            this.setPreferredSize(new Dimension(650,490));
            this.setLayout(new GridLayout(8,1));
            //this.setBackground(res.celadon);
            Border border = new EmptyBorder(10,20,10,60);
            this.setBorder(border);
            JLabel editProfile = res.createLblH1("Edit Profile", res.eerieBlack);
            editProfile.setBorder(new EmptyBorder(25,0,0,0));
            editProfile.setFont(new Font("Arial", Font.BOLD, 32));
            this.add(editProfile);

            JPanel nameLabelsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JLabel firstNameLabel = res.createLblH3("First Name", res.eerieBlack);
            firstNameLabel.setBorder(new EmptyBorder(30,0,0,0));

            nameLabelsPanel.add(firstNameLabel);

           // nameLabelsPanel.add(firstNameLabel);
            JLabel lastNameLabel = res.createLblH3("Last Name", res.eerieBlack);
            lastNameLabel.setBorder(new EmptyBorder(30,180,0,0));
            nameLabelsPanel.add(lastNameLabel);

            this.add(nameLabelsPanel);

            JPanel nameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JTextField firstNameField = res.createTxtRounded("Patrick", res.white, res.gray,20);
            firstNameField.setPreferredSize(new Dimension(10,50));

            JTextField lastNameField = res.createTxtRounded("Bozorgi", res.white, res.gray,20);
            lastNameField.setPreferredSize(new Dimension(10,50));
            //firstNameField.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));
            //lastNameField.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
            nameFieldPanel.add(firstNameField);
            nameFieldPanel.add(lastNameField);

            this.add(nameFieldPanel);

            JLabel emailLabel = res.createLblH3("Email", res.eerieBlack);
            emailLabel.setBorder(new EmptyBorder(30,0,0,0));
            this.add(emailLabel);

            JTextField emailField = res.createTxtRounded("Patrick@gmail.com", res.white, res.gray,20);
            emailField.setSize(new Dimension(50,50));
            this.add(emailField);

            JLabel contactLabel = res.createLblH3("Contact", res.eerieBlack);
            contactLabel.setBorder(new EmptyBorder(30,0,0,0));
            this.add(contactLabel);
            JTextField contactField = res.createTxtRounded("+63 917 790 0153", res.white, res.gray,20);
            this.add(contactField);

            JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
            buttonsPanel.setBorder(new EmptyBorder(0,0,5,0));
            JButton cancelButton = res.createBtnRounded("Cancel", res.feldgrau,res.eerieBlack,30);
            cancelButton.setPreferredSize(new Dimension(130,40));
            buttonsPanel.add(cancelButton);
            JButton continueButton = res.createBtnRounded("Continue", res.feldgrau,res.eerieBlack,30);
            continueButton.setPreferredSize(new Dimension(130,40));
            buttonsPanel.add(continueButton);
            this.add(buttonsPanel);



        }
    }

    class EditCars extends JPanel {
        public EditCars() {
            this.setBounds(220,0,750,560);
            this.setBackground(res.feldgrau);
            this.setVisible(true);
            JLabel editProfile = res.createLblH1("Edit Cars", res.eerieBlack);
            this.add(editProfile);
            this.setVisible(true);
        }
    }

    public static void main(String[] args) {
        UserProfileView obj = new UserProfileView();
    }

}
