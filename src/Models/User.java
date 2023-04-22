package Models;

public class User {
    private String userName;
    private String name;
    private String emailId;
    private String phoneNumber;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
    public void setName(String name) {
        this.name = name;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public User(String userName, String name, String emailId, String phoneNumber, String password) {
        this.userName = userName;
        this.name = name;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }


}
