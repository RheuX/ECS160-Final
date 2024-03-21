import javax.swing.*;
import java.awt.event.*;

/*
@author ChatGPT
Creates a JMenuBar which will serve as the menu for file an canvas related actions. 
*/
public class MenuBar extends JMenuBar {
    private final MainCanvasPanel mainCanvasPanel;
    private final CommandManager commandManager;
    private final ManageCanvas manageCanvas;

    public MenuBar(MainCanvasPanel mainCanvasPanel, CommandManager commandManager, ManageCanvas manageCanvas) {
        this.mainCanvasPanel = mainCanvasPanel;
        this.commandManager = commandManager;
        this.manageCanvas = manageCanvas;
        add(createMenuBar());
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setMnemonic(KeyEvent.VK_S);
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveItem.addActionListener(e -> saveCanvasToFile()); // Call save method
        fileMenu.add(saveItem);

        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.setMnemonic(KeyEvent.VK_L);
        loadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
        loadItem.addActionListener(e -> loadCanvasFromFile()); // Call load method
        fileMenu.add(loadItem);

        fileMenu.add(new JSeparator()); // Separator

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");

        JMenuItem undoItem = new JMenuItem("Undo");
        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        undoItem.addActionListener(e -> undoAction()); // Add ActionListener for Undo
        editMenu.add(undoItem);

        JMenuItem redoItem = new JMenuItem("Redo");
        redoItem.setMnemonic(KeyEvent.VK_X);
        redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        redoItem.addActionListener(e -> redoAction()); // Add ActionListener for Undo
        editMenu.add(redoItem);

        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.setMnemonic(KeyEvent.VK_C);
        clearItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        editMenu.add(clearItem);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        //aboutItem.addActionListener(e -> showAbout());
        helpMenu.add(aboutItem);

        // Canvas Menu
        JMenu canvasMenu = new JMenu("Canvas");

        JMenuItem resizeItem = new JMenuItem("Resize");
        canvasMenu.add(resizeItem);

        JMenuItem zoomInItem = new JMenuItem("Zoom In");
        //zoomInItem.addActionListener(e -> mainCanvasPanel.zoom(0.25));
        canvasMenu.add(zoomInItem);

        JMenuItem zoomOutItem = new JMenuItem("Zoom Out");
        //zoomOutItem.addActionListener(e -> mainCanvasPanel.zoom(-0.25));
        canvasMenu.add(zoomOutItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(canvasMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    private void undoAction() {
        if (commandManager.canUndo()) {
            commandManager.undo(); // Call undo method from CommandManager
            mainCanvasPanel.repaint(); // Repaint canvas after undo
        } else {
            JOptionPane.showMessageDialog(null, "Nothing to undo", "Undo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void redoAction() {
        if (commandManager.canRedo()) {
            commandManager.redo();
            mainCanvasPanel.repaint();
        } else {
            JOptionPane.showMessageDialog(null, "Nothing to redo", "Redo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Method to save canvas data to a file
    private void saveCanvasToFile() {
        manageCanvas.saveCanvas("canvas_data.ser"); // Adjust the file name as needed
    }

    // Method to load canvas data from a file
    private void loadCanvasFromFile() {
        manageCanvas.loadCanvas("canvas_data.ser"); // Adjust the file name as needed
        mainCanvasPanel.repaint(); // Repaint canvas after loading
    }
    
}
