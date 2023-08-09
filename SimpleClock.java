//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;


public class SimpleClock extends JFrame implements Runnable{
    //define a variable that controls the threads
    static boolean clockSet = false;
    boolean militaryTime = false;
    boolean timeZoneGMT = false;

    SimpleDateFormat timeFormat;
    SimpleDateFormat militaryTimeFormat;
    SimpleDateFormat dayFormat;
    SimpleDateFormat dateFormat;

    JLabel timeLabel;
    JLabel dayLabel;
    JLabel dateLabel;
    JButton hourFormatButton;
    JButton timezoneButton;
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
        militaryTimeFormat = new SimpleDateFormat("HH:mm:ss");
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

        hourFormatButton = new JButton("12 / 24 hr format");
        hourFormatButton.addActionListener(new HourFormat());

        timezoneButton = new JButton("Local / GMT");
        timezoneButton.addActionListener(new TimeZoneFormat());

        this.add(timeLabel);
        this.add(dayLabel);
        this.add(dateLabel);
        this.add(hourFormatButton);
        this.add(timezoneButton);
        this.setSize(390, 230);
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

    class HourFormat implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            militaryTime = !militaryTime;
        }
    }
    class TimeZoneFormat implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            timeZoneGMT = !timeZoneGMT;
            if(timeZoneGMT){
                timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                militaryTimeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            }
            else{
                timeFormat.setTimeZone(TimeZone.getDefault());
                militaryTimeFormat.setTimeZone(TimeZone.getDefault());
            }
        }
    }

    public static void main(String[] args) {
        SimpleClock sc = new SimpleClock();
        Thread thread = new Thread(sc);
        thread.start();
    }

    private String getCurrentTime(){
        Date currentDate = Calendar.getInstance().getTime();
        if(!militaryTime) {
            return timeFormat.format(currentDate);
        }
        else{
            return militaryTimeFormat.format(currentDate);
        }
    }

    @Override
    public void run() {
        while(!clockSet) {
            time = getCurrentTime();
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
