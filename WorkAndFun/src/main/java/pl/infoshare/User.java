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
    private String voivodeship;
    private String city;
    private String cityDistrict;
    private int postalCode;
    private float rating;

    public User (int id, String firstName, String lastName, String password, int phone, String mail,int age,String languages, float rating)
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

    public User(int id, String firstName, String lastName, String password, int phone, String mail, int age, String languages, String voivodeship, String city, String cityDistrict, int postalCode, float rating) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.mail = mail;
        this.age = age;
        this.languages = languages;
        this.voivodeship = voivodeship;
        this.city = city;
        this.cityDistrict = cityDistrict;
        this.postalCode = postalCode;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
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

    public String getVoivodeship() {
        return voivodeship;
    }

    public String getCity() {
        return city;
    }

    public String getCityDistrict() {
        return cityDistrict;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public float getRating() {
        return rating;
    }
}