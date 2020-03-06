package battle.registration;

/**
 * Created by MadYeti on 06.03.2020.
 */
public class Admin {

    private String login;
    private String password;

    public Admin(){
        this.login = "admin";
        this.password = "admin";
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
