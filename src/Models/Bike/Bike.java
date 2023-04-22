package Models.Bike;

public abstract class Bike {
    private String bikeId;
    private String model;
    private String build;
    private String color;
    private boolean available;

    public Bike(String bikeId, String model, String build, String color) {
        this.bikeId = bikeId;
        this.model = model;
        this.build = build;
        this.color = color;
        this.available = true;
    }
    public String getBikeId() {
        return bikeId;
    }

    public void setBikeId(String bikeId) {
        this.bikeId = bikeId;
    }

    public String getModel() {
        return model;
    }

    public String getBuild() {
        return build;
    }

    public String getColor() {
        return color;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract boolean isModelAvailable(String model);
    public abstract double getPrice();
    public abstract void setPrice(double price);
    public abstract  String getType();
}
