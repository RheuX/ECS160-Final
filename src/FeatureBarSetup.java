import javax.swing.*;
import java.awt.*;

public class FeatureBarSetup {
    public static JToolBar setupFeatureMenu() {
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
        tabbedPane.addTab("Dining Room", PanelCreation.createPanel("Dining Room Features"));
        tabbedPane.addTab("Living Room", PanelCreation.createPanel("Living Room Features"));
        tabbedPane.addTab("Bathroom", PanelCreation.createPanel("Bathroom Features"));
        tabbedPane.addTab("Kitchen", PanelCreation.createPanel("Kitchen Features"));
        tabbedPane.addTab("Bedroom", PanelCreation.createPanel("Bedroom Features"));
        tabbedPane.addTab("Structural", PanelCreation.createPanel("Structural Features"));
        tabbedPane.addTab("General", PanelCreation.createPanel("General Features"));
    
        toolbar.add(tabbedPane, BorderLayout.NORTH); 
    
        return toolbar;
    }
}

