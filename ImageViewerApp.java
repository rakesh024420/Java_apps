import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class ImageViewerApp extends JFrame implements ActionListener {

    JLabel imageLabel;
    JButton loadBtn, nextBtn, prevBtn;

    ArrayList<File> imageList = new ArrayList<>();
    int currentIndex = -1;

    public ImageViewerApp() {

        setTitle("Image Viewer");
        setSize(600, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Image display
        imageLabel = new JLabel("No Image Loaded", JLabel.CENTER);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // Buttons
        loadBtn = new JButton("Load Images");
        nextBtn = new JButton("Next");
        prevBtn = new JButton("Previous");

        loadBtn.addActionListener(this);
        nextBtn.addActionListener(this);
        prevBtn.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(prevBtn);
        panel.add(loadBtn);
        panel.add(nextBtn);

        add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // Load images
        if (e.getSource() == loadBtn) {
            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(true);

            int result = chooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                imageList.clear();
                File[] files = chooser.getSelectedFiles();

                for (File file : files) {
                    imageList.add(file);
                }

                if (!imageList.isEmpty()) {
                    currentIndex = 0;
                    showImage();
                }
            }
        }

        // Next image
        if (e.getSource() == nextBtn) {
            if (currentIndex < imageList.size() - 1) {
                currentIndex++;
                showImage();
            }
        }

        // Previous image
        if (e.getSource() == prevBtn) {
            if (currentIndex > 0) {
                currentIndex--;
                showImage();
            }
        }
    }

    void showImage() {
        File file = imageList.get(currentIndex);
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());

        // Resize image to fit label
        Image img = icon.getImage().getScaledInstance(
                imageLabel.getWidth(),
                imageLabel.getHeight(),
                Image.SCALE_SMOOTH
        );

        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setText(file.getName());
    }

    public static void main(String[] args) {
        new ImageViewerApp();
    }
}