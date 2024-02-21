import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class MainCanvasPanel extends JPanel {
    private BufferedImage canvas;
    private final int gridSize = 30; // Size of each grid cell

    public MainCanvasPanel() {
        canvas = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        clearCanvas();

        setPreferredSize(new Dimension(800, 600));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Handle mouse press event
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Handle mouse drag event
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw grid lines with a white background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);

        int width = getWidth();
        int height = getHeight();
        int gridSize = 30;

        // Draw vertical grid lines
        for (int x = 0; x <= width; x += gridSize) {
            g.drawLine(x, 0, x, height);
        }

        // Draw horizontal grid lines
        for (int y = 0; y <= height; y += gridSize) {
            g.drawLine(0, y, width, y);
        }
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
