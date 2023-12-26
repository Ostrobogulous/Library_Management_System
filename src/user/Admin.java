package user;

public class Admin extends User {
    private String password;

    public Admin(String name, String email, String password) {
        super(name, email);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
