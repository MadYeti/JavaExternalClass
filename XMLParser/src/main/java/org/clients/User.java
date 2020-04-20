package org.clients;

/**
 * Created by MadYeti on 10.04.2020.
 */
public class User extends Client{

    private String role;

    public User(){
        role = "user";
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
