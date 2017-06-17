package entity;

/**
 * Created by John on 16.06.2017.
 */
public class LoginPassword {
    private String login;
    private String pussword;

    public LoginPassword() {
    }

    public LoginPassword(String login, String pussword) {
        this.login = login;
        this.pussword = pussword;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPussword() {
        return pussword;
    }

    public void setPussword(String pussword) {
        this.pussword = pussword;
    }
}
