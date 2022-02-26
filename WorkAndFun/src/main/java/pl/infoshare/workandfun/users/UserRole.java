package pl.infoshare.workandfun.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = UserRole.TABLE_NAME)
public class UserRole {

    public static final String TABLE_NAME = "roles";
    public static final String COLUMN_PREFIX = "r_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_PREFIX + "id", nullable = false)
    private Long id;
    @Column(name = COLUMN_PREFIX + "name", nullable = false)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> user;
}