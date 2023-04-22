package dao;

import Models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers() throws SQLException;
    void addUser(User user) throws SQLException;
    void deleteUser(String id) throws SQLException;
    void updateUser(User book) throws SQLException;
    List<User> searchUsers(String keyword) throws SQLException;

    User searchUser(String userName) throws SQLException;
}
