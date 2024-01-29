package client.view.account_view;

import utilities.Resources;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class UserProfileView extends JFrame {

    Resources res = new Resources();
    GridBagConstraints gbc = new GridBagConstraints();


    public UserProfileView() {
        super("User Profile");

        Container contentArea = new JPanel(new BorderLayout());

        CardLayout cardLayout = new CardLayout();

        JPanel mainPanel = new JPanel(new GridLayout(0, 3));
        contentArea.add(mainPanel, BorderLayout.CENTER);


        mainPanel.add(new ProfileOptionsPanel());
        mainPanel.add(new EditProfile());

        this.setContentPane(contentArea);
        this.setLocationRelativeTo(null);
        this.setSize(950,560);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    class ProfileOptionsPanel extends JPanel {

        public ProfileOptionsPanel() {
            this.setLayout(new GridBagLayout());
            this.setBackground(res.feldgrau);

            gbc.gridy = 0;
            gbc.gridwidth = 1;
             JLabel profilePicture = new JLabel();
            profilePicture.setIcon(res.logoParcs);
            this.add(profilePicture,gbc);
        }

    }

    class EditProfile extends JPanel {


        public EditProfile() {
            this.setLayout(new GridBagLayout());
            gbc.gridy = 0;
            gbc.anchor = gbc.WEST;
            JLabel editProfileText = res.createLblH1("Edit Profile", res.eerieBlack);
            this.add(editProfileText,gbc);

            gbc.gridy = 1;
            gbc.anchor = gbc.WEST;
            JLabel firstNameLabel = res.createLblH4("First Name", res.eerieBlack);
            this.add(firstNameLabel, gbc);

            gbc.gridy=2;
            JTextField firstNameField = res.createTxtRounded("Patrick", res.lightGray, res.gray,20);
            this.add(firstNameField,gbc);

            gbc.gridy = 1;
            gbc.anchor = gbc.EAST;
            JLabel lastNameLabel = res.createLblH4("Last Name", res.eerieBlack);
            this.add(lastNameLabel, gbc);

            gbc.gridy = 2;
            gbc.anchor = gbc.EAST;
            JTextField lastNameField = res.createTxtRounded("Bozorgi", res.lightGray, res.gray,100);
            this.add(lastNameField,gbc);

            this.setVisible(true);

        }

    }

    public static void main(String[] args) {
        UserProfileView obj = new UserProfileView();
    }

}
