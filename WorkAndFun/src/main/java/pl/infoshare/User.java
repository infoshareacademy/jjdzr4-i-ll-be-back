package pl.infoshare;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String voivodeship; //typ do zmiany na ENUM
    private String city;
    private String street;  //rozbić na streetName i streetNr + houseNr?
    private int postalCode; //dodać obsługę klasy Decimal Format?
    private int phone;
    private String mail;
}