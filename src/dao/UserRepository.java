package dao;

import Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements UserDAO{


    DatabaseConnection dbInstance = DatabaseConnection.getDatabaseInstance();
    Connection connection = dbInstance.getConnection();
    public static UserRepository instance = null;
    public static UserRepository getInstance() throws SQLException {
        if (instance!=null){
            return instance;
        }
        else{
            return new UserRepository();
        }
    }
    private UserRepository() throws SQLException {
        super();
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String userName = resultSet.getString("userName");
            String name = resultSet.getString("name");
            String email = resultSet.getString("emailId");
            String phone = resultSet.getString("phoneNumber");
            String password = resultSet.getString("password");
            User user = new User(userName,name,email,phone, password);
            users.add(user);
        }
        return users;
    }

    @Override
    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO user (userName, name, emailId,phoneNumber,password) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getUserName());
        statement.setString(2, user.getName());
        statement.setString(3, user.getEmailId());
        statement.setString(4, user.getPhoneNumber());
        statement.setString(5, user.getPassword());
        statement.executeUpdate();
    }

    @Override
    public void deleteUser(String id) throws SQLException {

    }

    @Override
    public void updateUser(User book) throws SQLException {

    }

    @Override
    public List<User> searchUsers(String keyword) throws SQLException {
        return null;
    }

    @Override
    public User searchUser(String userName) throws SQLException {
        String query = "SELECT * FROM user WHERE userName = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userName);
//        statement.executeUpdate();
        ResultSet resultSet = statement.executeQuery();
        User user = null;
        while (resultSet.next()) {
            userName = resultSet.getString("userName");
            String name = resultSet.getString("name");
            String email = resultSet.getString("emailId");
            String phone = resultSet.getString("phoneNumber");
            String password = resultSet.getString("password");
            user = new User(userName,name,email,phone, password);

        }
        return user;
    }
}
