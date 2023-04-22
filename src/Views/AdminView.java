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
                    System.out.println("fourth option");
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
}
