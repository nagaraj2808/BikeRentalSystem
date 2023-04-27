package Views;

import Controllers.Controller;
import Controllers.RentalController;
import Controllers.UserController;
import Models.User;

import java.util.Scanner;

public class UserView {
    private String username;
    private Controller controller = new Controller();




    public UserView(String username) {
        this.username = username;
    }

    public void userMenu(){
        Scanner scanner = new Scanner(System.in);

        int choice;
        do{
            System.out.println("Welcome!\n");
            System.out.println("1. View My Details");
            System.out.println("2. Rental page");
            System.out.println("-1. Return to login page");
            System.out.println("0. Exit\n");

            System.out.print("Enter your choice: ");
            choice=scanner.nextInt();

            switch(choice){
                case 1:
                    controller.setUserController(new UserController(this.username));
                    System.out.println(controller.getUserController().viewUser(this.username));
                    break;
                case 2:
                    rentalPage();
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

    private void rentalPage() {
        controller.setRentalController(new RentalController(this.username));
        controller.rentalPage(this.username);


    }



}
