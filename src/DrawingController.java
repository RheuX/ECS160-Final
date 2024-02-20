import java.awt.*;
import java.awt.event.*;

import javax.swing.SwingUtilities;

public class DrawingController {
    private DrawingModel model;
    private DrawingView view;
    private Point lastPoint;

    public DrawingController(DrawingModel model, DrawingView view) {
        this.model = model;
        this.view = view;
        lastPoint = null;
        setupEventListeners();
    }

    private void setupEventListeners() {
        view.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastPoint = e.getPoint();
            }
        });

        view.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (lastPoint != null) {
                    model.drawLine(lastPoint, e.getPoint());
                    lastPoint = e.getPoint();
                    view.repaint();
                }
            }
        });
    }

    public void handleMousePressed(MouseEvent e) {
        System.out.println("Mouse pressed: " + e.getPoint());
        lastPoint = e.getPoint();
    }

    public void handleMouseDragged(MouseEvent e) {
        System.out.println("Mouse dragged: " + e.getPoint());
        if (lastPoint != null) {
            model.drawLine(lastPoint, e.getPoint());
            lastPoint = e.getPoint();
            SwingUtilities.invokeLater(() -> {
                view.repaint();
            });
        }
    }
}


