package client.view;


import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author MIzuchi
 */
public class TimerView extends javax.swing.JFrame {

    /**
     * Creates new form timerView
     */
    public TimerView() {

        initComponents();
        JButton [] btns = {homeButtonLabel,ticketButtonLabel,profileButtonLabel,exitButtonLabel};
        for (JButton btn : btns){
            btn.setBackground(new Color(76,102,99));
            btn.setUI(new BasicButtonUI());
            btn.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(new Color(225,225,225));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(new Color(76,102,99));

                }

            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        panelRt = new javax.swing.JPanel();
        panelSide = new javax.swing.JPanel();
        homeButtonLabel = new javax.swing.JButton();
        ticketButtonLabel = new javax.swing.JButton();
        profileButtonLabel = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        exitButtonLabel = new javax.swing.JButton();
        panelCenter = new javax.swing.JPanel();
        panelCar = new javax.swing.JPanel();
        carLogo = new javax.swing.JLabel();
        carAvail = new javax.swing.JLabel();
        avail1 = new javax.swing.JLabel();
        carSlot = new javax.swing.JLabel();
        panelMotor = new javax.swing.JPanel();
        motorLabel = new javax.swing.JLabel();
        motorAvail = new javax.swing.JLabel();
        avail2 = new javax.swing.JLabel();
        motorSlot = new javax.swing.JLabel();
        panelTicket = new javax.swing.JPanel();
        ticketLabel = new javax.swing.JLabel();
        ticketBook = new javax.swing.JLabel();
        avail3 = new javax.swing.JLabel();
        ticket1 = new javax.swing.JLabel();
        informationLabel = new javax.swing.JPanel();
        parkingAreaLabel = new java.awt.Label();
        parkingTypeLabel = new java.awt.Label();
        vehicleLabel = new java.awt.Label();
        parkingSpotLabel = new java.awt.Label();
        dateLabel = new java.awt.Label();
        durationLabel = new java.awt.Label();
        hoursLabel = new java.awt.Label();
        parkingAreaInfoLabel = new java.awt.Label();
        parkingTypeInfoLabel = new java.awt.Label();
        vehicleInfoLabel = new java.awt.Label();
        parkingSpotInfoLabel = new java.awt.Label();
        dateInfoLabel = new java.awt.Label();
        durationInfoLabel = new java.awt.Label();
        hoursInfoLabel = new java.awt.Label();
        endTimerLabel = new javax.swing.JButton();
        searchLabel = new javax.swing.JTextField();
        userFirstNameLabel = new javax.swing.JLabel();
        weekTimeLabel = new javax.swing.JLabel();
        helloLabel1 = new javax.swing.JLabel();
        monthDayYearLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelRt.setLayout(new java.awt.BorderLayout());

        panelSide.setBackground(new java.awt.Color(76, 102, 99));
        panelSide.setPreferredSize(new java.awt.Dimension(55, 100));
        panelSide.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));

        homeButtonLabel.setBackground(new java.awt.Color(76, 102, 99));
        homeButtonLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home-white-outline.png"))); // NOI18N
        homeButtonLabel.setBorder(null);
        homeButtonLabel.setPreferredSize(new java.awt.Dimension(30, 25));
        homeButtonLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonLabelActionPerformed(evt);
            }
        });
        panelSide.add(homeButtonLabel);

        ticketButtonLabel.setBackground(new java.awt.Color(76, 102, 99));
        ticketButtonLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ticket-white-outline_1.png"))); // NOI18N
        ticketButtonLabel.setBorder(null);
        ticketButtonLabel.setPreferredSize(new java.awt.Dimension(30, 25));
        ticketButtonLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ticketButtonLabelActionPerformed(evt);
            }
        });
        panelSide.add(ticketButtonLabel);

        profileButtonLabel.setBackground(new java.awt.Color(76, 102, 99));
        profileButtonLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user-white-outline.png"))); // NOI18N
        profileButtonLabel.setBorder(null);
        profileButtonLabel.setPreferredSize(new java.awt.Dimension(30, 25));
        profileButtonLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileButtonLabelActionPerformed(evt);
            }
        });
        panelSide.add(profileButtonLabel);

        panel.setBackground(new java.awt.Color(76, 102, 99));
        panel.setPreferredSize(new java.awt.Dimension(55, 415));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 55, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 415, Short.MAX_VALUE)
        );

        panelSide.add(panel);

        exitButtonLabel.setBackground(new java.awt.Color(76, 102, 99));
        exitButtonLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit-white-outline.png"))); // NOI18N
        exitButtonLabel.setBorder(null);
        exitButtonLabel.setPreferredSize(new java.awt.Dimension(30, 25));
        exitButtonLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonLabelActionPerformed(evt);
            }
        });
        panelSide.add(exitButtonLabel);

        panelRt.add(panelSide, java.awt.BorderLayout.WEST);

        panelCenter.setBackground(new java.awt.Color(228, 228, 228));
        panelCenter.setPreferredSize(new java.awt.Dimension(835, 575));
        panelCenter.setLayout(null);

        panelCar.setLayout(null);

        carLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/car-black-outline.png"))); // NOI18N
        panelCar.add(carLogo);
        carLogo.setBounds(17, 16, 100, 80);

        carAvail.setFont(new java.awt.Font("Inter", 1, 48)); // NOI18N
        carAvail.setText("00");
        panelCar.add(carAvail);
        carAvail.setBounds(120, 20, 70, 70);

        avail1.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        avail1.setText("Available ");
        panelCar.add(avail1);
        avail1.setBounds(200, 30, 70, 20);

        carSlot.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        carSlot.setText("Car Slots");
        panelCar.add(carSlot);
        carSlot.setBounds(200, 50, 70, 20);

        panelCenter.add(panelCar);
        panelCar.setBounds(10, 70, 300, 120);

        panelMotor.setLayout(null);

        motorLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/motorcycle-black-solid.png"))); // NOI18N
        panelMotor.add(motorLabel);
        motorLabel.setBounds(17, 16, 100, 80);

        motorAvail.setFont(new java.awt.Font("Inter", 1, 48)); // NOI18N
        motorAvail.setText("00");
        panelMotor.add(motorAvail);
        motorAvail.setBounds(120, 20, 70, 70);

        avail2.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        avail2.setText("Available ");
        panelMotor.add(avail2);
        avail2.setBounds(200, 30, 70, 20);

        motorSlot.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        motorSlot.setText("Motor Slots ");
        panelMotor.add(motorSlot);
        motorSlot.setBounds(200, 50, 90, 20);

        panelCenter.add(panelMotor);
        panelMotor.setBounds(330, 70, 310, 120);

        panelTicket.setRequestFocusEnabled(false);
        panelTicket.setLayout(null);

        ticketLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ticket-black-solid.png"))); // NOI18N
        panelTicket.add(ticketLabel);
        ticketLabel.setBounds(10, 10, 100, 100);

        ticketBook.setFont(new java.awt.Font("Inter", 1, 48)); // NOI18N
        ticketBook.setText("00");
        panelTicket.add(ticketBook);
        ticketBook.setBounds(120, 20, 70, 70);

        avail3.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        avail3.setText("Your Total");
        panelTicket.add(avail3);
        avail3.setBounds(200, 30, 80, 20);

        ticket1.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        ticket1.setText("Bookings");
        panelTicket.add(ticket1);
        ticket1.setBounds(200, 50, 70, 20);

        panelCenter.add(panelTicket);
        panelTicket.setBounds(660, 70, 300, 120);

        informationLabel.setLayout(null);

        parkingAreaLabel.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        parkingAreaLabel.setForeground(new java.awt.Color(102, 102, 102));
        parkingAreaLabel.setText("Parking Area");
        informationLabel.add(parkingAreaLabel);
        parkingAreaLabel.setBounds(450, 70, 74, 20);

        parkingTypeLabel.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        parkingTypeLabel.setForeground(new java.awt.Color(102, 102, 102));
        parkingTypeLabel.setText("Parking Type");
        informationLabel.add(parkingTypeLabel);
        parkingTypeLabel.setBounds(450, 100, 75, 20);

        vehicleLabel.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        vehicleLabel.setForeground(new java.awt.Color(102, 102, 102));
        vehicleLabel.setText("Vehicle");
        informationLabel.add(vehicleLabel);
        vehicleLabel.setBounds(450, 130, 44, 20);

        parkingSpotLabel.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        parkingSpotLabel.setForeground(new java.awt.Color(102, 102, 102));
        parkingSpotLabel.setText("Parking Spot");
        informationLabel.add(parkingSpotLabel);
        parkingSpotLabel.setBounds(450, 160, 74, 20);

        dateLabel.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        dateLabel.setForeground(new java.awt.Color(102, 102, 102));
        dateLabel.setText("Date");
        informationLabel.add(dateLabel);
        dateLabel.setBounds(450, 190, 30, 20);

        durationLabel.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        durationLabel.setForeground(new java.awt.Color(102, 102, 102));
        durationLabel.setText("Duration");
        informationLabel.add(durationLabel);
        durationLabel.setBounds(450, 220, 51, 20);

        hoursLabel.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        hoursLabel.setForeground(new java.awt.Color(102, 102, 102));
        hoursLabel.setText("Hours");
        informationLabel.add(hoursLabel);
        hoursLabel.setBounds(450, 250, 38, 20);

        parkingAreaInfoLabel.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        parkingAreaInfoLabel.setForeground(new java.awt.Color(102, 102, 102));
        parkingAreaInfoLabel.setText("Info");
        informationLabel.add(parkingAreaInfoLabel);
        parkingAreaInfoLabel.setBounds(780, 70, 25, 20);

        parkingTypeInfoLabel.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        parkingTypeInfoLabel.setForeground(new java.awt.Color(102, 102, 102));
        parkingTypeInfoLabel.setText("Info");
        informationLabel.add(parkingTypeInfoLabel);
        parkingTypeInfoLabel.setBounds(780, 100, 25, 20);

        vehicleInfoLabel.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        vehicleInfoLabel.setForeground(new java.awt.Color(102, 102, 102));
        vehicleInfoLabel.setText("Info");
        informationLabel.add(vehicleInfoLabel);
        vehicleInfoLabel.setBounds(780, 130, 25, 20);

        parkingSpotInfoLabel.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        parkingSpotInfoLabel.setForeground(new java.awt.Color(102, 102, 102));
        parkingSpotInfoLabel.setText("Info");
        informationLabel.add(parkingSpotInfoLabel);
        parkingSpotInfoLabel.setBounds(780, 160, 25, 20);

        dateInfoLabel.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        dateInfoLabel.setForeground(new java.awt.Color(102, 102, 102));
        dateInfoLabel.setText("Info");
        informationLabel.add(dateInfoLabel);
        dateInfoLabel.setBounds(780, 190, 25, 20);

        durationInfoLabel.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        durationInfoLabel.setForeground(new java.awt.Color(102, 102, 102));
        durationInfoLabel.setText("Info");
        informationLabel.add(durationInfoLabel);
        durationInfoLabel.setBounds(780, 220, 25, 20);

        hoursInfoLabel.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        hoursInfoLabel.setForeground(new java.awt.Color(102, 102, 102));
        hoursInfoLabel.setText("Info");
        informationLabel.add(hoursInfoLabel);
        hoursInfoLabel.setBounds(780, 250, 25, 20);

        endTimerLabel.setBackground(new java.awt.Color(255, 102, 102));
        endTimerLabel.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        endTimerLabel.setText("End Timer");
        endTimerLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endTimerLabelActionPerformed(evt);
            }
        });
        informationLabel.add(endTimerLabel);
        endTimerLabel.setBounds(120, 330, 160, 22);

        panelCenter.add(informationLabel);
        informationLabel.setBounds(10, 210, 950, 400);

        searchLabel.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        searchLabel.setForeground(new java.awt.Color(153, 153, 153));
        searchLabel.setText("Search");
        searchLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchLabelActionPerformed(evt);
            }
        });
        panelCenter.add(searchLabel);
        searchLabel.setBounds(480, 20, 480, 30);

        userFirstNameLabel.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        userFirstNameLabel.setForeground(new java.awt.Color(76, 102, 99));
        userFirstNameLabel.setText("Name!");
        panelCenter.add(userFirstNameLabel);
        userFirstNameLabel.setBounds(70, 10, 60, 30);

        weekTimeLabel.setFont(new java.awt.Font("Inter", 0, 10)); // NOI18N
        weekTimeLabel.setText("Weekday 00:00 AM ");
        panelCenter.add(weekTimeLabel);
        weekTimeLabel.setBounds(90, 40, 110, 20);

        helloLabel1.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        helloLabel1.setText("Hello,");
        panelCenter.add(helloLabel1);
        helloLabel1.setBounds(10, 10, 60, 30);

        monthDayYearLabel.setFont(new java.awt.Font("Inter", 0, 10)); // NOI18N
        monthDayYearLabel.setText("MM DD, YYYY |");
        panelCenter.add(monthDayYearLabel);
        monthDayYearLabel.setBounds(10, 40, 80, 20);

        panelRt.add(panelCenter, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelRt, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>

    private void homeButtonLabelActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void ticketButtonLabelActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void exitButtonLabelActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void profileButtonLabelActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void searchLabelActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void endTimerLabelActionPerformed(java.awt.event.ActionEvent evt) {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TimerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TimerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TimerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TimerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimerView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel avail1;
    private javax.swing.JLabel avail2;
    private javax.swing.JLabel avail3;
    private javax.swing.JLabel carAvail;
    private javax.swing.JLabel carLogo;
    private javax.swing.JLabel carSlot;
    private java.awt.Label dateInfoLabel;
    private java.awt.Label dateLabel;
    private java.awt.Label durationInfoLabel;
    private java.awt.Label durationLabel;
    private javax.swing.JButton endTimerLabel;
    private javax.swing.JButton exitButtonLabel;
    private javax.swing.JLabel helloLabel1;
    private javax.swing.JButton homeButtonLabel;
    private java.awt.Label hoursInfoLabel;
    private java.awt.Label hoursLabel;
    private javax.swing.JPanel informationLabel;
    private javax.swing.JLabel monthDayYearLabel;
    private javax.swing.JLabel motorAvail;
    private javax.swing.JLabel motorLabel;
    private javax.swing.JLabel motorSlot;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelCar;
    private javax.swing.JPanel panelCenter;
    private javax.swing.JPanel panelMotor;
    private javax.swing.JPanel panelRt;
    private javax.swing.JPanel panelSide;
    private javax.swing.JPanel panelTicket;
    private java.awt.Label parkingAreaInfoLabel;
    private java.awt.Label parkingAreaLabel;
    private java.awt.Label parkingSpotInfoLabel;
    private java.awt.Label parkingSpotLabel;
    private java.awt.Label parkingTypeInfoLabel;
    private java.awt.Label parkingTypeLabel;
    private javax.swing.JButton profileButtonLabel;
    private javax.swing.JTextField searchLabel;
    private javax.swing.JLabel ticket1;
    private javax.swing.JLabel ticketBook;
    private javax.swing.JButton ticketButtonLabel;
    private javax.swing.JLabel ticketLabel;
    private javax.swing.JLabel userFirstNameLabel;
    private java.awt.Label vehicleInfoLabel;
    private java.awt.Label vehicleLabel;
    private javax.swing.JLabel weekTimeLabel;
    // End of variables declaration
}