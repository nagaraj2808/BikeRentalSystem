package Models.Rental;

public class RentalWithInsurance extends RentalDecorator {
    private double insuranceAmount = 100.00;

    public RentalWithInsurance(Rental rental) {
        super(rental);
    }
    public double getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }


    @Override
    public double calculatePrice() {
//        return rental.calculatePrice();
        double basePrice = rental.calculatePrice();
        return this.insuranceAmount + basePrice;
    }
}

