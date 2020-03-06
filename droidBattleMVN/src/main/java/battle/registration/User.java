package battle.registration;

/**
 * Created by MadYeti on 06.03.2020.
 */
public class User {

    private String login;
    private String password;

    public User(String login, String password){
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
