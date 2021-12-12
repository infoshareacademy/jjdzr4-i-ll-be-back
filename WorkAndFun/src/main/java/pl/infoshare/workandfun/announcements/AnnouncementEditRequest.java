package pl.infoshare.workandfun.announcements;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.ServiceType;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.Type;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.Voivodeship;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnnouncementEditRequest {
    @Enumerated(EnumType.STRING)
    private Type type;
    @NotBlank
    private String header;
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;
    @Pattern(regexp = "\\D+")
    private String city;
    @Pattern(regexp = "\\D*")
    private String cityDistrict;
    @Pattern(regexp = "\\D*")
    private String unit; //osiedle
    @Pattern(regexp = "\\d+")
    private String price;
    @Enumerated(EnumType.STRING)
    private Voivodeship voivodeship;
    @Email
    private String email;
    @NotNull
    private Boolean isPriceNegotiable = false;
    @NotBlank
    private String description;
    @Pattern(regexp = "(\\+48)?\\d{9}")
    private String phoneNumber;
    @Pattern(regexp = ".*")
    private String priceAdditionComment = "";
}