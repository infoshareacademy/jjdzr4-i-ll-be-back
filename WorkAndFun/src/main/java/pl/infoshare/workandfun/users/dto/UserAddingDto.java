package pl.infoshare.workandfun.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.Voivodeship;

@Getter
@Setter
@NoArgsConstructor
public class UserAddingDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Voivodeship voivodeship;
    private String city;
}
