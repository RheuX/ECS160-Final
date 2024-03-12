public class AddFurnitureCommand implements Command {
    private final ManageCanvas manageCanvas;
    private final FurnitureObject furnitureObject;

    public AddFurnitureCommand(ManageCanvas manageCanvas, FurnitureObject furnitureObject) {
        this.manageCanvas = manageCanvas;
        this.furnitureObject = furnitureObject;
    }

    @Override
    public void execute() {
        manageCanvas.addFurniture(furnitureObject);
    }

    @Override
    public void undo() {
        manageCanvas.deleteFurniture(furnitureObject);
    }
}
