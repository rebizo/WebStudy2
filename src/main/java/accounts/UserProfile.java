package accounts;

public class UserProfile {

    private final String login;
    private final String pass;
    private final String email;

    public UserProfile(String login, String password, String email) {
        this.login = login;
        this.pass = password;
        this.email = email;
    }

    public UserProfile(String login) {
        this.login = login;
        this.pass = login;
        this.email = login;
    }

    String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

   /* public String getEmail() {
        return email;
    }*/
}