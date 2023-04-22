package Controllers;

import Models.Admin;
import Models.User;
import dao.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class AdminController {
    private String adminName;
    UserRepository userRepositoryInstance;
    {
        try {
            userRepositoryInstance = UserRepository.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public AdminController(String adminName){
        this.adminName = adminName;
    }


    public String viewAllUser() {
        try {

            List<User> users = userRepositoryInstance.getAllUsers();

            String USER_NAME = "\nUsername: ";
            String NAME = "\t Name:";
            String EMAIL = "\tEmail: ";
            String PHONE = "\tPhone: ";

            String allusers="";
            String userI;
            for (User user:users) {
                userI = "";
                userI = USER_NAME+ user.getUserName() + NAME + user.getName() + EMAIL+ user.getEmailId() +PHONE + user.getPhoneNumber();
                allusers+=userI;
            }
            return  allusers;
        } catch (SQLException e) {
            System.out.println("User details missing: " + e.getMessage());

        }
        return null;
    }


    public boolean addUser(String username,String name,String email,String phone,String password){
        boolean flag = false;
        try {
            User user = new User(username,name,email,phone,password);
            userRepositoryInstance.addUser(user);
            flag = true;
            System.out.println("User added Successfully");
        } catch (SQLException e) {
            System.out.println("An error occurred while adding the user: " + e.getMessage());
        }


        return flag;
    }


    public boolean deleteUser(String username){
        boolean flag = false;
        try {
            userRepositoryInstance.deleteUser(username);
            flag = true;
            System.out.println("User deleted Successfully");
        } catch (SQLException e) {
            System.out.println("An error occurred while deleting the user: " + e.getMessage());
        }


        return flag;
    }
}
