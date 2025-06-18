package org.easysplit.entitites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class User extends BaseEntity {
    private String userName;
    private String firstName;
    private String lastName;
    private String emailId;
    @Column(unique = true)
    private String phone;

    public User() {
        super();
    }

    public User(int id, String userName, String firstName, String lastName, String emailId, String phone) {
        super(id);
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
