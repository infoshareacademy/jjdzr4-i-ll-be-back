package pl.infoshare.workandfun.announcements.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class QuickViewAnnouncementDto {
    private Long id;
    private String header;
    private String price;
    private Boolean isPriceNegotiable = false;
    private LocalDateTime date;
    private boolean isIndividualPrice;
    private String fullLocalization;
}
