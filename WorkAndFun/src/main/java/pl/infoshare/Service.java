package pl.infoshare;

public class Service {
    private long serviceId;
    private String serviceType; //enum?
    private String status; //enum - DRAFT, SUBMITTED, ARCHIVED
    private String date; //klasa daty - do ogarnięcia
    private String hour; //klasa godziny - może razem z datą - doczytać o możliwościach javy
    private String voivodeship; //typ do zmiany na ENUM
    private String city;
    private int postalCode;
    private String street; //rozbić na streetName i streetNr + houseNr?
    private int price;
    private User client;
    private ServiceProvider contractor;
    private String comments;
    private float rating;

    //Co w przypadku kilku usług pod jednym id?
}
