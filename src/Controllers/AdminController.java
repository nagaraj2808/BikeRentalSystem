package Controllers;

import Models.Admin;
import Models.Bike.Bike;
import Models.Bike.BikeFactory;
import Models.User;
import dao.BikeRepository;
import dao.UserRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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


    public String  addUser(String username,String name,String email,String phone,String password){
//        boolean flag = false;
        String output = "";
        try {
            User user = new User(username,name,email,phone,password);
            userRepositoryInstance.addUser(user);
            output = "User added Successfully";
        } catch (SQLException e) {
            output = "SQLException: An error occurred while adding the user: " + e.getMessage();
        }


        return output;
    }


    public String deleteUser(String username){
//        boolean flag = false;
        String output = "";
        try {
            User user = userRepositoryInstance.searchUser(username);
            if (user!=null){
                userRepositoryInstance.deleteUser(username);
                output = "Deleted user successfully\n";
            }
            else{
                output = "User not found\n";
            }
        } catch (SQLException e) {
            output = "SQLException: An error occurred while deleting the user: " + e.getMessage();
        }


        return output;
    }

    public String viewAllBike() {
        HashMap<String,List<String >> bikeByTypeModel = getAllBikeByModelType();
        String allbikes = "";
        if (bikeByTypeModel!=null){
            for (String type : bikeByTypeModel.keySet()) {
                allbikes+="\n\n\t\t-------------Type: " + type + "-------------------";
                List<String >models = bikeByTypeModel.get(type);
                for (String model : models) {
                    allbikes += "\n\tModel: " +model;
                }
            }
        }
        return allbikes;
    }

    private HashMap<String ,List<String>> getAllBikeByModelType(){
        HashMap<String,List<String>>bikeByTypeModel = null;
        try {
            bikeByTypeModel = new HashMap<>();

//            List<Bike> bikes = bikeRepositoryInstance.searchBikesByType("mechanical");
//            List<Bike> electricBike = bikeRepositoryInstance.searchBikesByType("electrical");
//            List<String,List<String>> modelMechBike = new ArrayList<>();
            List<Bike> mechanicalBike = bikeRepositoryInstance.searchBikesByType("mechanical");
            List<Bike> electricalBike = bikeRepositoryInstance.searchBikesByType("electrical");
            List<String > modelBike = new ArrayList<>();
            String bikeAttr;
            for (int i = 0; i < mechanicalBike.size(); i++) {

                String avail = (mechanicalBike.get(i).isAvailable())?"Available":"Unavailabe";
                bikeAttr = mechanicalBike.get(i).getModel()+"\tbuild:" +mechanicalBike.get(i).getBuild() + "  color:"
                        +mechanicalBike.get(i).getColor()+"  price:"
                        + mechanicalBike.get(i).getPrice()
                        + "Rs./hr   " + avail+ "  bikeId:"+mechanicalBike.get(i).getBikeId();


                modelBike.add(bikeAttr);

            }
            bikeByTypeModel.put("mechanical",new ArrayList<>(modelBike));
            modelBike = new ArrayList<>();
            for (int i = 0; i < electricalBike.size(); i++) {

                String avail = (electricalBike.get(i).isAvailable())?"Available":"Unavailabe";
                bikeAttr = electricalBike.get(i).getModel()+ "\tbuild:" +electricalBike.get(i).getBuild() + "  color:"
                        +electricalBike.get(i).getColor()+"  price:"
                        + electricalBike.get(i).getPrice()
                        + "Rs./hr   " + avail+ "  bikeId:"+electricalBike.get(i).getBikeId();


                modelBike.add(bikeAttr);

            }
            bikeByTypeModel.put("electrical",new ArrayList<>(modelBike));





        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }
        return bikeByTypeModel;
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
