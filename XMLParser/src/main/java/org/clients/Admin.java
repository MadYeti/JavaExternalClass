package org.clients;

/**
 * Created by MadYeti on 10.04.2020.
 */
public class Admin extends Client {

    private String role;

    public Admin(){
        role = "admin";
    }

    @Override
    public String toString() {
        return "Admin{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
