package Views;

import Controllers.Controller;
import Controllers.RentalController;
import Controllers.UserController;
import Models.User;

import java.util.Objects;
import java.util.Scanner;

public class RentalView {
    private Controller controller = new Controller();
    private String username;

    public void setUsername(String username) {

        this.username = username;
    }

    public void RentalView(String username){
        this.username = username;
    }
    public void rentalMenu(){
            controller.setRentalController(new RentalController(this.username));
            Scanner scanner = new Scanner(System.in);

            int choice;
            do{
                System.out.println("Welcome!\n");
                System.out.println("1. Rent a bike");
                System.out.println("2. Return a rented bike");
                System.out.println("3. View Rental history");
                System.out.println("0. Exit\n");

                System.out.print("Enter your choice: ");
                choice=scanner.nextInt();

                switch(choice){
                    case 1:
                        rentBike();
                        break;
                    case 2:
                        returnBike();
                        break;
                    case 3:
                        viewRentalHistory();
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
    public void rentBike(){
        Scanner scanner = new Scanner(System.in);

        System.out.println(controller.getRentalController().viewAllBike());
        System.out.println("Enter selected bike id: ");
        String bikeId = scanner.nextLine();
        boolean avail = controller.getRentalController().checkAvailability(bikeId);
        if (!avail) {
            System.out.println("Bike not available");
            System.out.println("Notify options");
        }
        else{

            controller.getRentalController().rentBike(this.username,bikeId);

        }
    }

    public void returnBike(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter bike id: ");
        String bikeId = scanner.nextLine();
        System.out.println("Enter rental id");
        int id = scanner.nextInt();
        scanner.nextLine();
        controller.getRentalController().returnBike(id,bikeId);
    }

    public void viewRentalHistory(){
        controller.getRentalController().viewMyRentalHistory(this.username);
    }
}
