package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final String url = "jdbc:mysql://localhost/bikerental";
    private final String user = "root";
    private final String password = "";
    private final Connection connection = DriverManager.getConnection(url, user, password);

    private static DatabaseConnection instance;
    private DatabaseConnection() throws SQLException {
        super();
    }

    public static DatabaseConnection getDatabaseInstance() throws SQLException {
        if (instance==null){
            instance = new DatabaseConnection();

        }
        return instance;
    }

    public Connection getConnection(){
        return this.connection;
    }
}
