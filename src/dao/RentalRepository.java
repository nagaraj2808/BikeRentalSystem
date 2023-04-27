package dao;


import Models.Bike.Bike;
import Models.Bike.BikeFactory;
import Models.Rental.ConcreteRental;
import Models.Rental.Rental;
import Models.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentalRepository implements RentalDAO {
    DatabaseConnection dbInstance = DatabaseConnection.getDatabaseInstance();
    Connection connection = dbInstance.getConnection();
    public static RentalRepository instance = null;


    public RentalRepository() throws SQLException {
    }
    public static RentalRepository getInstance() throws SQLException {
        if (instance!=null){
            return instance;
        }
        else{
            return new RentalRepository();
        }
    }




    @Override
    public void addNewRental(Rental rental) throws SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateString = formatter.format(date);
        String query = "INSERT INTO rental (bikeId,username,rentDate,duration,returned,price) VALUES (?, ?, ?, ?,?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, rental.getSelectedBike().getBikeId());
        statement.setString(2, rental.getUser().getUserName());
        statement.setString(3,formatter.format(rental.getRentDate()));
        statement.setDouble(4,rental.getDuration());
        statement.setBoolean(5,rental.isReturned());
        statement.setDouble(6,rental.calculatePrice());

        statement.executeUpdate();

    }

    @Override
    public void updateAvailability(int id) throws SQLException{
        String query = "UPDATE rental SET returned = ? WHERE rental_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
//        PreparedStatement statement = connection.prepareStatement(query);
        statement.setBoolean(1,true);
        statement.setInt(2,id);

        statement.executeUpdate();
    }

    @Override
    public List<Rental> viewRentalHistory(String username) throws SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String query = "SELECT * FROM rental WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        List<Rental> rentals = new ArrayList<>();
        while (resultSet.next()) {
            int rental_id = resultSet.getInt("rental_id");
            String bikeId = resultSet.getString("bikeId");
            String date = resultSet.getString("rentDate");
            Date rentDate = null;
            try {
                rentDate = formatter.parse(date);
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
            boolean returned = resultSet.getBoolean("returned");
            double duration = resultSet.getDouble("duration");
            double price =resultSet.getDouble("price");

            UserRepository userRepositoryInstance = UserRepository.getInstance();
            User user = userRepositoryInstance.searchUser(username);
            BikeRepository bikeRepository = BikeRepository.getInstance();
            Bike bike = bikeRepository.searchBikeByID(bikeId);

            Rental rental = new ConcreteRental(user,bike,duration,rentDate,returned);
            rental.setRental_id(rental_id);

            rentals.add(rental);
        }
        return rentals;
    }
}
