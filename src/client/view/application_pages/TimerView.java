package client.view.application_pages;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * TODO: Documentation
 * @author MIzuchi
 */
public class TimerView extends JPanel {
    // declare variables to store X and Y coordinates values.
    int positionX = 0;
    int positionY = 0;
    //declare variables to incorporate with Timer class
    private int hour;
    private int minute;
    private int second;
    private static final int CIRCLE_RADIUS = 152;
    private static final int ARC_START_ANGLE = 90;
    private int arcExtent;
    double init;
    double current;
    private javax.swing.Timer t;
    //declaring other variables to perform buffer, dealing with flickering "stopwatch" graphics
    private BufferStrategy bufferStrategy;

    public TimerView() {
        initComponents();

        // TODO: create factory methods for action listeners
        JButton [] btns = {homeBtn,ticketBtn,userBtn,exitBtn};
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

        /*
        createBufferStrategy(2);
        bufferStrategy = getBufferStrategy();
        setIgnoreRepaint(true);

         */

        //instantiation of variables for the "stopwatch"
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
    }
    public void draw() {
        // Get the graphics context from the buffer strategy
        Graphics g = bufferStrategy.getDrawGraphics();

        // Clear the screen
        g.clearRect(0, 0, getWidth(), getHeight());

        // Dispose the graphics context
        g.dispose();

        // Show the buffer
        bufferStrategy.show();
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        parcsLogo = new javax.swing.JLabel();
        ticketText = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        homeBtn = new javax.swing.JButton();
        ticketBtn = new javax.swing.JButton();
        userBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        endTimerBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        parkingAreaLabel = new javax.swing.JLabel();
        parkingTypeLabel = new javax.swing.JLabel();
        vehicleLabel = new javax.swing.JLabel();
        parkingSpotLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        durationLabel = new javax.swing.JLabel();
        hoursLabel = new javax.swing.JLabel();
        parkingAreaInfo = new javax.swing.JLabel();
        parkingTypeInfo = new javax.swing.JLabel();
        vehicleInfo = new javax.swing.JLabel();
        parkingSpotInfo = new javax.swing.JLabel();
        dateInfo = new javax.swing.JLabel();
        durationInfo = new javax.swing.JLabel();
        hoursInfo = new javax.swing.JLabel();
        timerHours = new javax.swing.JLabel();
        timerMinutesLabel = new javax.swing.JLabel();
        timerSeconds = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(128, 207, 169));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        parcsLogo.setIcon(new javax.swing.ImageIcon("res/drawable/parcs-logo.png")); // Change Directory

        ticketText.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        ticketText.setForeground(new java.awt.Color(255, 255, 255));
        ticketText.setText("Ticket");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(parcsLogo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ticketText)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(parcsLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ticketText)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(76, 102, 99));

        ImageIcon home = new ImageIcon("res/drawable/icons/exit-white-outline.png");
        Image scaledHome = home.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        home = new ImageIcon(scaledHome);
        homeBtn.setBackground(new java.awt.Color(76, 102, 99));
        homeBtn.setIcon(home);
        homeBtn.setBorder(null);

        ImageIcon ticket = new ImageIcon("res/drawable/icons/home-white-outline.png");
        Image scaledTicket = ticket.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        ticket = new ImageIcon(scaledTicket);
        ticketBtn.setBackground(new java.awt.Color(76, 102, 99));
        ticketBtn.setSize(50,50);
        ticketBtn.setIcon(ticket); // Change Directory
        ticketBtn.setBorder(null);

        ImageIcon user = new ImageIcon("res/drawable/icons/ticket-white-outline.png");
        Image scaledUser = user.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        user = new ImageIcon(scaledUser);
        userBtn.setBackground(new java.awt.Color(76, 102, 99));
        userBtn.setSize(50,50);
        userBtn.setIcon(user);
        userBtn.setBorder(null);

        ImageIcon exit = new ImageIcon("res/drawable/icons/user-white-outline.png");
        Image scaledExit = exit.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        exit = new ImageIcon(scaledExit);
        exitBtn.setBackground(new java.awt.Color(76, 102, 99));
        exitBtn.setSize(50,50);
        exitBtn.setIcon(exit); // Change Directory
        exitBtn.setBorder(null);

        // Sidebar buttons
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(userBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(homeBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(ticketBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(exitBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ticketBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(homeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(timerMinutesLabel)
                                                .addGap(29, 29, 29)
                                                .addComponent(timerSeconds)
                                                .addGap(281, 281, 281)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(hoursLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(hoursInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addComponent(durationLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(durationInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addComponent(dateLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(dateInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addComponent(parkingSpotLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(parkingSpotInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addComponent(vehicleLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(vehicleInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addComponent(parkingTypeLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                                                                .addComponent(parkingTypeInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addComponent(parkingAreaLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(parkingAreaInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(99, 99, 99))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                                .addGap(161, 161, 161)
                                                                .addComponent(timerHours))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                                .addGap(170, 170, 170)
                                                                .addComponent(endTimerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                                .addGap(413, 413, 413)
                                                                .addComponent(jLabel2)))
                                                .addContainerGap(414, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(37, 37, 37)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(parkingAreaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(parkingTypeLabel)
                                                                        .addComponent(parkingTypeInfo))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(vehicleLabel)
                                                                        .addComponent(vehicleInfo))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(parkingSpotLabel)
                                                                        .addComponent(parkingSpotInfo))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(dateLabel)
                                                                        .addComponent(dateInfo)
                                                                        .addComponent(timerSeconds)
                                                                        .addComponent(timerMinutesLabel)
                                                                        .addComponent(timerHours))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(durationLabel)
                                                                        .addComponent(durationInfo))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(hoursLabel)
                                                                        .addComponent(hoursInfo)))
                                                        .addComponent(parkingAreaInfo))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(endTimerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(80, 80, 80))))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);

    }

    // TODO: add the methods below to the controller.
    private void endTimerBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:\

    }

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {
        // get x and y coordinates values
        positionX = evt.getX();
        positionY = evt.getY();
    }

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {
        // set JFrame animation
        setLocation(evt.getXOnScreen()-positionX, evt.getYOnScreen()-positionY);
    }

    public static void main(String args[]) {

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


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimerView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel dateInfo;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel durationInfo;
    private javax.swing.JLabel durationLabel;
    private javax.swing.JButton endTimerBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel hoursInfo;
    private javax.swing.JLabel hoursLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel parcsLogo;
    private javax.swing.JLabel parkingAreaInfo;
    private javax.swing.JLabel parkingAreaLabel;
    private javax.swing.JLabel parkingSpotInfo;
    private javax.swing.JLabel parkingSpotLabel;
    private javax.swing.JLabel parkingTypeInfo;
    private javax.swing.JLabel parkingTypeLabel;
    private javax.swing.JButton ticketBtn;
    private javax.swing.JLabel ticketText;
    private javax.swing.JLabel timerHours;
    private javax.swing.JLabel timerMinutesLabel;
    private javax.swing.JLabel timerSeconds;
    private javax.swing.JButton userBtn;
    private javax.swing.JLabel vehicleInfo;
    private javax.swing.JLabel vehicleLabel;
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