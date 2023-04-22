package Models.Bike;
public class ConcreteElectricBike extends ElectricBike {
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;
    public ConcreteElectricBike(String bikeId,String model, String build, String color) {
        super(bikeId,model, build, color);
    }

    public boolean isModelAvailable(String model) {
        return this.getModel().equals(model) && this.isAvailable();
    }
    public String getType(){
        return "electrical";
    }
}
