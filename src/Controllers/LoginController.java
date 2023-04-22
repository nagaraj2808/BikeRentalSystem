package Controllers;

import Models.Admin;
import Models.User;
import Views.LoginView;
import Views.UserView;
import dao.AdminRepository;
import dao.UserRepository;

import java.sql.SQLException;

public class LoginController {
    private LoginView loginView;

    UserRepository userRepository;


    {
        try {
            userRepository = UserRepository.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    AdminRepository adminRepository;
    {
        try {
            adminRepository = AdminRepository.getInstance();
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
            User user = userRepository.searchUser(username);

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
            userRepository.addUser(user);
            flag = true;
            System.out.println("Registration Successful");
        } catch (SQLException e) {
            System.out.println("An error occurred while adding the user: " + e.getMessage());
            flag = false;
        }


        return flag;
    }


    public boolean handleAdminLogin(String name,String password){
        boolean flag = false;
        try {
            Admin admin = adminRepository.searchAdmin(name);

            if(admin!=null){
                if(admin.getAdminPassword().equals(password)){
                    flag = true;
                }
                else{
                    System.out.println("Invalid credentials");
                }
            }
            else{
                System.out.println("Admin not found.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while adding the user: " + e.getMessage());
        }
        return flag;
    }


    public boolean handleAdminRegister(String name , String password) {
        boolean flag = false;
        try {
            Admin admin = new Admin(name,password);
            adminRepository.addAdmin(admin);
            flag = true;
            System.out.println("Registration Successful");
        } catch (SQLException e) {
            System.out.println("An error occurred while adding the user: " + e.getMessage());
        }
        return flag;
    }
}
