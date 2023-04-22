package Controllers;

import Views.LoginView;
import Views.UserView;

public class Controller {
    UserController userController;

    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public UserController getUserController() {
        return userController;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    LoginController loginController;
    public void start(){
        LoginView loginView = new LoginView();
        this.setLoginController(new LoginController(loginView));
        loginView.setController(this);
        loginView.menuLoginPage();
    }

    public void userController(String username){
        this.setUserController(new UserController(username));
        UserView userView = new UserView(username);
        userView.userMenu();
    }


}
