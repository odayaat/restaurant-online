package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;

/**
 * The User class represents a user entity in the application.
 * It includes fields for user details and is mapped to a database table.
 */
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Name is mandatory")
    private String userName;

    @NotBlank
    @Size(max = 120)
    private String password;

    @NotBlank
    private String address;

    @Size(min = 10, max = 10)
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String telephone;

    private String role;

    /**
     * Default constructor for User.
     */
    public User() {

    }
    /**
     * Returns the ID of the user.
     *
     * @return the user ID
     */
    public long getId() {
        return id;
    }
    /**
     * Sets the ID of the user.
     *
     * @param id the user ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Constructs a new User instance with the specified id, username, and password.
     *
     * @param id the ID of the user
     * @param userName the username of the user
     * @param password the password of the user
     */
    public User(long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }
    /**
     * Returns the password of the user.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets the password of the user.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Checks if this user is equal to another user based on their ID.
     *
     * @param o the user to compare to
     * @return true if the users have the same ID, false otherwise
     */
    public boolean equals(User o) {
        return o.getId() == this.id;
    }
    /**
     * Returns the username of the user.
     *
     * @return the username
     */
    public String getUserName() {
        return userName;
    }
    /**
     * Sets the username of the user.
     *
     * @param userName the username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the role of the user.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }
    /**
     * Sets the role of the user.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }
    /**
     * Returns the address of the user.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }
    /**
     * Sets the address of the user.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Returns the telephone number of the user.
     *
     * @return the telephone number
     */
    public String getTelephone() {
        return telephone;
    }
    /**
     * Sets the telephone number of the user.
     *
     * @param telephone the telephone number
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
