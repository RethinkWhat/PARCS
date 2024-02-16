package client.view.application_pages;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

import static javax.swing.BorderFactory.createLineBorder;

public class HistoryPageView extends JPanel{

    //integer variables that indicated what page the user is currently viewing
    private int currentPageIndex;

    //the panel cards that allows user to toggle between different sets of history info
    private JPanel pnlCards;
    private CardLayout userHisLayout = new CardLayout();

    //declaring other variables which constructed the entire GUI
    //buttons
    private JButton previousButton;
    private JButton nextButton;

    //panels that showed related info to user
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JLayeredPane jLayeredPane;
    private ArrayList<JPanel> pages;
    private ArrayList<JLabel> dateTicket;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private ArrayList<JLabel> parkingArea;
    private ArrayList<JLabel> parkingType;
    private ArrayList<JLabel> vehicle;
    private ArrayList<JLabel> parkingSpot;
    private ArrayList<JLabel> hours;
    private ArrayList<JLabel> duration;
    private ArrayList<JLabel> parkingAreaInfo;
    private ArrayList<JLabel> parkingTypeInfo;
    private ArrayList<JLabel> vehicleInfo;
    private ArrayList<JLabel> parkingSpotInfo;
    private ArrayList<JLabel> hoursInfo;
    private ArrayList<JLabel> durationInfo;

    public HistoryPageView(){
        setLayout(new BorderLayout());
        components();

        currentPageIndex = 0;

        // body panel acting as a container to hold all UI components
        Container contentArea = new JPanel(new BorderLayout());

        //panelCards for customer list
        pnlCards = new JPanel(userHisLayout);
        pnlCards.setPreferredSize(new Dimension(1100,700));
        contentArea.add(pnlCards, BorderLayout.CENTER);


    }

