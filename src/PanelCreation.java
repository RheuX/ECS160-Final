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
        } else if (title.equals("Bathroom Features")) {
            JPanel structuralPanel = new JPanel(new GridLayout(3, 1));
            JButton sinkButton = new JButton("Sink");
            sinkButton.addActionListener(e -> drawingTools.setDrawingMode(DrawingTools.DrawingMode.SINK));
            structuralPanel.add(sinkButton);
            JButton toiletButton = new JButton("Toilet");
            toiletButton.addActionListener(e -> drawingTools.setDrawingMode(DrawingTools.DrawingMode.TOILET));
            structuralPanel.add(toiletButton);
            JButton showerButton = new JButton("Shower");
            showerButton.addActionListener(e -> drawingTools.setDrawingMode(DrawingTools.DrawingMode.SHOWER));
            structuralPanel.add(showerButton);

            panel.add(structuralPanel, BorderLayout.CENTER);
        } else if (title.equals("Kitchen Features")) {
            JPanel structuralPanel = new JPanel(new GridLayout(3, 1));
            JButton sinkButton = new JButton("Sink on Counter");
            JButton counterButton = new JButton("Counter");
            JButton washingMachineButton = new JButton("Refrigerator");
            structuralPanel.add(sinkButton);
            structuralPanel.add(counterButton);
            structuralPanel.add(washingMachineButton);

            panel.add(structuralPanel, BorderLayout.CENTER);
        } else if (title.equals("Living Room Features")) {
            JPanel structuralPanel = new JPanel(new GridLayout(3, 1));
            JButton couchButton = new JButton("Couch");
            JButton sofaButton = new JButton("Sofa");
            JButton tvButton = new JButton("TV");
            structuralPanel.add(couchButton);
            structuralPanel.add(sofaButton);
            structuralPanel.add(tvButton);

            panel.add(structuralPanel, BorderLayout.CENTER);
        } else if (title.equals("Dining Room Features")) {
            JPanel structuralPanel = new JPanel(new GridLayout(2, 1));
            JButton tableButton = new JButton("Table");
            JButton chairButton = new JButton("Chair");
            structuralPanel.add(tableButton);
            structuralPanel.add(chairButton);

            panel.add(structuralPanel, BorderLayout.CENTER);
        } else {
            panel.add(new JLabel(title));
        }
        return panel;
    }
}
