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
    @NotEmpty
    @Size(min = 10,max = 60)
    private String header;
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;
    @NotEmpty
    @Size(min = 2, max = 35)
    private String city;
    @Size(max = 50)
    private String cityDistrict;
    @Size(max = 50)
    private String unit; //osiedle
    private String price;
    private Integer clientId;
    @Enumerated(EnumType.STRING)
    private Voivodeship voivodeship;
    @CreationTimestamp
    private LocalDateTime date;
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
}
