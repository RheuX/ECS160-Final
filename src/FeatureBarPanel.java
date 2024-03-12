import javax.swing.*;
import java.awt.*;

public class FeatureBarPanel extends JPanel {
    private static final int button_width = 80;
    private static final int button_height = 40;

    public FeatureBarPanel(JPanel canvas, ManageCanvas manageCanvas, CommandManager commandManager) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 600));

        // Drawing tools setup
        DrawingTools drawingTools = new DrawingTools(canvas, manageCanvas, commandManager);

        // Feature menu panel with borders
        JPanel featureMenuPanel = new JPanel(new BorderLayout());
        featureMenuPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Tools panel with icons
        JPanel toolsPanel = new JPanel(new GridLayout(3, 2));
        addButtonToPanel(toolsPanel, createDrawingButton("Mouse", "none.png", DrawingTools.DrawingMode.NONE, drawingTools));
        addButtonToPanel(toolsPanel, createDrawingButton("Delete", "delete.png", drawingTools));
        addButtonToPanel(toolsPanel, createDrawingButton("Resize", "resize.png", DrawingTools.DrawingMode.RESIZE, drawingTools));
        addButtonToPanel(toolsPanel, createDrawingButton("Rotate-Left", "rotate-left.png", DrawingTools.DrawingMode.ROTATE_LEFT, drawingTools));
        addButtonToPanel(toolsPanel, createDrawingButton("Rotate-Right", "rotate-right.png", DrawingTools.DrawingMode.ROTATE_RIGHT, drawingTools));
        addButtonToPanel(toolsPanel, createDrawingButton("Rotate-Flip", "rotate-180.png", DrawingTools.DrawingMode.ROTATE_FLIP, drawingTools));
        featureMenuPanel.add(toolsPanel, BorderLayout.NORTH);

        // Search bar panel
        JPanel searchBarPanel = new JPanel(new BorderLayout());
        searchBarPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JLabel searchTitleLabel = new JLabel("Search:");
        searchBarPanel.add(searchTitleLabel, BorderLayout.WEST);
        JTextField searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(150, 25));
        searchBar.setMaximumSize(new Dimension(150, 25));
        searchBar.setText("Search furniture...");
        searchBarPanel.add(searchBar, BorderLayout.CENTER);
        featureMenuPanel.add(searchBarPanel, BorderLayout.SOUTH);

        // Feature categories tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        tabbedPane.addTab("Dining Room", PanelCreation.createPanel("Dining Room Features", drawingTools));
        tabbedPane.addTab("Living Room", PanelCreation.createPanel("Living Room Features", drawingTools));
        tabbedPane.addTab("Bathroom", PanelCreation.createPanel("Bathroom Features", drawingTools));
        tabbedPane.addTab("Kitchen", PanelCreation.createPanel("Kitchen Features", drawingTools));
        tabbedPane.addTab("Bedroom", PanelCreation.createPanel("Bedroom Features", drawingTools));
        tabbedPane.addTab("Structural", PanelCreation.createPanel("Structural Features", drawingTools));
        featureMenuPanel.add(tabbedPane, BorderLayout.CENTER);

        add(featureMenuPanel, BorderLayout.CENTER);
    }

    private JButton createDrawingButton(String buttonText, String iconFileName, DrawingTools.DrawingMode mode, DrawingTools drawingTools) {
        String iconDirectory = "../assets";
        String iconPath = iconDirectory + "/" + iconFileName;
        ImageIcon icon = new ImageIcon(iconPath);
        JButton button = new JButton(buttonText, icon);
        button.setPreferredSize(new Dimension(button_width, button_height));
        button.addActionListener(e -> drawingTools.setDrawingMode(mode));
        return button;
    }

    private JButton createDrawingButton(String buttonText, String iconFileName, DrawingTools drawingTools) {
        String iconDirectory = "../assets";
        String iconPath = iconDirectory + "/" + iconFileName;
        ImageIcon icon = new ImageIcon(iconPath);
        JButton button = new JButton(buttonText, icon);
        button.setPreferredSize(new Dimension(button_width, button_height));
        button.addActionListener(e -> drawingTools.deleteSelectedObjects());
        return button;
    }

    private void addButtonToPanel(Container container, JButton button) {
        container.add(button);
    }
}
