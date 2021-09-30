package pl.infoshare;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private int phone;
    private String mail;
    private int age;
    private String languages;
    private double rating;
    public User (int id, String firstName, String lastName, String password, int phone, String mail,int age,String languages, double rating)
    {
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.password=password;
        this.phone=phone;
        this.mail=mail;
        this.age=age;
        this.languages=languages;
        this.rating=rating;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public int getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public int getAge() {
        return age;
    }

    public String getLanguages() {
        return languages;
    }

    public double getRating() {
        return rating;
    }
}
