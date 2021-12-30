package pl.infoshare.workandfun.announcements.announcement_repo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.ServiceType;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.Type;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.Voivodeship;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Announcement implements Comparable<Announcement> {

    public static final String INDIVIDUAL_PRICE_KEY = "do ustalenia indywidualnie";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Type type;
    @NotEmpty
    @Size(min = 10,max = 60)
    private String header;
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType; //TODO
    @NotEmpty
    @Size(min = 2, max = 35)
    @Pattern(regexp="^[A-Za-z]*$",message = "Proszę podać Miasto!")
    private String city;
    @Size(max = 50)
    @Pattern(regexp="^[A-Za-z]*$",message = "Proszę podać dzielnicę!")
    private String cityDistrict;
    @Pattern(regexp="^[A-Za-z]*$",message = "Proszę podać osiedle!")
    @Size(max = 50)
    private String unit; //osiedle
    @NotEmpty
    @Pattern(regexp = "(?=^\\s*do ustalenia indywidualnie).{26}|[0-9]{1,10}",message = "Musisz wpisać 'do ustalenia indywidualnie' lub liczbę mniejszą od miliarda")
    private String price;   //TODO
    private Integer clientId;
    @Enumerated(EnumType.STRING)
    private Voivodeship voivodeship;
    @CreationTimestamp
    private LocalDateTime date;
    @NotEmpty
    @Size(min = 2, max = 35)
    @Pattern(regexp="^[A-Za-z]*$",message = "Musisz podać Imie!")
    private String nameOfAdvertiser;
    @Email
    private String email;
    private Boolean isPriceNegotiable = false;
    @Size (min = 30, max=500)
    private String description;
    @Pattern(regexp = "(\\+48|0)[0-9]{9}", message = "Musisz podać numer w formacie +48123456789")
    private String phoneNumber;
    private String priceAdditionComment = "";

    @Override
    public int compareTo(Announcement o) {
        if (!this.date.isEqual(o.date)) {
            Duration duration = Duration.between(o.date, this.date);
            return Integer.parseInt(String.valueOf(duration.getSeconds()));
        } else {
            return Integer.parseInt(String.valueOf(o.id - this.id));
        }
    }

    public String getFullLocalization(){
        StringBuilder sb = new StringBuilder();
        sb.append(getCity());
        if (!(getCityDistrict().isEmpty() || getCityDistrict().isBlank())){
            sb.append(", " + getCityDistrict());
        }
        if (!(getUnit().isEmpty() || getUnit().isBlank())){
            sb.append(", " + getUnit());
        }
        return sb.toString();
    }

    public String announcementCreationDateFormatted(){
        LocalDateTime now = LocalDateTime.now();
        if (now.getYear() != getDate().getYear()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.forLanguageTag("PL"));
            return getDate().format(formatter);
        } else if (now.getMonthValue() != getDate().getMonthValue() || now.getDayOfMonth() - getDate().getDayOfMonth() > 1) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM", Locale.forLanguageTag("PL"));
            return getDate().format(formatter);
        } else if ((now.getDayOfMonth() - getDate().getDayOfMonth()) > 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.forLanguageTag("PL"));
            return "wczoraj " + getDate().format(formatter);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.forLanguageTag("PL"));
            return "dzisiaj " + getDate().format(formatter);
        }
    }

    public boolean isIndividualPrice(){
        return this.price.toLowerCase(Locale.ROOT).equals(INDIVIDUAL_PRICE_KEY.toLowerCase(Locale.ROOT));
    }
}
