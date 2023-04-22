package Models.Bike;
public class ConcreteElectricBike extends ElectricBike {
    public ConcreteElectricBike(String model, String build, String color) {
        super(model, build, color);
    }

    public boolean isModelAvailable(String model) {
        return this.getModel().equals(model) && this.isAvailable();
    }
}
