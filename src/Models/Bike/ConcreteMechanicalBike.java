package Models.Bike;
public class ConcreteMechanicalBike extends MechanicalBike {
    private double price;
    public ConcreteMechanicalBike(String bikeID,String model, String build, String color) {
        super(bikeID,model, build, color);
    }

    public boolean isModelAvailable(String model) {
        return this.getModel().equals(model) && this.isAvailable();
    }
    public double getPrice(){
        return this.price;
    }

    public void setPrice(double price){
        this.price = price;
    }
    public String getType(){
        return "mechanical";
    }
}