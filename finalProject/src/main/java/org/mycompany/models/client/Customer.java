package org.mycompany.models.client;

/**
 * Child class of Client class. Already has defined role
 */
public class Customer extends Client{

    public Customer(){
        role = "customer";
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
