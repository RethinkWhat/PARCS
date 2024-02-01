//imported packages that help display items in graphical form
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class Timer extends JFrame{
        //a class that will show the client on how much time left before his or her reservation will end
        //integer variables that represent the hour, minute and second left for the said session.
        private int hour;
        private int minute;
        private int second;

        //Prototype GUI that displays timer, notifying the users
        //when their reservation will be invalid
        private static final int width = 400;
        private static final int height = 400;
        private static final int radius = 200;
        private static final int arcStartAngle = 270;

        private int arcExtent;
        double init;
        double current;
        private javax.swing.Timer t;

        public Timer(int hour, int minute, int second) {
            setTitle("Duration of reservation");
            setSize(width, height);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            arcExtent = 360;  // Start with a full circle
            this.hour=hour;
            this.minute=minute;
            this.second=second;
            init = hour*3600 + minute*60 + second;
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
        private void updateTime() {
            if (hour == 0 && minute == 0 && second == 0) {
                // Stop the timer when the time reaches 0:00
                t.stop();
                //setVisible(false);
            }
            else if(minute == 0 && second ==0){
                hour--;
                minute = 59;
                second = 59;
            }
            else if (second == 0) {
                minute--;
                second = 59;
            }
            else {
                second--;
            }
        }

        //method that apply the prototype GUI via JFrame
        public void paint(Graphics g) {
            super.paint(g);
            drawArc(g);
            drawStopwatch(g);
        }

        //method that will graphically display the remaining time.
        private void drawStopwatch(Graphics g) {
            g.setColor(Color.RED);
            g.setFont(new Font("Times New Roman", Font.PLAIN, 24));
            String timeString = String.format("%02d:%02d:%02d", hour, minute, second);
            g.drawString(timeString, width / 2 - 30, height / 2 + 5);
        }

        private void drawArc(Graphics g) {
            if(arcExtent>=0) {
                g.setColor(Color.green);
                g.fillArc((width - 2 * radius) / 2, (height - 2 * radius) / 2, 2 * radius, 2 * radius, arcStartAngle, arcExtent);
            }
            current--;
            arcExtent= (int)((current/init) * 360.0);
        }
        public static void main(String[] args) throws InterruptedException{
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Timer time=new Timer(0,30,0);
                    time.setVisible(true);
                }
            });
        }
    }