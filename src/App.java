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
        JToolBar toolbar = setupFeatureMenu();
        mainPanel.add(toolbar, BorderLayout.WEST);
        
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(canvas, 0, 0, null);
            }
        };

        panel.setPreferredSize(new Dimension(800, 600));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastPoint = e.getPoint();
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                drawLine(lastPoint, e.getPoint());
                lastPoint = e.getPoint();
                repaint();
            }
        });

        mainPanel.add(panel, BorderLayout.CENTER);
        add(mainPanel);
        setupMenuBar();
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
     * Sets up the menu bar with File, Edit, and Help menus.
     */
    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setMnemonic(KeyEvent.VK_S);
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveItem.addActionListener(e -> saveImage());
        fileMenu.add(saveItem);

        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.setMnemonic(KeyEvent.VK_L);
        loadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
        loadItem.addActionListener(e -> loadImage());
        fileMenu.add(loadItem);

        fileMenu.add(new JSeparator()); // Separator

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");

        JMenuItem undoItem = new JMenuItem("Undo");
        undoItem.setMnemonic(KeyEvent.VK_Z);
        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        editMenu.add(undoItem);

        JMenuItem redoItem = new JMenuItem("Redo");
        redoItem.setMnemonic(KeyEvent.VK_X);
        redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        editMenu.add(redoItem);

        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.setMnemonic(KeyEvent.VK_C);
        clearItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        editMenu.add(clearItem);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> showAbout());
        helpMenu.add(aboutItem);

        // Canvas Menu
        JMenu canvasMenu = new JMenu("Canvas");

        JMenuItem resizeItem = new JMenuItem("Resize");
        canvasMenu.add(resizeItem);

        JMenuItem zoomInItem = new JMenuItem("Zoom In");
        canvasMenu.add(zoomInItem);

        JMenuItem zoomOutItem = new JMenuItem("Zoom Out");
        canvasMenu.add(zoomOutItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(canvasMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    private JToolBar setupFeatureMenu() {
        JToolBar toolbar = new JToolBar(JToolBar.VERTICAL);
        toolbar.setFloatable(false);
    
        // Panel for the label (centered)
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel featureMenuLabel = new JLabel("Feature Menu");
        labelPanel.add(featureMenuLabel);
        toolbar.add(labelPanel);
    
        // Search bar
        JTextField searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(150, 25));
        searchBar.setMaximumSize(new Dimension(150, 25));
        toolbar.add(searchBar);
    
        // Tabbed pane for feature categories
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(150, 550)); // Adjust the size as needed

        // Add tabs
        tabbedPane.addTab("Dining Room", createPanel("Dining Room Features"));
        tabbedPane.addTab("Living Room", createPanel("Living Room Features"));
        tabbedPane.addTab("Bathroom", createPanel("Bathroom Features"));
        tabbedPane.addTab("Kitchen", createPanel("Kitchen Features"));
        tabbedPane.addTab("Bedroom", createPanel("Bedroom Features"));
        tabbedPane.addTab("Structural", createPanel("Structural Features"));
        tabbedPane.addTab("General", createPanel("General Features"));
    
        toolbar.add(tabbedPane, BorderLayout.NORTH); 

        return toolbar;
    }

    /**
     * Creates a simple JPanel with a label for the specified category.
     */
    private JPanel createPanel(String category) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(category));
        return panel;
    }
    
    /**
     * Main method to run the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().setVisible(true));
    }
}
