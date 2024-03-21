import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import java.io.*;

public class ManageCanvas {
    private List<StructureObject> structures;
    private List<FurnitureObject> furniture;

    public ManageCanvas() {
        structures = new ArrayList<>();
        furniture = new ArrayList<>();
    }

    public void addStructure(StructureObject structure) {
        structures.add(structure);
        printStructureList(); // Print structure list after adding a structure
    }

    public void addFurniture(FurnitureObject piece) {
        furniture.add(piece);
        printFurnitureList(); // Print furniture list after adding a furniture piece
    }

    // Method to delete a StructureObject from the canvas
    public void deleteStructure(StructureObject structure) {
        structures.remove(structure);
    }

    // Method to delete a FurnitureObject from the canvas
    public void deleteFurniture(FurnitureObject piece) {
        furniture.remove(piece);
    }

    public void clearCanvas() {
        // Clear the list of structures
        structures.clear();
        
        // Clear the list of furniture
        furniture.clear();
    }
    

    // Method to get all StructureObjects on the canvas
    public List<StructureObject> getAllStructures() {
        return new ArrayList<>(structures);
    }

    // Method to get all FurnitureObjects on the canvas
    public List<FurnitureObject> getAllFurniture() {
        return new ArrayList<>(furniture);
    }

    private void printStructureList() {
        System.out.println("Structures on canvas:");
        for (int i = 0; i < structures.size(); i++) {
            System.out.println((i + 1) + ". " + structures.get(i).getClass().getSimpleName());
        }
    }

    private void printFurnitureList() {
        System.out.println("Furniture on canvas:");
        for (int i = 0; i < furniture.size(); i++) {
            System.out.println((i + 1) + ". " + furniture.get(i).getClass().getSimpleName());
        }
    }

    // Method to save the canvas data to a file
    public void saveCanvas() {
        JFileChooser fileChooser = new JFileChooser();
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileToSave))) {
                outputStream.writeObject(structures);
                outputStream.writeObject(furniture);
                System.out.println("Canvas saved successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to load the canvas data from a file
    public void loadCanvas() {
        JFileChooser fileChooser = new JFileChooser();
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileToLoad))) {
                structures = (List<StructureObject>) inputStream.readObject();
                furniture = (List<FurnitureObject>) inputStream.readObject();
                System.out.println("Canvas loaded successfully.");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
