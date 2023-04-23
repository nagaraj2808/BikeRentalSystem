package Views;

import Controllers.AdminController;
import Controllers.Controller;
import Controllers.UserController;
import Models.Admin;
import Models.User;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AdminView {
    Controller controller = new Controller();
    private String adminName;

    public void adminMenu(){
        controller.setAdminController(new AdminController(this.adminName));
        Scanner scanner = new Scanner(System.in);

        int choice;
        do{
            System.out.println("Welcome!\n");
            System.out.println("1. View All Users");
            System.out.println("2. Add a new user");
            System.out.println("3. Delete an existing user");
            System.out.println("4. View all bikes");
            System.out.println("5. Add new bike");
            System.out.println("6. Remove an existing bike");

            System.out.println("-1. Return to login page");
            System.out.println("0. Exit\n");

            System.out.print("Enter your choice: ");
            choice=scanner.nextInt();
            String correctDetails;
            switch(choice){
                case 1:

                    String  allUsers = controller.getAdminController().viewAllUser();
                    if (Objects.equals(allUsers, "")) {
                        System.out.println("No users are there");
                    } else {
                        System.out.println(allUsers);
                    }
                    break;
                case 2:
                    correctDetails = addNewUser();
                    if (correctDetails!=null){
                        System.out.println("User added successfully");
                    }
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    viewAllBikes();
                    break;
                case 5:addNewBike();
                    break;
                case 6:deleteBike();
                    break;
                case 7: changePricing();
                    break;
                case -1:
                    System.out.println("Redirecting to login page.....\n");
                    break;
                case 0:
                    System.out.println("Thank you");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.\n");
                    break;
            }
        }while(choice!=-1);
    }

    private void changePricing() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the bikeId of the bike: ");
        String bikeId = scanner.nextLine();

        System.out.println("Set new price for this bike");
        double newPrice = scanner.nextDouble();
        String output = controller.getAdminController().changePrice(bikeId,newPrice);
        System.out.println(output + "\n");
    }

    private void viewAllBikes() {
        String allbikes = controller.getAdminController().viewAllBike();
        if (Objects.equals(allbikes, "")) {
            System.out.println("No bikes found");
        } else {
            System.out.println(allbikes);
        }
    }

    public void deleteUser(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the username of the user: ");
        String username = scanner.nextLine();
        if(!controller.getAdminController().deleteUser(username)){
            System.out.println("Operation failed Retry..........");
        }

    }
    public String addNewUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the Details of user to be added");
        System.out.print("Enter the username of the user: ");
        String username = scanner.nextLine();

        System.out.print("Enter name of user: ");
        String name = scanner.nextLine();

        System.out.print("Enter user password: ");
        String password = scanner.nextLine();

        System.out.print("Enter user email");
        String emailId = scanner.nextLine();

        System.out.print("Enter user phone number");
        String phoneNumber = scanner.nextLine();

        if(controller.getAdminController().addUser(username,name,emailId,phoneNumber,password)){
            return  username;
        }
        else {
            return null;
        }
    }

    public void addNewBike(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the Details of bike to be added");
        System.out.print("Enter bikeId: ");
        String bikeId = scanner.nextLine();

        System.out.print("Enter type: ");
        String type = scanner.nextLine();

        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        System.out.print("Enter build: ");
        String build = scanner.nextLine();

        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        System.out.print("set price: ");
        double price = scanner.nextDouble();


        System.out.println(controller.getAdminController().addBike(bikeId,type,model,build,color,"true",price));
    }

    public void deleteBike(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the bikeId of the bike: ");
        String bikeId = scanner.nextLine();
        if(!controller.getAdminController().deleteBike(bikeId)){
            System.out.println("Operation failed Retry..........");
        }
        else{
            System.out.println("Bike deleted successfully");
        }

    }



}
