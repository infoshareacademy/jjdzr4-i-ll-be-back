package pl.infoshare.announcements;


import java.time.Duration;
import java.time.LocalDateTime;

public class Announcement implements Comparable<Announcement> {

    public static final String[] ANNOUNCEMENT_HEADER = {"ID", "offerType", "serviceType", "voivodeship", "city",
            "cityDistrict", "unit", "nameOfAdvertiser", "phoneNumber", "email", "description", "price",
            "isPriceNegotiable", "priceAdditionComment", "date", "clientId", "Header"};

    private OfferType offerType;
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

    public Announcement(OfferType offerType, String header, long id, ServiceType serviceType, String city,
                        String cityDistrict, String unit, String price, Voivodeship voivodeship, LocalDateTime date,
                        String nameOfAdvertiser, String email, Boolean isPriceNegotiable, String description,
                        String phoneNumber, String priceAdditionComment, Integer clientId) {
        this.offerType = offerType;
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

    public Announcement() {
    }

    public String[] mapToStringArray() {
        String[] announcementInString = new String[17];
        announcementInString[0] = String.valueOf(this.getId());
        announcementInString[1] = String.valueOf(this.getOfferType());
        announcementInString[2] = String.valueOf(this.getServiceType());
        announcementInString[3] = String.valueOf(this.getVoivodeship());
        announcementInString[4] = String.valueOf(this.getCity());
        announcementInString[5] = String.valueOf(this.getCityDistrict());
        announcementInString[6] = String.valueOf(this.getUnit());
        announcementInString[7] = String.valueOf(this.getNameOfAdvertiser());
        announcementInString[8] = String.valueOf(this.getPhoneNumber());
        announcementInString[9] = String.valueOf(this.getEmail());
        announcementInString[10] = String.valueOf(this.getDescription());
        announcementInString[11] = String.valueOf(this.getPrice());
        announcementInString[12] = String.valueOf(this.getIsPriceNegotiable());
        announcementInString[13] = String.valueOf(this.getPriceAdditionComment());
        announcementInString[14] = String.valueOf(this.getDate());
        announcementInString[15] = String.valueOf(this.getClientId());
        announcementInString[16] = String.valueOf(this.getHeader());

        return announcementInString;
    }

    public static Announcement mapStringArrayToAnnouncement(String[] attributes) {
        Announcement announcement = new Announcement();
        announcement.setId(Long.parseLong(attributes[0]));
        announcement.setOfferType(OfferType.valueOf(attributes[1]));
        announcement.setServiceType(ServiceType.valueOf(attributes[2]));
        announcement.setVoivodeship(Voivodeship.valueOf(attributes[3]));
        announcement.setCity(attributes[4]);
        announcement.setCityDistrict(attributes[5]);
        announcement.setUnit(attributes[6]);
        announcement.setNameOfAdvertiser(attributes[7]);
        announcement.setPhoneNumber(attributes[8]);
        announcement.setEmail(attributes[9]);
        announcement.setDescription(attributes[10]);
        announcement.setPrice(attributes[11]);
        announcement.setPriceNegotiable(Boolean.parseBoolean(attributes[12]));
        announcement.setPriceAdditionComment(attributes[13]);
        announcement.setDate(LocalDateTime.parse(attributes[14]));
        //TODO: change code below after adding clientId assigning functionality
        announcement.setClientId(attributes[15].equals("null") ? null : Integer.parseInt(attributes[15]));
        announcement.setHeader(attributes[16]);
        return announcement;
    }


    public OfferType getOfferType() {
        return offerType;
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

    public void setOfferType(OfferType offerType) {
        this.offerType = offerType;
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

