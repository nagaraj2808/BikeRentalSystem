package dao;

import Models.Admin;
import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository implements AdminDAO{
    DatabaseConnection dbInstance = DatabaseConnection.getDatabaseInstance();
    Connection connection = dbInstance.getConnection();

    public static AdminRepository instance = null;

    public AdminRepository() throws SQLException {
    }

    public static AdminRepository getInstance() throws SQLException {
        if (instance!=null){
            return instance;
        }
        else{
            return new AdminRepository();
        }
    }


    @Override
    public void addAdmin(Admin admin) throws SQLException {
        String query = "INSERT INTO admin (name, password) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, admin.getAdminName());
        statement.setString(2, admin.getAdminPassword());

        statement.executeUpdate();
    }

    @Override
    public Admin searchAdmin(String adminName) throws SQLException {
        String query = "SELECT * FROM admin WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, adminName);

        ResultSet resultSet = statement.executeQuery();
        Admin admin = null;
        while (resultSet.next()) {
            adminName = resultSet.getString("name");
            String password = resultSet.getString("password");
            admin = new Admin(adminName, password);

        }
        return admin;
    }
}
