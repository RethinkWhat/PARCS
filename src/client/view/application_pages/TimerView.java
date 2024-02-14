package client.view.application_pages;

import utilities.Resources;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * TODO: Documentation
 * @author MIzuchi
 */
public class TimerView extends JPanel {
    /**
     * The label that holds the information of date.
     */
    private JLabel lblDateInfo;
    /**
     * The label of date.
     */
    private JLabel lblDate;
    /**
     * The label that holds the information of the duration.
     */
    private JLabel lblDurationInfo;
    /**
     * The label of duration.
     */
    private JLabel lblDuration;
    /**
     * The button to end the timer.
     */
    private JButton btnEndTimer;
    /**
     * The label that holds the information of the timer's hours.
     */
    private JLabel lblHoursInfo;
    /**
     * The label of hours.
     */
    private JLabel lblHours;
    /**
     * The title of the panel.
     */
    private JLabel lblTitle;
    /**
     * The panel that holds the components
     */
    private JPanel pnlMain;

    private JPanel jPanel2;
    /**
     * The label that holds the information of the parking area.
     */
    private JLabel lblParkingAreaInfo;
    /**
     * The label of parking area.
     */
    private JLabel lblParkingArea;
    /**
     * The label that holds the information of the parking spot.
     */
    private JLabel lblParkingSpotInfo;
    /**
     * The label of parking spot.
     */
    private JLabel lblParkingSpot;
    /**
     * The label that holds the information of the parking type.
     */
    private JLabel lblParkingTypeInfo;
    /**
     * The label of parking type.
     */
    private JLabel lblParkingType;
    /**
     * The label of the ticket.
     */
    private JLabel lblTicketText;
    /**
     * The label of the hours left in the timer.
     */
    private JLabel lblTimerHours;
    /**
     * The label of the minutes left in the timer.
     */
    private JLabel lblTimerMinutes;
    /**
     * The label of the seconds left in the timer.
     */
    private JLabel lblTimerSeconds;
    /**
     * The label that holds the information of the vehicle.
     */
    private JLabel lblVehicleInfo;
    /**
     * The label of vehicle.
     */
    private JLabel lblVehicle;
    /**
     * The hour of the timer object.
     */
    private int hour;
    /**
     * The minute of the timer object.
     */
    private int minute;
    /**
     * The second of the timer object.
     */
    private int second;
    /**
     * The radius of the timer, representing a countdown.
     */
    private static final int CIRCLE_RADIUS = 152;
    /**
     * The starting angle of the countdown timer.
     */
    private static final int ARC_START_ANGLE = 90;
    /**
     * The extent of the arc.
     */
    private int arcExtent;
    /**
     * The components inside the timer.
     */
    double init;
    /**
     * The current time of the timer.
     */
    double current;

    int positionX;
    int positionY;


    /** Button declarations */
    private JButton homeBtn = new JButton();
    private JButton ticketBtn = new JButton();
    private JButton exitBtn = new JButton();
    private JButton userBtn = new JButton();
    private JButton endTimerBtn = new JButton();


    private JPanel jPanel3 = new JPanel();
    /**
     * Instanc variable of the timer.
     */
    private javax.swing.Timer t;
    /**
     * The stylesheet.
     */
    private Resources res = new Resources();

