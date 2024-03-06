import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MainCanvasPanel extends JPanel {
    private BufferedImage canvas;
    private int originalGridSize = 30; // Size of each grid cell
    private double zoomFactor = 1.0;
    private int scaledGridSize = (int) (originalGridSize * zoomFactor);
    private double ZOOM_INCREMENT = 0.1;

    public MainCanvasPanel() {
        canvas = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        setPreferredSize(new Dimension(800, 600));
        addMouseWheelListener(new ZoomMouseWheelListener());
    }

    private class ZoomMouseWheelListener implements MouseWheelListener {

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            int notches = e.getWheelRotation();
            if (notches < 0) {
                zoom(ZOOM_INCREMENT);
            } else {
                zoom(-ZOOM_INCREMENT);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        g2d.setColor(Color.BLACK);

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        // Draw vertical grid lines
        for (int x = 0; x <= width; x += scaledGridSize) {
            g2d.drawLine(x, 0, x, height);
        }

        // Draw horizontal grid lines
        for (int y = 0; y <= height; y += scaledGridSize) {
            g2d.drawLine(0, y, width, y);
        }

        g2d.drawImage(DrawingTools.drawAllFurniture(this), 0, 0, null);

        g2d.dispose(); 
    }

    public void zoom(double zoomDelta) {
        zoomFactor += zoomDelta;
        adjustGridSize();
    }

    private void adjustGridSize() {
        int originalGridSize = 30;
        int scaledGridSize = (int) (originalGridSize * zoomFactor);
        int gridSize = Math.min(scaledGridSize, getWidth() / 2); // Limit to half of the panel size
        setOriginalGridSize(gridSize);
        repaint();
    }

    private void setOriginalGridSize(int gridSize) {
        originalGridSize = gridSize;
    }

    public int getOriginalGridSize() {
        return originalGridSize;
    }

    public double getZoomFactor() {
        return zoomFactor;
    }

    public int getScaledGridSize() {
        return scaledGridSize;
    }

    public void clearCanvas() {
        Graphics2D g2d = canvas.createGraphics();
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g2d.setComposite(AlphaComposite.SrcOver);
        g2d.dispose();
        repaint();
    }
}
