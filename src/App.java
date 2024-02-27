import javax.swing.*;
import java.awt.BorderLayout;


public class App extends JFrame {
    public App() {
        super("Simple Floor Planning Application");
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        MainCanvasPanel mainCanvasPanel = new MainCanvasPanel();
        FeatureBarPanel featureBarPanel = new FeatureBarPanel(mainCanvasPanel);
        MenuBar menuBar = new MenuBar(mainCanvasPanel);

        add(mainCanvasPanel, BorderLayout.CENTER);
        add(featureBarPanel, BorderLayout.WEST);
        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }
}
