package Controllers;

import Models.User;
import Views.LoginView;
import Views.UserView;
import dao.UserRepository;

import java.sql.SQLException;

public class LoginController {
    private LoginView loginView;

    UserRepository instance;

    {
        try {
            instance = UserRepository.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public LoginController(LoginView loginView){
       this.loginView = loginView;
   }
    public void start(){
        loginView.menuLoginPage();
    }

    public boolean handleLogin(String username,String password){
        boolean flag = false;
        try {
            User user = instance.searchUser(username);

            if(user!=null){
                if(user.getPassword().equals(password)){
                    flag = true;
                }
                else{
                    System.out.println("Invalid credentials");
                    flag = false;
                }
            }
            else{
                flag = false;
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while adding the user: " + e.getMessage());
            flag = false;
        }


        return flag;
    }
    public boolean handleRegister(String username,String name,String email,String phone,String password){
        boolean flag = false;
        try {
            User user = new User(username,name,email,phone,password);
            instance.addUser(user);
            flag = true;
            System.out.println("Registration Successful");
        } catch (SQLException e) {
            System.out.println("An error occurred while adding the user: " + e.getMessage());
            flag = false;
        }


        return flag;
    }


//
//    public void handleUser(String username){
//
//    }
}
