package Models.Rental;

public class RentalWithHelmet extends RentalDecorator{
    private double helmetPrice = 50.00;

    public double getHelmetPrice() {
        return helmetPrice;
    }

    public void setHelmetPrice(double helmetPrice) {
        this.helmetPrice = helmetPrice;
    }

    public RentalWithHelmet(Rental rental) {
        super(rental);
    }

    @Override
    public double calculatePrice(){
        return rental.calculatePrice() + this.helmetPrice;
    }

}
