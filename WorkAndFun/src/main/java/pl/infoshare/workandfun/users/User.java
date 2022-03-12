package pl.infoshare.workandfun.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.Voivodeship;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    @Column(name = COLUMN_PREFIX + "username", nullable = false, unique = true)
    private String username;
    @Column(name = COLUMN_PREFIX + "password", nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<UserRole> roles;
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Announcement> ownAnnouncements;
    @Column(name = COLUMN_PREFIX + "first_name", nullable = false)
    private String firstName;
    @Column(name = COLUMN_PREFIX + "last_name", nullable = false)
    private String lastName;
    @Column(name = COLUMN_PREFIX + "phone_number")
    private String phoneNumber;
    @Column(name = COLUMN_PREFIX + "email", nullable = false, unique = true)
    private String email;
    @Column(name = COLUMN_PREFIX + "age")
    private int age;
    @Enumerated(EnumType.STRING)
    @Column(name = COLUMN_PREFIX + "voivodeship", nullable = false)
    private Voivodeship voivodeship;
    @Column(name = COLUMN_PREFIX + "city", nullable = false)
    private String city;
    @Column(name = COLUMN_PREFIX + "city_district")
    private String cityDistrict;

    public User(String username, String password, Set<UserRole> roles, List<Announcement> ownAnnouncements, String firstName, String lastName, String phoneNumber, String email, int age, Voivodeship voivodeship, String city, String cityDistrict) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.ownAnnouncements = ownAnnouncements;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
        this.voivodeship = voivodeship;
        this.city = city;
        this.cityDistrict = cityDistrict;
    }
}