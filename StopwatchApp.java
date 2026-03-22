import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StopwatchApp extends JFrame implements ActionListener {
    private JLabel timeLabel;
    private JButton startBtn, stopBtn, resetBtn, lapBtn;
    private JTextArea lapArea;
    private Timer timer;

    private int milliseconds = 0;
    private boolean running = false;

    public StopwatchApp() {
        setTitle("Stopwatch");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Time display
        timeLabel = new JLabel("00:00:000", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        add(timeLabel, BorderLayout.NORTH);

        // Buttons panel
        JPanel buttonPanel = new JPanel();

        startBtn = new JButton("Start");
        stopBtn = new JButton("Stop");
        resetBtn = new JButton("Reset");
        lapBtn = new JButton("Lap");

        startBtn.addActionListener(this);
        stopBtn.addActionListener(this);
        resetBtn.addActionListener(this);
        lapBtn.addActionListener(this);

        buttonPanel.add(startBtn);
        buttonPanel.add(stopBtn);
        buttonPanel.add(resetBtn);
        buttonPanel.add(lapBtn);

        add(buttonPanel, BorderLayout.CENTER);

        // Lap area
        lapArea = new JTextArea();
        lapArea.setEditable(false);
        add(new JScrollPane(lapArea), BorderLayout.SOUTH);

        // Timer (updates every 10 ms)
        timer = new Timer(10, e -> updateTime());

        setVisible(true);
    }

    private void updateTime() {
        milliseconds += 10;

        int seconds = (milliseconds / 1000) % 60;
        int minutes = (milliseconds / 60000);
        int ms = milliseconds % 1000;

        timeLabel.setText(String.format("%02d:%02d:%03d", minutes, seconds, ms));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startBtn) {
            if (!running) {
                timer.start();
                running = true;
            }
        } else if (e.getSource() == stopBtn) {
            timer.stop();
            running = false;
        } else if (e.getSource() == resetBtn) {
            timer.stop();
            running = false;
            milliseconds = 0;
            timeLabel.setText("00:00:000");
            lapArea.setText("");
        } else if (e.getSource() == lapBtn) {
                
            lapArea.append(timeLabel.getText() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StopwatchApp::new);
    }
}