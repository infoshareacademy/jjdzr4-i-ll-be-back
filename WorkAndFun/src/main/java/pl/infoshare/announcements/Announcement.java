package pl.infoshare.announcements;


import java.time.Duration;
import java.time.LocalDateTime;

public class Announcement implements Comparable<Announcement>{

    private boolean isOffer; //true=usługa/false=zapotrzebowanie
    private String header;
    private long id;
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
    private Boolean isPriceNegotiable = false;
    private String description;
    private String phoneNumber;
    private String priceAdditionComment = "";

    public Announcement(boolean isOffer, String header, long id, ServiceType serviceType, String city, String cityDistrict, String unit, String price, Voivodeship voivodeship, LocalDateTime date, String nameOfAdvertiser, String email, Boolean isPriceNegotiable, String description, String phoneNumber, String priceAdditionComment, Integer clientId) {
        this.isOffer = isOffer;
        this.header = header;
        this.id = id;
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
        this.clientId = clientId;
    }



    public boolean getIsOffer() {
        return isOffer;
    }

    public long getId() {
        return id;
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

    public Integer getClientId() {
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

    public Boolean getIsPriceNegotiable() {
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

<<<<<<< HEAD

=======
    public String getHeader() {
        return header;
    }

    @Override
    public int compareTo(Announcement o) {
        if (!this.date.isEqual(o.date)){
            Duration duration = Duration.between(o.date,this.date);
            return Integer.valueOf(String.valueOf(duration.getSeconds())) ;
        } else {
            return Integer.valueOf(String.valueOf(o.id - this.id));
        }
    }
>>>>>>> origin/master
}

