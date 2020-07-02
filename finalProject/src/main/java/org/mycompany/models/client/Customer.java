package org.mycompany.models.client;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Child class of Client class. Already has defined role
 */
@Component
@Scope("prototype")
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
