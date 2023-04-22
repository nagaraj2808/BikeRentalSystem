package Models.Bike;

public abstract class Bike {
    private String model;
    private String build;
    private String color;
    private boolean available;

    public Bike(String model, String build, String color) {
        this.model = model;
        this.build = build;
        this.color = color;
        this.available = true;
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
}
