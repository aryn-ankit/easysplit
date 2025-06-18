package org.easysplit.dto;

public class UserInfo extends BaseDTO {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public UserInfo(int id, String userName, String firstName, String lastName, String emailId, String phoneNumber) {
        super(id);
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = emailId;
        this.phoneNumber = phoneNumber;
    }

    public UserInfo() {
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
