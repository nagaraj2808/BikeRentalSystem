package Models.Rental;

import Models.Bike.Bike;
import Models.User;

import java.util.Date;
import java.util.List;

public class ConcreteRental implements Rental{
    protected final double min_duration = 1.00; // 1.00 hr
    private int rental_id;

    public int getRental_id() {
        return rental_id;
    }

    public void setRental_id(int rental_id) {
        this.rental_id = rental_id;
    }

    private User user;
    private Bike selectedBike;
    private double duration;
    private Date rentDate;
    private boolean returned;
    private double price;

    public ConcreteRental(User user, Bike selectedBike, double duration, Date rentDate, boolean returned) {
        this.user = user;
        this.selectedBike = selectedBike;
        this.duration = duration;
        this.rentDate = rentDate;
        this.returned = returned;
        this.price = selectedBike.getPrice();
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bike getSelectedBike() {
        return selectedBike;
    }

    public void setSelectedBike(Bike selectedBike) {
        this.selectedBike = selectedBike;
    }

    public double getDuration() {
        return duration;
    }

    public double getMin_duration() {
        return min_duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public double calculatePrice(){
        return this.duration * selectedBike.getPrice();
    }
}
