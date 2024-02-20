import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Simple Paint Application using Java Swing.
 * Allows users to draw, save, load, and clear drawings.
 *
 * @author ChatGPT
 */
public class App extends JFrame {
    private DrawingModel model;
    private DrawingView view;
    private DrawingController controller;

    /**
     * Constructor to initialize the application.
     */
    public App() {
        super("Simple Paint Application");
        model = new DrawingModel(800, 600);
        view = new DrawingView(model);
        controller = new DrawingController(model, view);
        initUI();
    }

    /**
     * Initializes the User Interface components of the application.
     */
    private void initUI() {
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
                g.drawImage(model.getCanvas(), 0, 0, null);
            }
        };

        gridPanel.setPreferredSize(new Dimension(800, 600));
        gridPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                controller.handleMousePressed(e);
            }
        });

        gridPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                controller.handleMouseDragged(e);
            }
        });

        mainPanel.add(gridPanel, BorderLayout.CENTER);
        add(mainPanel);
        setupMenuBar(gridPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setupMenuBar(JPanel gridPanel) {
        JMenuBar menuBar = MenuBarSetup.setupMenuBar(); // Call the method from MenuBarSetup.java
        setJMenuBar(menuBar); // Set the menuBar for the App frame
    }

    /**
     * Main method to run the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }
}
