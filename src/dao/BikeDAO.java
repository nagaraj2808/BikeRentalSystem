package dao;

import Models.Bike.Bike;


import java.sql.SQLException;
import java.util.List;

public interface BikeDAO {
    List<Bike> getAllBikes() throws SQLException;
    void addBike(Bike bike) throws SQLException;
    void deleteBike(String bikeId) throws SQLException;

//    List<User> searchUsers(String keyword) throws SQLException;

    List<Bike> searchBikesByType(String type) throws SQLException;
    Bike searchBikeByID(String bikeId) throws SQLException;
}
