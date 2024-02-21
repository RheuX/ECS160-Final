import javax.swing.*;
import java.awt.*;
/*
@author ChatGPT
Creates a panel. Called by FeatureBarSetup it creates the buttons for furniture options within the category indicated by the title parameter. 
*/
public class PanelCreation {
    public static JPanel createPanel(String title, DrawingTools drawingTools) {
        JPanel panel = new JPanel();

        if (title.equals("Structural Features")) {
            JPanel structuralPanel = new JPanel(new GridLayout(3, 1));
            JButton wallButton = new JButton("Wall");
            wallButton.addActionListener(e -> drawingTools.setDrawingMode(DrawingTools.DrawingMode.WALL));
            structuralPanel.add(wallButton);
            panel.add(structuralPanel, BorderLayout.CENTER);
        } else {
            panel.add(new JLabel(title));
        }

        return panel;
    }
}
