package Models.Bike;
public class ConcreteMechanicalBike extends MechanicalBike {
    private double price = 200;
    public ConcreteMechanicalBike(String model, String build, String color) {
        super(model, build, color);
    }

    public boolean isModelAvailable(String model) {
        return this.getModel().equals(model) && this.isAvailable();
    }
    public double getPrice(){
        return this.price;
    }

}