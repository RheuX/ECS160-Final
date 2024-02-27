import javax.swing.*;
import java.awt.*;

public class PanelCreation {
    public static JPanel createPanel(String title, DrawingTools drawingTools) {
        JPanel panel = new JPanel();

        if (title.equals("Structural Features")) {
            JPanel structuralPanel = new JPanel(new GridLayout(3, 1));
            JButton wallButton = new JButton("Wall");
            wallButton.addActionListener(e -> drawingTools.setDrawingMode(DrawingTools.DrawingMode.WALL));
            structuralPanel.add(wallButton);
            JButton windowButton = new JButton("Window");
            windowButton.addActionListener(e -> drawingTools.setDrawingMode(DrawingTools.DrawingMode.WINDOW));
            structuralPanel.add(windowButton);
            JButton doorButton = new JButton("Door");
            doorButton.addActionListener(e -> drawingTools.setDrawingMode(DrawingTools.DrawingMode.DOOR));
            structuralPanel.add(doorButton);

            panel.add(structuralPanel, BorderLayout.CENTER);
        } else if (title.equals("Bedroom Features")) {
            JPanel furniturePanel = new JPanel(new GridLayout(3, 1));
            JButton bedButton = new JButton("Bed");
            bedButton.addActionListener(e -> drawingTools.setDrawingMode(DrawingTools.DrawingMode.BED));
            furniturePanel.add(bedButton);
            JButton deskButton = new JButton("Desk");
            deskButton.addActionListener(e -> drawingTools.setDrawingMode(DrawingTools.DrawingMode.DESK));
            furniturePanel.add(deskButton);
            JButton chairButton = new JButton("Chair");
            chairButton.addActionListener(e -> drawingTools.setDrawingMode(DrawingTools.DrawingMode.CHAIR));
            furniturePanel.add(chairButton);

            panel.add(furniturePanel, BorderLayout.CENTER);
        } else {
            panel.add(new JLabel(title));
        }
        return panel;
    }
}
