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
    private int arcExtent;
    double init;
    double current;
    private javax.swing.Timer t;
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

    private void initComponents() {

        pnlMain = res.createPnlRounded(1100,700,res.white,res.lightGray);
        jPanel2 = new JPanel();
        parcsLogo = new JLabel();
        ticketText = new JLabel();
        homeBtn = new JButton();
        ticketBtn = new JButton();
        userBtn = new JButton();
        exitBtn = new JButton();
        endTimerBtn = new JButton();
        jLabel2 = new JLabel();
        parkingAreaLabel = new JLabel();
        parkingTypeLabel = new JLabel();
        vehicleLabel = new JLabel();
        parkingSpotLabel = new JLabel();
        dateLabel = new JLabel();
        durationLabel = new JLabel();
        hoursLabel = new JLabel();
        parkingAreaInfo = new JLabel();
        parkingTypeInfo = new JLabel();
        vehicleInfo = new JLabel();
        parkingSpotInfo = new JLabel();
        dateInfo = new JLabel();
        durationInfo = new JLabel();
        hoursInfo = new JLabel();
        timerHours = new JLabel();
        timerMinutesLabel = new JLabel();
        timerSeconds = new JLabel();

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setPreferredSize(new Dimension(1100,700));

        parcsLogo.setIcon(new ImageIcon("res/drawable/parcs-logo.png")); // Change Directory

        ticketText.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        ticketText.setForeground(new java.awt.Color(255, 255, 255));
        ticketText.setText("Ticket");

        endTimerBtn.setBackground(new java.awt.Color(230, 92, 92));
        endTimerBtn.setFont(new java.awt.Font("Inter", 0, 14));
        endTimerBtn.setText("End Timer");
        endTimerBtn.setBorder(null);

        jLabel2.setFont(new java.awt.Font("Inter", 1, 25));
        jLabel2.setText("CURRENT TICKET");

        parkingAreaLabel.setFont(new java.awt.Font("Inter", 0, 12));
        parkingAreaLabel.setText("Parking Area");

        parkingTypeLabel.setFont(new java.awt.Font("Inter", 0, 12));
        parkingTypeLabel.setText("Parking Type");

        vehicleLabel.setFont(new java.awt.Font("Inter", 0, 12));
        vehicleLabel.setText("Vehicle");

        parkingSpotLabel.setFont(new java.awt.Font("Inter", 0, 12));
        parkingSpotLabel.setText("Parking Spot");

        dateLabel.setFont(new java.awt.Font("Inter", 0, 12));
        dateLabel.setText("Date");

        durationLabel.setFont(new java.awt.Font("Inter", 0, 12));
        durationLabel.setText("Duration");

        hoursLabel.setFont(new java.awt.Font("Inter", 0, 12));
        hoursLabel.setText("Hours");

        parkingAreaInfo.setFont(new java.awt.Font("Inter", 1, 12));
        parkingAreaInfo.setText("Info");

        parkingTypeInfo.setFont(new java.awt.Font("Inter", 1, 12));
        parkingTypeInfo.setText("Info");

        vehicleInfo.setFont(new java.awt.Font("Inter", 1, 12));
        vehicleInfo.setText("Info");

        parkingSpotInfo.setFont(new java.awt.Font("Inter", 1, 12));
        parkingSpotInfo.setText("Info");

        dateInfo.setFont(new java.awt.Font("Inter", 1, 12));
        dateInfo.setText("Info");

        durationInfo.setFont(new java.awt.Font("Inter", 1, 12));
        durationInfo.setText("Info");

        hoursInfo.setFont(new java.awt.Font("Inter", 1, 12));
        hoursInfo.setText("Info");

        timerHours.setFont(new java.awt.Font("Inter", 0, 12));
        timerHours.setForeground(new java.awt.Color(102, 102, 102));
        timerHours.setText("Hours");

        timerMinutesLabel.setFont(new java.awt.Font("Inter", 0, 12));
        timerMinutesLabel.setForeground(new java.awt.Color(102, 102, 102));
        timerMinutesLabel.setText("Minutes");

        timerSeconds.setFont(new java.awt.Font("Inter", 0, 12));
        timerSeconds.setForeground(new java.awt.Color(102, 102, 102));
        timerSeconds.setText("Seconds");

        GroupLayout pnlMainLayout = new GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
                pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(timerMinutesLabel)
                                                .addGap(29, 29, 29)
                                                .addComponent(timerSeconds)
                                                .addGap(281, 281, 281)
                                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                                                .addComponent(hoursLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(hoursInfo, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                                                .addComponent(durationLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(durationInfo, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                                                .addComponent(dateLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(dateInfo, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                                                .addComponent(parkingSpotLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(parkingSpotInfo, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                                                .addComponent(vehicleLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(vehicleInfo, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                                                .addComponent(parkingTypeLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                                                                .addComponent(parkingTypeInfo, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                                                .addComponent(parkingAreaLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(parkingAreaInfo, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
                                                .addGap(99, 99, 99))
                                        .addGroup(GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                                                .addGap(161, 161, 161)
                                                                .addComponent(timerHours))
                                                        .addGroup(GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                                                .addGap(170, 170, 170)
                                                                .addComponent(endTimerBtn, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                                                .addGap(413, 413, 413)
                                                                .addComponent(jLabel2)))
                                                .addContainerGap(414, Short.MAX_VALUE))))
        );
        pnlMainLayout.setVerticalGroup(
                pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                                .addGap(37, 37, 37)
                                                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                                                .addComponent(parkingAreaLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(parkingTypeLabel)
                                                                        .addComponent(parkingTypeInfo))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(vehicleLabel)
                                                                        .addComponent(vehicleInfo))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(parkingSpotLabel)
                                                                        .addComponent(parkingSpotInfo))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(dateLabel)
                                                                        .addComponent(dateInfo)
                                                                        .addComponent(timerSeconds)
                                                                        .addComponent(timerMinutesLabel)
                                                                        .addComponent(timerHours))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(durationLabel)
                                                                        .addComponent(durationInfo))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(hoursLabel)
                                                                        .addComponent(hoursInfo)))
                                                        .addComponent(parkingAreaInfo))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(endTimerBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                                .addGap(80, 80, 80))))
        );

        add(pnlMain, java.awt.BorderLayout.CENTER);

    }

    // TODO: add the methods below to the controller.
    private void setBtnEndTimerListener(ActionListener actionListener) {
        // TODO add your handling code here:\

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

    // Variables declaration - do not modify
    private JLabel dateInfo;
    private JLabel dateLabel;
    private JLabel durationInfo;
    private JLabel durationLabel;
    private JButton endTimerBtn;
    private JButton exitBtn;
    private JButton homeBtn;
    private JLabel hoursInfo;
    private JLabel hoursLabel;
    private JLabel jLabel2;
    private JPanel pnlMain;
    private JPanel jPanel2;
    private JLabel parcsLogo;
    private JLabel parkingAreaInfo;
    private JLabel parkingAreaLabel;
    private JLabel parkingSpotInfo;
    private JLabel parkingSpotLabel;
    private JLabel parkingTypeInfo;
    private JLabel parkingTypeLabel;
    private JButton ticketBtn;
    private JLabel ticketText;
    private JLabel timerHours;
    private JLabel timerMinutesLabel;
    private JLabel timerSeconds;
    private JButton userBtn;
    private JLabel vehicleInfo;
    private JLabel vehicleLabel;
    // End of variables declaration

    public void paint(Graphics g) {
        super.paint(g);
        drawArc(g);
        drawStopwatch(g);
    }

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