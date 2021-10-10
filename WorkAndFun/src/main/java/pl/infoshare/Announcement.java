package pl.infoshare;

import java.time.LocalDateTime;

public class Announcement {

    private boolean isOffer; //true=usługa/false=zapotrzebowanie
    private long ID;
    private ServiceTypes serviceType; //enum?         //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private String city;                       //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private String cityDistrict;                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private String unit; //osiedle          //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private String price;                   //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private User client;
    private Voivodeship voivodeship; //typ do zmiany na ENUM/WYWAŁKI   !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private LocalDateTime date;                 //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private String nameOfAdvertiser;       //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private String mail;                   //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private boolean isPriceNegotiable = false;  //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private String description;            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private String phoneNumber;           //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private String priceAdditionComment = "";  //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public Announcement(boolean isOffer, long ID, ServiceTypes serviceType, String city, String cityDistrict, String unit, String price, Voivodeship voivodeship, LocalDateTime date, String nameOfAdvertiser, String mail, boolean isPriceNegotiable, String description, String phoneNumber, String priceAdditionComment) {
        this.isOffer = isOffer;
        this.ID = ID;
        this.serviceType = serviceType;
        this.city = city;
        this.cityDistrict = cityDistrict;
        this.unit = unit;
        this.price = price;
        this.voivodeship = voivodeship;
        this.date = date;
        this.nameOfAdvertiser = nameOfAdvertiser;
        this.mail = mail;
        this.isPriceNegotiable = isPriceNegotiable;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.priceAdditionComment = priceAdditionComment;
    }



}

