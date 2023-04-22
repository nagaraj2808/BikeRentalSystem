package Models.Rental;

public class ConcreteRentalDecorator implements RentalDecorator {
    protected Rental rental;

    public ConcreteRentalDecorator(Rental rental) {
        this.rental = rental;
    }

    @Override
    public void rentBike() {
//        rental.rentBike();
    }

    @Override
    public void returnBike() {
//        rental.returnBike();
    }

    @Override
    public double calculatePrice() {
//        return rental.calculatePrice();
        return 9.0;
    }
}

