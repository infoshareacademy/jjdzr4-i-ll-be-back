package pl.infoshare.workandfun.announcements.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.ServiceType;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.Type;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.Voivodeship;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddAndEditAnnouncementDto {
    private Long id;
    @Enumerated(EnumType.STRING)
    private Type type;
    @NotBlank
    @Size(min = 10,max = 60)
    private String header;
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;
    @Size(min = 2, max = 35)
    @NotBlank(message = "białe znaki nie zadziałają")
    @Pattern(regexp = "\\D+", message = "Proszę podać Miasto!")
    private String city;
    @Pattern(regexp = "\\D*", message = "Proszę podać Dzielnicę!")
    private String cityDistrict;
    @Pattern(regexp = "\\D*", message = "Proszę podać Osiedle!")
    private String unit; //osiedle
    @NotBlank
    @Pattern(regexp = "(do ustalenia indywidualnie)|[0-9]{1,9}",message = "Musisz wpisać 'do ustalenia indywidualnie' lub liczbę mniejszą od miliarda")
    private String price;
    @Enumerated(EnumType.STRING)
    private Voivodeship voivodeship;
    @NotBlank
    @Size(min = 2, max = 35)
    @Pattern(regexp="^[A-Za-z]*$",message = "Musisz podać Imie!")
    private String nameOfAdvertiser;
    @Email
    private String email;
    @NotNull
    private Boolean isPriceNegotiable = false;
    @Size (min = 30, max=500)
    @NotBlank
    private String description;
    @Pattern(regexp = "(\\+48)?\\d{9}", message = "Musisz podać numer w formacie +48123456789")
    private String phoneNumber;
    @Pattern(regexp = ".*")
    private String priceAdditionComment = "";
    private LocalDateTime date;
}