package org.clients;

/**
 * Created by MadYeti on 10.04.2020.
 */
public abstract class Client {

    protected String login;
    protected String password;
    protected String role;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
