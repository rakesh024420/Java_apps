import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DigitalClock extends JFrame {

    JLabel timeLabel, dateLabel;

    public DigitalClock() {

        setTitle("Digital Clock");
        setSize(400, 200);
        setLayout(new GridLayout(2, 1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        timeLabel = new JLabel("", JLabel.CENTER);
        dateLabel = new JLabel("", JLabel.CENTER);

        timeLabel.setFont(new Font("Verdana", Font.BOLD, 40));
        timeLabel.setForeground(Color.BLUE);

        dateLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        add(timeLabel);
        add(dateLabel);

        // Timer updates every second
        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();

        updateTime();
        setVisible(true);
    }

    void updateTime() {
        Date now = new Date();

        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMM yyyy");

        timeLabel.setText(timeFormat.format(now));
        dateLabel.setText(dateFormat.format(now));
    }

    public static void main(String[] args) {
        new DigitalClock();
    }
}