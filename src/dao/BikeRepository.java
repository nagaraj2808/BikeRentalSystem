package dao;

import Models.Bike.Bike;
import Models.Bike.BikeFactory;
import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BikeRepository implements BikeDAO{

    DatabaseConnection dbInstance = DatabaseConnection.getDatabaseInstance();
    Connection connection = dbInstance.getConnection();
    public static BikeRepository instance = null;

    public BikeRepository() throws SQLException {
    }

    public static BikeRepository getInstance() throws SQLException {
        if (instance!=null){
            return instance;
        }
        else{
            return new BikeRepository();
        }
    }


    @Override
    public List<Bike> getAllBikes() throws SQLException {
        List<Bike> bikes = new ArrayList<>();
        String query = "SELECT * FROM bike";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String bikeId = resultSet.getString("bikeId");
            String model = resultSet.getString("model");
            String build = resultSet.getString("build");
            String color = resultSet.getString("color");
            boolean available = Boolean.parseBoolean(resultSet.getString("available"));
            String type = resultSet.getString("type");
            double price =resultSet.getDouble("price");
            Bike bike = BikeFactory.createBike(type,bikeId,model,build,color);
            bike.setAvailable(available);
            bike.setPrice(price);
            bikes.add(bike);
        }
        return bikes;
    }

    @Override
    public void addBike(Bike bike) throws SQLException {
        String query = "INSERT INTO bike(bikeId,model,build,color,available,type,price) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, bike.getBikeId());
        statement.setString(2, bike.getModel());
        statement.setString(3, bike.getBuild());
        statement.setString(4, bike.getColor());
        statement.setString(5, String.valueOf(bike.isAvailable()));
        statement.setString(6, bike.getType());
        statement.setDouble(7, bike.getPrice());
        statement.executeUpdate();

    }

    @Override
    public void deleteBike(String bikeId) throws SQLException {
        String query = "DELETE  FROM bike WHERE bikeId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, bikeId);
        statement.executeUpdate();
    }

    @Override
    public List<Bike> searchBikesByType(String type) throws SQLException {
        String query = "SELECT * FROM bike WHERE type = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, type);
        ResultSet resultSet = statement.executeQuery();
        List<Bike> bikes = new ArrayList<>();
        while (resultSet.next()) {
            String bikeId = resultSet.getString("bikeId");
            String model = resultSet.getString("model");
            String build = resultSet.getString("build");
            String color = resultSet.getString("color");
            boolean available = Boolean.parseBoolean(resultSet.getString("available"));
            type = resultSet.getString("type");
            double price =resultSet.getDouble("price");
            Bike bike = BikeFactory.createBike(type,bikeId,model,build,color);
            bike.setAvailable(available);
            bike.setPrice(price);
            bikes.add(bike);
        }
        return bikes;
    }

    @Override
    public Bike searchBikeByID(String bikeId) throws SQLException {
        String query = "SELECT * FROM bike WHERE bikeId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, bikeId);
        ResultSet resultSet = statement.executeQuery();
        Bike bike= null;
        while (resultSet.next()) {
            bikeId = resultSet.getString("bikeId");
            String model = resultSet.getString("model");
            String build = resultSet.getString("build");
            String color = resultSet.getString("color");
            boolean available = Boolean.parseBoolean(resultSet.getString("available"));
            String type = resultSet.getString("type");
            double price =resultSet.getDouble("price");
            bike = BikeFactory.createBike(type,bikeId,model,build,color);
            bike.setAvailable(available);
            bike.setPrice(price);

        }

        return bike;
    }

    @Override
    public void updateBikePrice(String bikeId, double newPrice) throws SQLException {
        String query = "UPDATE bike SET price = ? WHERE bikeId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
//        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDouble(1, newPrice);
        statement.setString(2, bikeId);
        statement.executeUpdate();
    }

    @Override
    public void updateBikeAvailability(String bikeId, boolean returned) throws SQLException {
        String query = "UPDATE bike SET available = ? WHERE bikeId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
//        PreparedStatement statement = connection.prepareStatement(query);
        String avail = (returned)?"true":"false";
        statement.setString(1, avail);
        statement.setString(2, bikeId);
        statement.executeUpdate();
    }
}
