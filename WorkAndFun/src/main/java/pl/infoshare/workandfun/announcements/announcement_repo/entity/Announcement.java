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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = Announcement.TABLE_NAME)
public class Announcement implements Comparable<Announcement> {

    public static final String TABLE_NAME = "announcement";
    public static final String COLUMN_PREFIX = "a_";
    public static final String INDIVIDUAL_PRICE_KEY = "do ustalenia indywidualnie";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_PREFIX + "id", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = COLUMN_PREFIX + "type", nullable = false)
    private Type type;
    @Column(name = COLUMN_PREFIX + "header", nullable = false)
    private String header;
    @Enumerated(EnumType.STRING)
    @Column(name = COLUMN_PREFIX + "service_type", nullable = false)
    private ServiceType serviceType;
    @Column(name = COLUMN_PREFIX + "city", nullable = false)
    private String city;
    @Column(name = COLUMN_PREFIX + "city_district")
    private String cityDistrict;
    @Column(name = COLUMN_PREFIX + "unit")
    private String unit; //osiedle
    @Column(name = COLUMN_PREFIX + "price")
    private String price;
    @Column(name = COLUMN_PREFIX + "client_id")
    private Integer clientId;
    @Enumerated(EnumType.STRING)
    @Column(name = COLUMN_PREFIX + "voivodeship", nullable = false)
    private Voivodeship voivodeship;
    @CreationTimestamp
    @Column(name = COLUMN_PREFIX + "date")
    private LocalDateTime date;
    @Column(name = COLUMN_PREFIX + "name_of_advertiser")
    private String nameOfAdvertiser;
    @Column(name = COLUMN_PREFIX + "email", nullable = false)
    private String email;
    @Column(name = COLUMN_PREFIX + "is_price_negotiable", nullable = false)
    private Boolean isPriceNegotiable = false;
    @Column(name = COLUMN_PREFIX + "description")
    private String description;
    @Column(name = COLUMN_PREFIX + "phone_number")
    private String phoneNumber;
    @Column(name = COLUMN_PREFIX + "price_addition_comment")
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