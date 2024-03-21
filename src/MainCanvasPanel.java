import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainCanvasPanel extends JPanel {
    private int originalGridSize = 30; // Size of each grid cell
    private double zoomFactor = 1.0;
    private int scaledGridSize = (int) (originalGridSize * zoomFactor);

    public MainCanvasPanel() {
        setPreferredSize(new Dimension(800, 600));
        addComponentListener(new ResizeComponentListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setColor(Color.BLACK);

        int width = getWidth();
        int height = getHeight();

        // Draw vertical grid lines
        for (int x = 0; x <= width; x += scaledGridSize) {
            g2d.drawLine(x, 0, x, height);
        }

        // Draw horizontal grid lines
        for (int y = 0; y <= height; y += scaledGridSize) {
            g2d.drawLine(0, y, width, y);
        }

        g2d.drawImage(DrawingTools.drawAllFurnitureAndStructures(this), 0, 0, null);

        g2d.dispose();
    }

    private class ResizeComponentListener extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            adjustGridSize();
        }
    }

    private void adjustGridSize() {
        int gridSize = Math.min(originalGridSize, Math.min(getWidth(), getHeight()));
        scaledGridSize = gridSize;
        repaint();
    }
}
