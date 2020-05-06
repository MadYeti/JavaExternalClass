package org.mycompany.models.client;


public class Admin extends Client{

    public Admin(){
        role = "admin";
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
