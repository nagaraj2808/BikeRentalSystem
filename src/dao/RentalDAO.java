package dao;

import Models.Rental.Rental;

import java.sql.SQLException;
import java.util.List;

public interface RentalDAO {
    public void addNewRental(Rental rental) throws SQLException;
    public void updateAvailability(int id)  throws SQLException;
    public List<Rental> viewRentalHistory(String username) throws SQLException;



}
