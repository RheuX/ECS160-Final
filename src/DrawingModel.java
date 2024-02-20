import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import java.awt.*;

public class DrawingModel {
    private BufferedImage canvas;
    private DrawingView view;

    public DrawingModel(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    public BufferedImage getCanvas() {
        return canvas;
    }

    public void setView(DrawingView view) {
        this.view = view;
    }

    public void drawLine(Point start, Point end) {
        Graphics2D g2d = canvas.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.drawLine(start.x, start.y, end.x, end.y);
        g2d.dispose();
        notifyView();
    }

    public void clearCanvas() {
        Graphics2D g2d = canvas.createGraphics();
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g2d.setComposite(AlphaComposite.SrcOver);
        g2d.dispose();
        notifyView();
    }

    private void notifyView() {
        if (view != null) {
            System.out.println("Notifying view to repaint...");
            SwingUtilities.invokeLater(() -> {
                view.repaint();
            });
        }
    }
}
