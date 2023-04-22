package Views;
import Controllers.Controller;
import Controllers.LoginController;

import java.util.Scanner;


public class LoginView {
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void  menuLoginPage(){
        Scanner scanner=new Scanner(System.in);

        int choice;
        do{
            System.out.println("Welcome to the Login System!\n");
            System.out.println("1. Enter Login Details");
            System.out.println("2. Create New Account");
            System.out.println("0. Exit\n");

            System.out.print("Enter your choice: ");
            choice=scanner.nextInt();
            String correctDetails=null;
            switch(choice){
                case 1:
                    correctDetails = enterLoginDetails();
                    if (correctDetails!=null){
                        controller.userController(correctDetails);
                    }
                    break;
                case 2:
                    correctDetails = createNewAccount();
                    if (correctDetails!=null){
                        controller.userController(correctDetails);
                    }

                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
                    break;
            }
        }while(choice!=0);

    }

    public  String enterLoginDetails() {
        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter User de");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if(controller.getLoginController().handleLogin(username,password)){
            return  username;
        }
        else {
            return null;
        }

    }
    public  String createNewAccount() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your desired username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your desired name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your desired password: ");
        String password = scanner.nextLine();

        System.out.print("Enter email");
        String emailId = scanner.nextLine();

        System.out.print("Enter phone number");
        String phoneNumber = scanner.nextLine();

        if(controller.getLoginController().handleRegister(username,name,emailId,phoneNumber,password)){
            return  username;
        }
        else {
            return null;
        }
    }
}





