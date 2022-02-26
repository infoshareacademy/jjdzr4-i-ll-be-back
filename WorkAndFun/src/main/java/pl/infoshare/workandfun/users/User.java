package pl.infoshare.workandfun.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.Voivodeship;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = User.TABLE_NAME)
public class User {

    public static final String TABLE_NAME = "user";
    public static final String COLUMN_PREFIX = "u_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_PREFIX + "id", nullable = false)
    private Long id;
    @Column(name = COLUMN_PREFIX + "first_name", nullable = false)
    private String firstName;
    @Column(name = COLUMN_PREFIX + "last_name", nullable = false)
    private String lastName;
    @Column(name = COLUMN_PREFIX + "username", nullable = false)
    private String username;
    @Column(name = COLUMN_PREFIX + "password", nullable = false)
    private String password;
    @Column(name = COLUMN_PREFIX + "phone_number")
    private String phoneNumber;
    @Column(name = COLUMN_PREFIX + "email", nullable = false)
    private String email;
    @Column(name = COLUMN_PREFIX + "age")
    private int age;
    @Column(name = COLUMN_PREFIX + "voivodeship", nullable = false)
    private Voivodeship voivodeship;
    @Column(name = COLUMN_PREFIX + "city", nullable = false)
    private String city;
    @Column(name = COLUMN_PREFIX + "city_district")
    private String cityDistrict;
}