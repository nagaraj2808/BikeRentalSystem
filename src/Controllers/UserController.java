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



    public String  viewUser(String username) {
        String userInfo="";
        try {

            User user = instance.searchUser(username);
            userInfo = getUserView(user);


        } catch (SQLException e) {
            userInfo = "User details missing: " + e.getMessage();

        }
        return userInfo;
    }
    public String getUserView(User user){
//         "-----------------------------------------\n" + "|   Name :" + user.getName() + "     |\n|         |"

        return "User details\nUsername:"+user.getUserName()+"\nName:"+user.getName()+"\nEmail id:"+user.getEmailId()+"\nPhone Number:"+user.getPhoneNumber()+"\n\n";
    }


}
