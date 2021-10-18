package pl.infoshare.announcements;

import pl.infoshare.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class Announcement implements Comparable<Announcement>{

    private boolean isOffer; //true=usługa/false=zapotrzebowanie
    private long ID;
    private ServiceType serviceType;
    private String city;
    private String cityDistrict;
    private String unit; //osiedle
    private String price;
    private Integer clientId;
    private Voivodeship voivodeship;
    private LocalDateTime date;
    private String nameOfAdvertiser;
    private String email;
    private boolean isPriceNegotiable = false;
    private String description;
    private String phoneNumber;
    private String priceAdditionComment = "";

    public Announcement(boolean isOffer, long ID, ServiceType serviceType, String city, String cityDistrict, String unit, String price, Voivodeship voivodeship, LocalDateTime date, String nameOfAdvertiser, String email, boolean isPriceNegotiable, String description, String phoneNumber, String priceAdditionComment, Integer clientId) {
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
        this.email = email;
        this.isPriceNegotiable = isPriceNegotiable;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.priceAdditionComment = priceAdditionComment;
    }



    public boolean getIsOffer() {
        return isOffer;
    }

    public long getID() {
        return ID;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public String getCity() {
        return city;
    }

    public String getCityDistrict() {
        return cityDistrict;
    }

    public String getUnit() {
        return unit;
    }

    public String getPrice() {
        return price;
    }

    public int getClientId() {
        return clientId;
    }

    public Voivodeship getVoivodeship() {
        return voivodeship;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getNameOfAdvertiser() {
        return nameOfAdvertiser;
    }

    public String getEmail() {
        return email;
    }

    public boolean getIsPriceNegotiable() {
        return isPriceNegotiable;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPriceAdditionComment() {
        return priceAdditionComment;
    }

    @Override
    public int compareTo(Announcement o) {
        if (!this.date.equals(o.date)){
            Duration duration = Duration.between(o.date,this.date);
            return Integer.valueOf(String.valueOf(duration.getSeconds())) ;
        } else {
            return Integer.valueOf(String.valueOf(o.ID - this.ID));
        }
    }
}

