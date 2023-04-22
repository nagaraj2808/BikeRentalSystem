package Models.Bike;

public class BikeFactory {
    public static Bike createBike(String type, String model, String build, String color) {
        switch(type) {
            case "mechanical":
                return new ConcreteMechanicalBike(model, build, color);
            case "electrical":
                return new ConcreteElectricBike(model, build, color);
            default:
                throw new IllegalArgumentException("Invalid bike type: " + type);
        }
    }
}
