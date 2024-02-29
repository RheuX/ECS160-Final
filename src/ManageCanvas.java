import java.util.ArrayList;
import java.util.List;

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
}
