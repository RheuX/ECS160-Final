import javax.swing.*;
import java.awt.*;

public class FeatureBarPanel extends JPanel {
    public FeatureBarPanel(JPanel canvas) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 600));
        ImageIcon image;

        // Drawing tools setup
        DrawingTools drawingTools = new DrawingTools(canvas);

        // Feature menu panel with borders
        JPanel featureMenuPanel = new JPanel(new BorderLayout());
        featureMenuPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Tools panel with icons
        JPanel toolsPanel = new JPanel(new GridLayout(2, 2));
        addButtonWithIcon(toolsPanel, "Mouse", "cursor.png", 80, 80); // Set preferred size for buttons
        addButtonWithIcon(toolsPanel, "Rotate-Left", "rotate-left.png", 80, 80);
        addButtonWithIcon(toolsPanel, "Rotate-Right", "rotate-right.png", 80, 80);
        addButtonWithIcon(toolsPanel, "Rotate-Flip", "rotate-180.png", 80, 80);
        featureMenuPanel.add(toolsPanel, BorderLayout.NORTH);

        // Search bar panel
        JPanel searchBarPanel = new JPanel(new BorderLayout());
        searchBarPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Title for search bar
        JLabel searchTitleLabel = new JLabel("Search:");
        searchBarPanel.add(searchTitleLabel, BorderLayout.WEST);

        // Search text field with placeholder text
        JTextField searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(150, 25));
        searchBar.setMaximumSize(new Dimension(150, 25)); // Set maximum size for search bar
        searchBar.setText("Search furniture...");
        searchBarPanel.add(searchBar, BorderLayout.CENTER);

        featureMenuPanel.add(searchBarPanel, BorderLayout.SOUTH);

        // Feature categories tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Add tabs
        tabbedPane.addTab("Dining Room", PanelCreation.createPanel("Dining Room Features", drawingTools));
        tabbedPane.addTab("Living Room", PanelCreation.createPanel("Living Room Features", drawingTools));
        tabbedPane.addTab("Bathroom", PanelCreation.createPanel("Bathroom Features", drawingTools));
        tabbedPane.addTab("Kitchen", PanelCreation.createPanel("Kitchen Features", drawingTools));
        tabbedPane.addTab("Bedroom", PanelCreation.createPanel("Bedroom Features", drawingTools));
        tabbedPane.addTab("Structural", PanelCreation.createPanel("Structural Features", drawingTools));

        featureMenuPanel.add(tabbedPane, BorderLayout.CENTER);

        add(featureMenuPanel, BorderLayout.CENTER);
    }

    private static void addButtonWithIcon(Container container, String buttonText, String iconFileName, int width, int height) {
        String iconDirectory = "../assets"; // Make it static
        String iconPath = iconDirectory + "/" + iconFileName; // Concatenate directory path and file name
        ImageIcon icon = new ImageIcon(iconPath);
        JButton button = new JButton(buttonText, icon);
        button.setPreferredSize(new Dimension(width, height)); // Set preferred size for button
        container.add(button); // Add button to the specified container
    }

}
