import javax.swing.*;
import java.awt.*;

public class WallComponent extends JPanel {
    private static final Color WALL_COLOR = Color.LIGHT_GRAY;

    public WallComponent() {
        setPreferredSize(new Dimension(100, 50)); // Adjust size as needed
        setBackground(WALL_COLOR);
    }
}
