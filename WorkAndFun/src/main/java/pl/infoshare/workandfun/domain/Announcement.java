package pl.infoshare.workandfun.domain;

import pl.infoshare.workandfun.announcements.Categories.ServiceType;
import pl.infoshare.workandfun.announcements.Type;
import pl.infoshare.workandfun.announcements.Voivodeship;

import java.time.Duration;
import java.time.LocalDateTime;

public class Announcement implements Comparable<Announcement> {

    private Type type;
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

    public Announcement(Type type, String header, long id, ServiceType serviceType, String city,
                        String cityDistrict, String unit, String price, Voivodeship voivodeship, LocalDateTime date,
                        String nameOfAdvertiser, String email, Boolean isPriceNegotiable, String description,
                        String phoneNumber, String priceAdditionComment, Integer clientId) {
        this.type = type;
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

    public Type getType() {
        return type;
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

    public String getHeader() {
        return header;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCityDistrict(String cityDistrict) {
        this.cityDistrict = cityDistrict;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public void setVoivodeship(Voivodeship voivodeship) {
        this.voivodeship = voivodeship;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setNameOfAdvertiser(String nameOfAdvertiser) {
        this.nameOfAdvertiser = nameOfAdvertiser;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPriceNegotiable(Boolean priceNegotiable) {
        isPriceNegotiable = priceNegotiable;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPriceAdditionComment(String priceAdditionComment) {
        this.priceAdditionComment = priceAdditionComment;
    }

    @Override
    public int compareTo(Announcement o) {
        if (!this.date.isEqual(o.date)) {
            Duration duration = Duration.between(o.date, this.date);
            return Integer.parseInt(String.valueOf(duration.getSeconds()));
        } else {
            return Integer.parseInt(String.valueOf(o.id - this.id));
        }
    }
}
