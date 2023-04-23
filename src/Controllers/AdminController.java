package Controllers;

import Models.Admin;
import Models.Bike.Bike;
import Models.Bike.BikeFactory;
import Models.User;
import dao.BikeRepository;
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

    BikeRepository bikeRepositoryInstance;
    {
        try {
            bikeRepositoryInstance = BikeRepository.getInstance();
        }catch (SQLException e){
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

    public String viewAllBike() {
        try {

            List<Bike> bikes = bikeRepositoryInstance.getAllBikes();



            String allBikes = "";

            for (Bike i:bikes) {
                allBikes = "\nbikeId:(" + i.getBikeId() + ")  type:(" + i.getType() + ")   model:(" +  i.getModel() + ")   build" + i.getBuild() +")   color:("+ i.getColor() + ")   price:(" + i.getPrice()+")";
            }
            return  allBikes;
        } catch (SQLException e) {
            System.out.println("User details missing: " + e.getMessage());

        }
        return null;
    }

    public String addBike(String bikeId,String type,String model,String build,String color,String available,double price){
        String output = "";
        try {
            try {
                Bike bike = BikeFactory.createBike(type,bikeId,model,build,color);
                bike.setAvailable(true);
                bike.setPrice(price);
                bikeRepositoryInstance.addBike(bike);
                output = "Bike added successfully\n";
            }
            catch (IllegalArgumentException e){
                output = "IllegalArgumentException:" + e.getMessage()+ "\n";
            }




        } catch (SQLException e) {
            output =  "SQLException: An error occurred while adding the user: " + e.getMessage() + "\n";
        }
        return output;
    }

    public boolean deleteBike(String bikeId){
        boolean flag = false;
        try {
            Bike bike = bikeRepositoryInstance.searchBikeByID(bikeId);

            if(bike!=null){

            bikeRepositoryInstance.deleteBike(bikeId);
            flag = true;
            }
        }
        catch (SQLException e){
            System.out.println("An error occurred while deleting the bike: " + e.getMessage());
        }


        return flag;
    }

    public String changePrice(String bikeId,double newPrice) {
        String output = "";
        try {
            Bike bike = bikeRepositoryInstance.searchBikeByID(bikeId);

            if(bike!=null){

                bikeRepositoryInstance.updateBikePrice(bikeId,newPrice);
                output = "Updated bike price successfully......";
            }
            else{
                output = "Bike not found";
            }
        }
        catch (SQLException e){
            output = "SQL ERROR: An error occurred while deleting the bike: " + e.getMessage();
        }
        return output;

    }
}
