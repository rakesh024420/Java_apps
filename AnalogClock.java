import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class AnalogClock extends JPanel {

    public AnalogClock() {
        Timer timer = new Timer(1000, e -> repaint());
        timer.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        int radius = Math.min(width, height) / 2 - 40;
        int centerX = width / 2;
        int centerY = height / 2;

        Graphics2D g2 = (Graphics2D) g;

        // Draw clock circle
        g2.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

        // Draw numbers (1–12)
        for (int i = 1; i <= 12; i++) {
            double angle = Math.toRadians(i * 30);
            int x = (int) (centerX + (radius - 20) * Math.sin(angle));
            int y = (int) (centerY - (radius - 20) * Math.cos(angle));

            String num = String.valueOf(i);
            FontMetrics fm = g2.getFontMetrics();

            int textWidth = fm.stringWidth(num);
            int textHeight = fm.getAscent();

            g2.drawString(num, x - textWidth / 2, y + textHeight / 2);
        }

        // Get current time
        Calendar cal = Calendar.getInstance();
        int sec = cal.get(Calendar.SECOND);
        int min = cal.get(Calendar.MINUTE);
        int hr = cal.get(Calendar.HOUR);

        // Calculate angles
        double secAngle = Math.toRadians(sec * 6);
        double minAngle = Math.toRadians(min * 6 + sec * 0.1);
        double hrAngle = Math.toRadians((hr % 12) * 30 + min * 0.5);

        // Draw hands
        drawHand(g2, centerX, centerY, secAngle, radius - 20, Color.RED);
        drawHand(g2, centerX, centerY, minAngle, radius - 40, Color.BLACK);
        drawHand(g2, centerX, centerY, hrAngle, radius - 60, Color.BLUE);
    }

    void drawHand(Graphics2D g, int x, int y, double angle, int length, Color color) {
        int xEnd = (int) (x + length * Math.sin(angle));
        int yEnd = (int) (y - length * Math.cos(angle));

        g.setColor(color);
        g.setStroke(new BasicStroke(2));
        g.drawLine(x, y, xEnd, yEnd);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Analog Clock with Numbers");
        frame.add(new AnalogClock());
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}