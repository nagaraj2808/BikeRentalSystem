package Views;

import java.util.Scanner;

public class UserView {
    private String username;

    public UserView(String username) {
        this.username = username;
    }

    public void userMenu(){
        Scanner scanner = new Scanner(System.in);

        int choice;
        do{
            System.out.println("Welcome!\n");
            System.out.println("1. View your Details");
            System.out.println("2. Rent a bike");
            System.out.println("3. Return a rented bike");
            System.out.println("4. View Rental history");
            System.out.println("-1. Return to login page");
            System.out.println("0. Exit\n");

            System.out.print("Enter your choice: ");
            choice=scanner.nextInt();

            switch(choice){
                case 1:
                    System.out.println("First option");
                    break;
                case 2:
                    System.out.println("Second option");
                    break;
                case 3:
                    System.out.println("Third option");
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

}
