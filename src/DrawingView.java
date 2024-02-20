import javax.swing.*;
import java.awt.*;

public class DrawingView extends JPanel {
    private DrawingModel model;

    public DrawingView(DrawingModel model) {
        this.model = model;
        model.setView(this); // Register the view with the model
        setPreferredSize(new Dimension(model.getCanvas().getWidth(), model.getCanvas().getHeight()));
    }

    //Not getting called right now
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Repainting view...");
        g.drawImage(model.getCanvas(), 0, 0, null);
    }
}
