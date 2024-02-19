import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Simple Paint Application using Java Swing.
 * Allows users to draw, save, load, and clear drawings.
 *
 * @author ChatGPT
 */
public class App extends JFrame {

    private BufferedImage canvas;
    private Point lastPoint;

    /**
     * Constructor to initialize the application.
     */
    public App() {
        super("Simple Paint Application");
        initUI();
        initDrawing();
    }

    /**
     * Initializes the User Interface components of the application.
     */
    private void initUI() {
        canvas = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        clearCanvas();

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Setting up feature menu toolbar
        JToolBar toolbar = FeatureBarSetup.setupFeatureMenu();
        mainPanel.add(toolbar, BorderLayout.WEST);

        // Setting up the grid layout
        JPanel gridPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw grid lines with a white background
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
    
                g.setColor(Color.BLACK);
    
                int width = getWidth();
                int height = getHeight();
                int gridSize = 30;
    
                // Draw vertical grid lines
                for (int x = 0; x <= width; x += gridSize) {
                    g.drawLine(x, 0, x, height);
                }
    
                // Draw horizontal grid lines
                for (int y = 0; y <= height; y += gridSize) {
                    g.drawLine(0, y, width, y);
                }

                // Draw the existing canvas on top of the grid
                g.drawImage(canvas, 0, 0, null);
            }
        };

        gridPanel.setPreferredSize(new Dimension(800, 600));
        gridPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastPoint = e.getPoint();
            }
        });

        gridPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                drawLine(lastPoint, e.getPoint());
                lastPoint = e.getPoint();
                repaint();
            }
        });

        mainPanel.add(gridPanel, BorderLayout.CENTER);
        add(mainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setupMenuBar(JPanel gridPanel) {
        JMenuBar menuBar = MenuBarSetup.setupMenuBar(); // Call the method from MenuBarSetup.java
        setJMenuBar(menuBar); // Set the menuBar for the App frame
    }

    /**
     * Initializes drawing settings for the canvas.
     */
    private void initDrawing() {
        Graphics2D g2d = canvas.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
    }

    /**
     * Draws a line between two points.
     *
     * @param start The starting point of the line.
     * @param end The ending point of the line.
     */
    private void drawLine(Point start, Point end) {
        Graphics2D g2d = canvas.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.drawLine(start.x, start.y, end.x, end.y);
        g2d.dispose();
    }

    /**
     * Clears the canvas.
     */
    private void clearCanvas() {
        Graphics2D g2d = canvas.createGraphics();
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g2d.setComposite(AlphaComposite.SrcOver);
        g2d.dispose();
        repaint();
    }

    /**
     * Saves the current drawing to a file.
     */
    private void saveImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Image");
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                ImageIO.write(canvas, "PNG", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Loads an image from a file into the canvas.
     */
    private void loadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open Image");
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                canvas = ImageIO.read(file);
                repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Shows an About dialog with information about the application.
     */
    private void showAbout() {
        JOptionPane.showMessageDialog(this, "Simple Paint Application\nVersion 1.0\nCreated by ChatGPT", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Main method to run the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setupMenuBar(null); // Call setupMenuBar to set up the menuBar
            app.setVisible(true);
        });
    }
}
