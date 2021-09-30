package pl.infoshare;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private int phone;
    private String mail;
    private float rating;
    public User (int id, String firstName, String lastName, String password, int phone, String mail, float rating)
    {
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.password=password;
        this.phone=phone;
        this.mail=mail;
        this.rating=rating;
    }

    public String getFirstName() {
        return firstName;
    }
}
