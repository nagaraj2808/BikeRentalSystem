package Models.Bike;

public class BikeFactory {
    public static Bike createBike(String type, String bikeID,String model, String build, String color) {
        switch(type) {
            case "mechanical":
                return new ConcreteMechanicalBike(bikeID,model, build, color);
            case "electrical":
                return new ConcreteElectricBike(bikeID,model, build, color);
            default:
                throw new IllegalArgumentException("Invalid bike type: " + type);
        }
    }
}
