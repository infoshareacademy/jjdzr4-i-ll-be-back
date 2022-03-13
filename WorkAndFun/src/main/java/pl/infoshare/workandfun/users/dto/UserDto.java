package pl.infoshare.workandfun.users.dto;

import lombok.Getter;
import lombok.Setter;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.Voivodeship;
import pl.infoshare.workandfun.announcements.dto.AnnouncementDtoForUserDto;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String username;
    private String password;
    private List<AnnouncementDtoForUserDto> ownAnnouncements;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private int age;
    private Voivodeship voivodeship;
    private String city;
    private String cityDistrict;

}
