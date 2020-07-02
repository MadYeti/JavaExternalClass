package org.mycompany.models.client;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Child class of Client class. Already has defined role
 */
@Component
@Scope("prototype")
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
