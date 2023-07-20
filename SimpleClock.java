//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SimpleClock extends JFrame implements Runnable{
    //define a variable that controls the thread 1
    static boolean clockSet = false;

    Calendar calendar;
    SimpleDateFormat timeFormat;
    SimpleDateFormat dayFormat;
    SimpleDateFormat dateFormat;

    JLabel timeLabel;
    JLabel dayLabel;
    JLabel dateLabel;
    String time;
    String day;
    String date;

    SimpleClock() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Digital Clock");
        this.setLayout(new FlowLayout());
        this.setSize(350, 220);
        this.setResizable(false);

        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        dayFormat = new SimpleDateFormat("EEEE");
        dateFormat = new SimpleDateFormat("dd MMMMM, yyyy");
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
        timeLabel.setBackground(Color.BLACK);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setOpaque(true);
        dayLabel = new JLabel();
        dayLabel.setFont(new Font("Ink Free", Font.BOLD, 34));

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Ink Free", Font.BOLD, 30));


        this.add(timeLabel);
        this.add(dayLabel);
        this.add(dateLabel);
        this.setVisible(true);

//        setTimer();
    }

    public void setTimer() {
        while (true) {
            time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);

            day = dayFormat.format(Calendar.getInstance().getTime());
            dayLabel.setText(day);

            date = dateFormat.format(Calendar.getInstance().getTime());
            dateLabel.setText(date);

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SimpleClock sc = new SimpleClock();
        Thread thread = new Thread(sc);
        thread.start();
    }

    @Override
    public void run() {
        while(!clockSet) {
            time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);

            day = dayFormat.format(Calendar.getInstance().getTime());
            dayLabel.setText(day);

            date = dateFormat.format(Calendar.getInstance().getTime());
            dateLabel.setText(date);

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }
    /*
     * This is the syntax for creating an instance of an anonymous class that is a thread
     * This thread will force the clock to keep updating periodically.
     *
     * From my current understanding, this makes your code concise, allowing one to declare and
     * instantiate a class at the same time (declaring t1 to be a Thread and creating a new Thread).
     * USAGE: only need a local class once!
     */
    Thread t1 = new Thread(){
        @Override
        public void run() {
            clockSet = true;
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    };
}
