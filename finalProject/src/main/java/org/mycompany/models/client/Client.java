package org.mycompany.models.client;

import org.mycompany.controllers.mail.MailController;
import org.mycompany.models.observer.Subject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Observable;
import java.util.Observer;

/**
 * Parent model class for client object. Represents by clients table in DB
 * Implements interface observer for Observable pattern realization
 */
@XmlAccessorType(XmlAccessType.NONE)
public class Client implements Observer{

    protected Observable observable;
    @XmlElement(name = "id")
    protected int id;
    @XmlElement(name = "email")
    protected String email;
    protected String password;
    @XmlElement(name = "role")
    protected String role;
    protected String token;

    public Client(){

    }

    public Client(int id){
        this.id = id;
        this.email = "";
        this.password = "";

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setObservable(Observable observable) {
        this.observable = observable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        Client client = (Client) o;

        if (email != null ? !email.equals(client.email) : client.email != null) return false;
        if (password != null ? !password.equals(client.password) : client.password != null) return false;
        return role != null ? role.equals(client.role) : client.role == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public void update(Observable o, Object arg) {
        Subject subject = (Subject) o;
        MailController mailController = new MailController(email);
        mailController.sendPaymentNotificationEmail(subject.getId());
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
