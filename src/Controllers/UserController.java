package Controllers;

import Models.User;
import dao.UserRepository;

import java.sql.SQLException;

public class UserController {
    private String username;

    UserRepository instance;

    {
        try {
            instance = UserRepository.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public UserController(String username){
        this.username = username;
    }



    public User viewUser(String username) {
        try {

            User user = instance.searchUser(username);

            return user;
        } catch (SQLException e) {
            System.out.println("User details missing: " + e.getMessage());

        }
        return null;
    }
}
