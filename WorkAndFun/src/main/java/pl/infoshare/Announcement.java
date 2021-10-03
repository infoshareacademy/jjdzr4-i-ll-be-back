package pl.infoshare;


//import java.time.LocalDate;


public class Announcement {
    private boolean isService; //true=usługa/false=zapotrzebowanie
    private int adID;
    private String serviceType; //enum?
    private String city;
    private int postalCode;
    private String street;
    private int price;
    private User client;
    //private String voivodeship; //typ do zmiany na ENUM/WYWAŁKI
    //private LocalDate date; //local date time - IDE pruje się o zmiany w pom.xml, do obadania
}