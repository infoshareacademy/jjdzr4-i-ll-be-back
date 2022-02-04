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
    @NotBlank(message = "Nie może być spacją")
    @Size(min = 10,max = 60,message = "Nagłówek musi mieścić się w zakresie od 10 do 60 znaków")
    private String header;
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;
    @Size(min = 2, max = 35,message = "Długość nazwy miasta musi mieścić się w zakresie od 2 do 35 liter")
    @NotBlank(message = "Nie może być spacją")
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
    @Size(min = 2, max = 35, message = "Długość imienia musi mieścić się w zakresie od 2 do 35 liter")
    @Pattern(regexp="^[\\w]*$",message = "Musisz podać Imie!")
    private String nameOfAdvertiser;
    @Email
    @NotBlank(message = "Nie może być spacją")
    @NotEmpty
    private String email;
    @NotNull
    private Boolean isPriceNegotiable = false;
    @Size (min = 15, max=500)
    @NotBlank
    private String description;
    @Pattern(regexp = "(\\+48)?\\d{9}", message = "Musisz podać numer w formacie +48123456789")
    private String phoneNumber;
    @Pattern(regexp = ".*")
    private String priceAdditionComment = "";
    private LocalDateTime date;
}