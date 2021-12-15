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
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Announcement implements Comparable<Announcement> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String header;
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;
    private String city;
    private String cityDistrict;
    private String unit; //osiedle
    private String price;
    private Integer clientId;
    @Enumerated(EnumType.STRING)
    private Voivodeship voivodeship;
    @CreationTimestamp
    private LocalDateTime date;
    private String nameOfAdvertiser;
    private String email;
    private Boolean isPriceNegotiable = false;
    private String description;
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return getDate().format(formatter);
        } else if (now.getMonthValue() != getDate().getMonthValue() || now.getDayOfMonth() - getDate().getDayOfMonth() > 1) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM");
            return getDate().format(formatter);
        } else if ((now.getDayOfMonth() - getDate().getDayOfMonth()) > 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            return "wczoraj " + getDate().format(formatter);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            return "dzisiaj " + getDate().format(formatter);
        }
    }
}
