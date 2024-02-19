import javax.swing.*;
import java.awt.*;
/*
@author ChatGPT
Creates a panel. Called by FeatureBarSetup it creates the buttons for furniture options within the category indicated by the title parameter. 
*/
public class PanelCreation {
    public static JPanel createPanel(String title) {
        JPanel panel = new JPanel();

        if (title.equals("Structural Features")) {
            JPanel structuralPanel = new JPanel(new GridLayout(3, 1));
            JButton wallButton = new JButton("Wall");
            JButton windowButton = new JButton("Window");
            JButton doorButton = new JButton("Door");
            structuralPanel.add(wallButton);
            structuralPanel.add(windowButton);
            structuralPanel.add(doorButton);
    
            panel.add(structuralPanel, BorderLayout.CENTER);
        } else if (title.equals("Bedroom Features")) {
            JPanel structuralPanel = new JPanel(new GridLayout(3, 1));
            JButton bedButton = new JButton("Bed");
            JButton deskButton = new JButton("Desk");
            JButton chairButton = new JButton("Chair");
            structuralPanel.add(bedButton);
            structuralPanel.add(deskButton);
            structuralPanel.add(chairButton);
    
            panel.add(structuralPanel, BorderLayout.CENTER);
        } else if (title.equals("Bathroom Features")) {
            JPanel structuralPanel = new JPanel(new GridLayout(3, 1));
            JButton sinkButton = new JButton("Sink");
            JButton toiletButton = new JButton("Toilet");
            JButton showerButton = new JButton("Shower");
            structuralPanel.add(sinkButton);
            structuralPanel.add(toiletButton);
            structuralPanel.add(showerButton);
    
            panel.add(structuralPanel, BorderLayout.CENTER);
        } else if (title.equals("Kitchen Features")) {
            JPanel structuralPanel = new JPanel(new GridLayout(3, 1));
            JButton sinkButton = new JButton("Sink");
            JButton counterButton = new JButton("Counter");
            JButton washingMachineButton = new JButton("Washing Machine");
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
