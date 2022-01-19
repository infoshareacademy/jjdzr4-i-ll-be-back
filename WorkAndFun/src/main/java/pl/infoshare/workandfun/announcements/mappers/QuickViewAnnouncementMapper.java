package pl.infoshare.workandfun.announcements.mappers;

import org.springframework.stereotype.Component;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;
import pl.infoshare.workandfun.announcements.dto.QuickViewAnnouncementDto;

@Component
public class QuickViewAnnouncementMapper {

    public static final String INDIVIDUAL_PRICE_KEY_WORD = "do ustalenia indywidualnie";

    public QuickViewAnnouncementDto toDto(Announcement entity) {
        QuickViewAnnouncementDto dto = new QuickViewAnnouncementDto();
        dto.setId(entity.getId());
        dto.setHeader(entity.getHeader());
        dto.setIsPriceNegotiable(entity.getIsPriceNegotiable());
        dto.setPrice(entity.getPrice());
        dto.setDate(entity.getDate());
        dto.setIndividualPrice(checkIsPriceIndividual(entity));
        dto.setFullLocalization(setFullLocalization(entity));
        dto.setDescription(entity.getDescription());
        return dto;
    }

    private boolean checkIsPriceIndividual(Announcement entity) {
        return entity.getPrice().equalsIgnoreCase(INDIVIDUAL_PRICE_KEY_WORD);
    }

    private String setFullLocalization(Announcement entity) {
        StringBuilder sb = new StringBuilder();
        sb.append(entity.getCity());
        if (!(entity.getCityDistrict().isEmpty() || entity.getCityDistrict().isBlank())){
            sb.append(", ").append(entity.getCityDistrict());
        }
        if (!(entity.getUnit().isEmpty() || entity.getUnit().isBlank())){
            sb.append(", ").append(entity.getUnit());
        }
        return sb.toString();
    }
}
