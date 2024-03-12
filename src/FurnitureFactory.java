import java.awt.*;

public class FurnitureFactory {
    public static FurnitureObject createFurniture(DrawingTools.DrawingMode drawingMode, Point point, int width, int height) {
        switch (drawingMode) {
            case BED:
                return new Bed(point, 100, 150);
            case DESK:
                return new Desk(point, width, height);
            case CHAIR:
                return new Chair(point, 25, 25);
            case TOILET:
                return new Toilet(point, 20, 50);
            case SHOWER:
                return new Shower(point, 40, 80);
            case SINK:
                return new Sink(point, 60, 45);
            case TABLE:
                return new Table(point, width+20, height+40);
            case DININGCHAIR:
                return new DiningChair(point, 25, 25); 
            case REFRIGERATOR:
                return new Refrigerator(point, 50, 50);
            case SOFA:
                return new Sofa(point, width, height);
            case TV:
                return new TV(point, width, height);
            case COUCH:
                return new Couch(point, 50, 40);
            default:
                throw new IllegalArgumentException("Unknown drawing mode: " + drawingMode);
        }
    }
}