    private void components(){
        //instantiation of variables used in JPanel and JLabel—
        //displaying user info concerning their reservation histories
        previousButton = new JButton();
        nextButton = new JButton();
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();
        jLayeredPane = new JLayeredPane();

        pages = new ArrayList<JPanel>();
        dateTicket = new ArrayList<JLabel>();
        jSeparator1 = new JSeparator();
        jSeparator2 = new JSeparator();
        parkingArea = new ArrayList<JLabel>();
        parkingType = new ArrayList<JLabel>();
        vehicle = new ArrayList<JLabel>();
        parkingSpot = new ArrayList<JLabel>();
        hours = new ArrayList<JLabel>();
        duration = new ArrayList<JLabel>();
        parkingAreaInfo = new ArrayList<JLabel>();
        parkingTypeInfo = new ArrayList<JLabel>();
        vehicleInfo = new ArrayList<JLabel>();
        parkingSpotInfo = new ArrayList<JLabel>();
        hoursInfo = new ArrayList<JLabel>();
        durationInfo = new ArrayList<JLabel>();
 

        jPanel1.setBackground(new Color(255, 255, 255));
        jLayeredPane.setForeground(new Color(153, 153, 153));
        jLayeredPane.setLayout(new OverlayLayout(jLayeredPane));

        /*
        Note: the values were for demonstration purposes, where the model and controller class
        will send required data to this class in the future
         */
        //adding values to their respective arrays
        for(int i=0;i<7;i++){
            //JPanel Page instantiation
            JPanel pg = new JPanel();
            pg.setBorder(createLineBorder(new Color(102,102,102)));
            //dateTicket variable instantiation
            JLabel dt = new JLabel();
            dt.setFont(new Font("Inter", 1, 14));
            switch(i){
                case 0:
                    dt.setText("February 17 2024");
                    break;
                case 1:
                    dt.setText("June 17 2024");
                    break;
                case 2:
                    dt.setText("July 17 2024");
                    break;
                case 3:
                    dt.setText("October 23 2024");
                    break;
                case 4:
                    dt.setText("March 5 2024");
                    break;
                case 5:
                    dt.setText("April 1 2024");
                    break;
                case 6:
                    dt.setText("May 9 2024");
                    break;
            }
            //page instantiation
            JPanel page = new JPanel();
            page.setBorder(createLineBorder(new Color(102,102,102)));
            //parkingArea instantiation
            JLabel pa = new JLabel();
            pa.setFont(new Font("Inter", 1, 12)); // NOI18N
            pa.setText("Info");
            //parkingType instantiation
            JLabel pt = new JLabel();
            pt.setFont(new Font("Inter", 0, 12)); // NOI18N
            pt.setText("Parking Type");
            //vehicle instantiation
            JLabel ve = new JLabel();
            ve.setFont(new Font("Inter", 0, 12)); // NOI18N
            ve.setText("Vehicle");
            //parkingSpot instantiation
            JLabel ps = new JLabel();
            ps.setFont(new Font("Inter", 0, 12));
            ps.setText("Parking Spot");
            //hours instantiation
            JLabel hrs = new JLabel();
            hrs.setFont(new Font("Inter", 0, 12));
            hrs.setText("Hours");
            //duration instantiation
            JLabel dura = new JLabel();
            dura.setFont(new Font("Inter", 1, 12));
            dura.setText("Info");//getter method to provide info
            //ParkingAreaInfo instantiation
            JLabel pkArInfo = new JLabel();
            pkArInfo.setFont(new Font("Inter", 1, 12));
            pkArInfo.setText("Info");//getter method to provide info
            //parkingTypeInfo instantiation
            JLabel pkTpInfo = new JLabel();
            pkTpInfo.setFont(new Font("Inter", 0, 12)); // NOI18N
            pkTpInfo.setText("Parking Type");
            //vehicleInfo instantiation
            JLabel veInfo = new JLabel();
            veInfo.setFont(new Font("Inter", 1, 12));
            veInfo.setText("Info"); //getter method to provide info
            //parkingSpotInfo instantiation
            JLabel pkSpInfo = new JLabel();
            pkSpInfo.setFont(new Font("Inter",1,12));
            pkSpInfo.setText("Info"); //getter method to provide info
            //hoursInfo instantiation
            JLabel hrsInfo = new JLabel();
            hrsInfo.setFont(new Font("Inter", 1, 12));
            hrsInfo.setText("Info"); //getter method to provide info
            //durationInfo instantiation
            JLabel duraInfo = new JLabel();
            duraInfo.setFont(new Font("Inter", 1, 12));
            duraInfo.setText("Info");

            pages.add(pg);
            dateTicket.add(dt);
            parkingArea.add(pa);
            parkingType.add(pt);
            vehicle.add(ve);
            parkingSpot.add(ps);
            hours.add(hrs);
            duration.add(dura);
            parkingAreaInfo.add(pkArInfo);
            parkingTypeInfo.add(pkTpInfo);
            vehicleInfo.add(veInfo);
            parkingSpotInfo.add(pkSpInfo);
            hoursInfo.add(hrsInfo);
            durationInfo.add(duraInfo);
        }

        jSeparator1.setForeground(new Color(102,102,102));
        jSeparator2.setForeground(new Color(102,102,102));

        for(int i = 0; i < pages.size(); i++){
            GroupLayout gpLayout = new GroupLayout(pages.get(i));
            pages.get(i).setLayout(gpLayout);
            gpLayout.setHorizontalGroup(
                    gpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(gpLayout.createSequentialGroup()
                                    .addGap(413, 413, 413)
                                    .addComponent(dateTicket.get(i))
                                    .addContainerGap(423, Short.MAX_VALUE))
                            .addGroup(gpLayout.createSequentialGroup()
                                    .addGap(31, 31, 31)
                                    .addGroup(gpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(gpLayout.createSequentialGroup()
                                                    .addComponent(hours.get(i))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(hoursInfo.get(i)))
                                            .addGroup(gpLayout.createSequentialGroup()
                                                    .addComponent(parkingSpot.get(i))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(parkingSpotInfo.get(i)))
                                            .addGroup(gpLayout.createSequentialGroup()
                                                    .addComponent(duration.get(i))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(durationInfo.get(i)))
                                            .addGroup(gpLayout.createSequentialGroup()
                                                    .addGroup(gpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(vehicle.get(i))
                                                            .addComponent(parkingType.get(i))
                                                            .addComponent(parkingArea.get(i)))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(gpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(vehicleInfo.get(i), javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(parkingAreaInfo.get(i), javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(parkingTypeInfo.get(i), javax.swing.GroupLayout.Alignment.TRAILING))))
                                    .addGap(63, 63, 63))
            );
            gpLayout.setVerticalGroup(
                    gpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(gpLayout.createSequentialGroup()
                                    .addGap(25, 25, 25)
                                    .addComponent(dateTicket.get(i))
                                    .addGap(39, 39, 39)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(gpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(parkingArea.get(i))
                                            .addComponent(parkingAreaInfo.get(i)))
                                    .addGap(23, 23, 23)
                                    .addGroup(gpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(parkingType.get(i))
                                            .addComponent(parkingTypeInfo.get(i)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                    .addGroup(gpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(vehicle.get(i))
                                            .addComponent(vehicleInfo.get(i)))
                                    .addGap(26, 26, 26)
                                    .addGroup(gpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(parkingSpot.get(i))
                                            .addComponent(parkingSpotInfo.get(i)))
                                    .addGap(25, 25, 25)
                                    .addGroup(gpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(hours.get(i))
                                            .addComponent(hoursInfo.get(i)))
                                    .addGap(28, 28, 28)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)
                                    .addGroup(gpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(duration.get(i))
                                            .addComponent(durationInfo.get(i)))
                                    .addGap(74, 74, 74))
            );
            jLayeredPane.add(pages.get(i));

        }
        previousButton.setText("Previous");
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        nextButton.setText("Next");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, 958, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(42, 42, 42))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(449, 449, 449)
                                                .addComponent(previousButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(previousButton)
                                                        .addComponent(nextButton))
                                                .addGap(30, 30, 30))))
        );
        add(jPanel1, java.awt.BorderLayout.CENTER); 
    }

    //method that invokes previousPage method
    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {
        previousPage();
    }
    //method that invokes nextPage method
    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {
        nextPage();
    }
    // Method to switch to the next page
    private void nextPage() {
        if (currentPageIndex < pages.size() - 1) {
            pages.get(currentPageIndex).setVisible(false); // Hide current page
            currentPageIndex++; // Move to the next page
            pages.get(currentPageIndex).setVisible(true); // Show next page
        }
    }

    // Method to switch to the previous page
    private void previousPage() {
        if (currentPageIndex > 0) {
            pages.get(currentPageIndex).setVisible(false); // Hide current page
            currentPageIndex--; // Move to the previous page
            pages.get(currentPageIndex).setVisible(true); // Show previous page
        }
    }

    //main method to test output
    public static void main(String args[]){
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HistoryPageView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HistoryPageView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HistoryPageView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HistoryPageView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HistoryPageView historyPage = new HistoryPageView();
                JFrame frame = new JFrame("History Page");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(historyPage);
                frame.pack();
                frame.setLocationRelativeTo(null); // Center the frame on the screen
                frame.setVisible(true);
            }
        });
    }
    //getters:
    public JPanel getPnlCards() {
        return pnlCards;
    }
}
