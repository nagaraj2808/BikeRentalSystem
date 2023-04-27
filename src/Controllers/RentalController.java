package Controllers;

import Models.Bike.Bike;
import Models.Rental.ConcreteRental;
import Models.Rental.Rental;
import Models.Rental.RentalWithHelmet;
import Models.Rental.RentalWithInsurance;
import Models.User;
import dao.BikeRepository;
import dao.RentalRepository;
import dao.UserRepository;

import java.sql.SQLException;
import java.util.*;

public class RentalController {

    BikeRepository bikeRepositoryInstance;
    {
        try {
            bikeRepositoryInstance = BikeRepository.getInstance();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    UserRepository userRepositoryInstance;
    {
        try {
            userRepositoryInstance = UserRepository.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    RentalRepository rentalRepository;
    {
        try {
            rentalRepository = RentalRepository.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String username;
    public RentalController(String username){
        this.username = username;
    }
    public String viewAllBike() {
        HashMap<String,List<String >>bikeByTypeModel = getAllBikeByModelType();
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

                String avail;
                if(Boolean.toString(mechanicalBike.get(i).isAvailable()).equals("true")){
                avail = "Available";
            }else{ avail = "Unavailable";}
                bikeAttr = mechanicalBike.get(i).getModel()+"\tbuild:" +mechanicalBike.get(i).getBuild() + "  color:"
                        +mechanicalBike.get(i).getColor()+"  price:"
                        + mechanicalBike.get(i).getPrice()
                        + "Rs./hr   " + avail+ "  bikeId:"+mechanicalBike.get(i).getBikeId();


                modelBike.add(bikeAttr);

            }
            bikeByTypeModel.put("mechanical",new ArrayList<>(modelBike));
            modelBike = new ArrayList<>();
            for (int i = 0; i < electricalBike.size(); i++) {

                String avail ="";
                if(Boolean.toString(electricalBike.get(i).isAvailable()).equals("true")){
                    avail = "Available";
                }else{ avail = "Unavailable";}
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

    public boolean checkAvailability(String bikeId){
        boolean flag = false;
        try{
            Bike bike = bikeRepositoryInstance.searchBikeByID(bikeId);
            if (bike != null) {
                if (bike.isAvailable()){
                    flag = true;
                }
            }

        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return flag;
    }

    public void rentBike(String username, String bikeId) {
        Scanner scanner = new Scanner(System.in);
        User user = null;
        Bike bike = null;
        try {
             user  = userRepositoryInstance.searchUser(username);
             bike = bikeRepositoryInstance.searchBikeByID(bikeId);
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }

        System.out.println("Enter the duration in hrs: ");
        double duration = scanner.nextDouble();

        Rental rental = new ConcreteRental(user,bike,duration,new Date(),false);

        rental.getUser();
        System.out.println("Rented bike \t\t Rental cost: "+rental.calculatePrice());
        System.out.println("Do you want insurance in this ride?[Y/N]");
        boolean insurance = scanner.next().toLowerCase().charAt(0) == 'y';
        if (insurance){
            rental = new RentalWithInsurance(rental);
            System.out.println("Insurance added\t\t Rental cost: "+rental.calculatePrice());
        }
        System.out.println("Do you want helmet?[Y/N]");
        boolean helmet = scanner.next().toLowerCase().charAt(0) == 'y';
        if (helmet){
            rental = new RentalWithHelmet(rental);
            System.out.println("Helmet added\t\t Rental cost: "+rental.calculatePrice());
        }
//        System.out.println(rental);

        System.out.println("Do payment of rupees: " + rental.calculatePrice());
        double payment = scanner.nextDouble();
        while (payment != rental.calculatePrice()){
            System.out.println("Rental unsuccessful");
            System.out.println("Enter valid amount");
            payment = scanner.nextDouble();
        }
        System.out.println("Rental successful.......");



        bike.setAvailable(false);
        try {
            rentalRepository.addNewRental(rental);
            System.out.println("Note rental id for future references.\t\tRental id :");
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        try {

        bikeRepositoryInstance.updateBikeAvailability(bikeId,false);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }



    }

    public void returnBike(int id,String bikeId) {
        try {
            rentalRepository.updateAvailability(id);
        } catch (SQLException e) {
            System.err.println("Error updating"+e.getMessage());
        }

        try {
            bikeRepositoryInstance.updateBikeAvailability(bikeId,true);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void viewMyRentalHistory(String username) {
        try {

        List<Rental> rentals = rentalRepository.viewRentalHistory(username);
        for(Rental rental:rentals){
            System.out.println("Bike ID: "+rental.getSelectedBike().getBikeId() + "  rental id:"+rental.getRental_id() + " date:"+rental.getRentDate() + "  returned:"+rental.isReturned() + "  price:"+rental.getPrice());
//            System.out.println("User ID: "+rental.getUser().);
        }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
