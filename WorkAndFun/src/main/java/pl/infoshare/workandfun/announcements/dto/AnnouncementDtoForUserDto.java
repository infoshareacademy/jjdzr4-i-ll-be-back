package pl.infoshare.workandfun.announcements.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.ServiceType;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.Type;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.Voivodeship;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AnnouncementDtoForUserDto {
    private Long id;
    private Type type;
    private String header;
    private ServiceType serviceType;
    private String city;
    private String cityDistrict;
    private String unit;
    private String price;
    private Voivodeship voivodeship;
    private LocalDateTime date;
    private String email;
    private Boolean isPriceNegotiable;
    private String description;
    private String phoneNumber;
    private String priceAdditionComment;
}