    /**
     * Constructs a panel of TimerView.
     */
    public TimerView() {
        initComponents(); // invokes the components inside the TimerView.

        // TODO: create factory methods for action listeners

        // instantiation of variables for the "stopwatch"
        arcExtent = 360;
        hour = 1;
        minute = 0;
        second = 0;
        init = hour * 3600 + minute * 60 + second;
        current = init;

        t = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
                repaint();
            }
        });
        t.start();

        this.setPreferredSize(new Dimension(1100,700));
    }

    /**
     * Updates the time left.
     */
    private void updateTime() {
        if (hour == 0 && minute == 0 && second == 0) {
            t.stop();
            setVisible(false);
        } else if (minute == 0 && second == 0) {
            hour--;
            minute = 59;
            second = 59;
        } else if (second == 0) {
            minute--;
            second = 59;
        } else {
            second--;
        }
    }

    /**
     * Instantiates and populates the components of the TimerView panel.
     */
    private void initComponents() {

        pnlMain = res.createPnlRounded(1100,700,res.white,res.lightGray);
        jPanel2 = new JPanel();
        lblTicketText = new JLabel();
        btnEndTimer = res.createBtnRounded("End Timer", res.red, res.eerieBlack, 10);
        lblTitle = new JLabel();
        lblParkingArea = new JLabel();
        lblParkingType = new JLabel();
        lblVehicle = new JLabel();
        lblParkingSpot = new JLabel();
        lblDate = new JLabel();
        lblDuration = new JLabel();
        lblHours = new JLabel();
        lblParkingAreaInfo = new JLabel();
        lblParkingTypeInfo = new JLabel();
        lblVehicleInfo = new JLabel();
        lblParkingSpotInfo = new JLabel();
        lblDateInfo = new JLabel();
        lblDurationInfo = new JLabel();
        lblHoursInfo = new JLabel();
        lblTimerHours = new JLabel();
        lblTimerMinutes = new JLabel();
        lblTimerSeconds = new JLabel();

        pnlMain.setPreferredSize(new Dimension(1100,700));

        lblTicketText.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        lblTicketText.setForeground(res.eerieBlack);
        lblTicketText.setText("Ticket");

        lblTitle.setFont(new java.awt.Font("Inter", 1, 25));
        lblTitle.setText("CURRENT TICKET");

        lblParkingArea.setFont(new java.awt.Font("Inter", 0, 12));
        lblParkingArea.setText("Parking Area");

        lblParkingType.setFont(new java.awt.Font("Inter", 0, 12));
        lblParkingType.setText("Parking Type");

        lblVehicle.setFont(new java.awt.Font("Inter", 0, 12));
        lblVehicle.setText("Vehicle");

        lblParkingSpot.setFont(new java.awt.Font("Inter", 0, 12));
        lblParkingSpot.setText("Parking Spot");

        ImageIcon home = new ImageIcon("res/drawable/icons/home-white-outline.png");
        Image scaledHome = home.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        home = new ImageIcon(scaledHome);
        homeBtn.setBackground(new java.awt.Color(76, 102, 99));
        homeBtn.setIcon(home);
        homeBtn.setBorder(null);


        ImageIcon ticket = new ImageIcon("res/drawable/icons/ticket-white-outline.png");
        Image scaledTicket = ticket.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        ticket = new ImageIcon(scaledTicket);
        ticketBtn.setBackground(new java.awt.Color(76, 102, 99));
        ticketBtn.setSize(50,50);
        ticketBtn.setIcon(ticket); // Change Directory
        ticketBtn.setBorder(null);

        ImageIcon user = new ImageIcon("res/drawable/icons/user-white-outline.png");
        Image scaledUser = user.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        user = new ImageIcon(scaledUser);
        userBtn.setBackground(new java.awt.Color(76, 102, 99));
        userBtn.setSize(50,50);
        userBtn.setIcon(user);
        userBtn.setBorder(null);

        ImageIcon exit= new ImageIcon("res/drawable/icons/exit-white-outline.png");
        Image scaledExit = exit.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        exit = new ImageIcon(scaledExit);
        exitBtn.setBackground(new java.awt.Color(76, 102, 99));
        exitBtn.setSize(50,50);
        exitBtn.setIcon(exit); // Change Directory
        exitBtn.setBorder(null);


        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(ticketBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(exitBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(homeBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(userBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(homeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ticketBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        endTimerBtn.setBackground(new java.awt.Color(230, 92, 92));
        endTimerBtn.setFont(new java.awt.Font("Inter", 0, 14));
        endTimerBtn.setText("End Timer");
        endTimerBtn.setBorder(null);
        endTimerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endTimerBtnActionPerformed(evt);
            }
        });

        lblVehicleInfo.setFont(new java.awt.Font("Inter", 1, 12));
        lblVehicleInfo.setText("Info");

        lblParkingSpotInfo.setFont(new java.awt.Font("Inter", 1, 12));
        lblParkingSpotInfo.setText("Info");

        lblDateInfo.setFont(new java.awt.Font("Inter", 1, 12));
        lblDateInfo.setText("Info");

        lblDurationInfo.setFont(new java.awt.Font("Inter", 1, 12));
        lblDurationInfo.setText("Info");

        lblHoursInfo.setFont(new java.awt.Font("Inter", 1, 12));
        lblHoursInfo.setText("Info");

        lblTimerHours.setFont(new java.awt.Font("Inter", 0, 12));
        lblTimerHours.setForeground(new java.awt.Color(102, 102, 102));
        lblTimerHours.setText("Hours");

        lblTimerMinutes.setFont(new java.awt.Font("Inter", 0, 12));
        lblTimerMinutes.setForeground(new java.awt.Color(102, 102, 102));
        lblTimerMinutes.setText("Minutes");

        lblTimerSeconds.setFont(new java.awt.Font("Inter", 0, 12));
        lblTimerSeconds.setForeground(new java.awt.Color(102, 102, 102));
        lblTimerSeconds.setText("Seconds");

        GroupLayout pnlMainLayout = new GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
                pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblTimerMinutes)
                                                .addGap(29, 29, 29)
                                                .addComponent(lblTimerSeconds)
                                                .addGap(281, 281, 281)
                                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                                                .addComponent(lblHours)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(lblHoursInfo, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                                                .addComponent(lblDuration)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(lblDurationInfo, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                                                .addComponent(lblDate)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(lblDateInfo, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                                                .addComponent(lblParkingSpot)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(lblParkingSpotInfo, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                                                .addComponent(lblVehicle)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(lblVehicleInfo, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                                                .addComponent(lblParkingType)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                                                                .addComponent(lblParkingTypeInfo, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                                                .addComponent(lblParkingArea)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(lblParkingAreaInfo, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
                                                .addGap(99, 99, 99))
                                        .addGroup(GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                                                .addGap(161, 161, 161)
                                                                .addComponent(lblTimerHours))
                                                        .addGroup(GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                                                .addGap(170, 170, 170)
                                                                .addComponent(btnEndTimer, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                                                .addGap(413, 413, 413)
                                                                .addComponent(lblTitle)))
                                                .addContainerGap(414, Short.MAX_VALUE))))
        );
        pnlMainLayout.setVerticalGroup(
                pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                                .addGap(37, 37, 37)
                                                .addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                                                .addComponent(lblParkingArea, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(lblParkingType)
                                                                        .addComponent(lblParkingTypeInfo))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(lblVehicle)
                                                                        .addComponent(lblVehicleInfo))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(lblParkingSpot)
                                                                        .addComponent(lblParkingSpotInfo))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(lblDate)
                                                                        .addComponent(lblDateInfo)
                                                                        .addComponent(lblTimerSeconds)
                                                                        .addComponent(lblTimerMinutes)
                                                                        .addComponent(lblTimerHours))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(lblDuration)
                                                                        .addComponent(lblDurationInfo))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(lblHours)
                                                                        .addComponent(lblHoursInfo)))
                                                        .addComponent(lblParkingAreaInfo))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnEndTimer, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                                .addGap(80, 80, 80))))
        );

        add(pnlMain, java.awt.BorderLayout.CENTER);

    }

    private void endTimerBtnActionPerformed(java.awt.event.ActionEvent evt) {
       // new TicketCancelDialog(null,false);

    }

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {
        // get x and y coordinates values

        positionX = evt.getX();
        positionY = evt.getY();
    }

    private void userBtnActionPerformed(java.awt.event.MouseEvent evt){
        // connect to User Page
       // dispose();
    }

    private void homeBtnActionPerformed(java.awt.event.MouseEvent evt){
        // connect to Home Page
     //   dispose();
    }

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {
        // set JFrame animation
        setLocation(evt.getXOnScreen()-positionX, evt.getYOnScreen()-positionY);
    }

    public static void main(String args[]) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TimerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TimerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TimerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TimerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimerView().setVisible(true);
            }
        });
    }

    /**
     * TODO: Documentation
     * @param g  the <code>Graphics</code> context in which to paint
     */
    public void paint(Graphics g) {
        super.paint(g);
        drawArc(g);
        drawStopwatch(g);
    }

    /**
     * TODO: Documentation
     * @param g
     */
    private void drawArc(Graphics g) {
       /* int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int x = centerX - CIRCLE_RADIUS;
        int y = centerY - CIRCLE_RADIUS;*/
        if (arcExtent >= 0) {
            g.setColor(Color.green);
            g.fillArc(150, 150, 2 * CIRCLE_RADIUS, 2 * CIRCLE_RADIUS, ARC_START_ANGLE, arcExtent);
            g.setColor(Color.white);
            g.fillArc(162,162,280, 280, 0, 360);
        }
        current--;
        arcExtent = (int) ((current / init) * 360.0);
    }

    /**
     * TODO: Documentation
     * @param g
     */
    private void drawStopwatch(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        String timeString = String.format("%02d:%02d:%02d", hour, minute, second);

        FontMetrics fm = g.getFontMetrics();
        int x = 260;
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g.drawString(timeString, x, y);
    }
}