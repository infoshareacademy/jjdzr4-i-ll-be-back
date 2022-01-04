package pl.infoshare.workandfun.announcements.mappers;

import org.springframework.stereotype.Component;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;
import pl.infoshare.workandfun.announcements.dto.AddAndEditAnnouncementDto;

@Component
public class AddAndEditMapper {
    public Announcement toEntity(AddAndEditAnnouncementDto dto) {
        Announcement entity = new Announcement();
        entity.setType(dto.getType());
        entity.setHeader(dto.getHeader());
        entity.setDescription(dto.getDescription());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setEmail(dto.getEmail());
        entity.setVoivodeship(dto.getVoivodeship());
        entity.setPrice(dto.getPrice());
        entity.setUnit(dto.getUnit());
        entity.setCityDistrict(dto.getCityDistrict());
        entity.setCity(dto.getCity());
        entity.setIsPriceNegotiable(dto.getIsPriceNegotiable());
        entity.setServiceType(dto.getServiceType());
        entity.setPriceAdditionComment(dto.getPriceAdditionComment());
        entity.setNameOfAdvertiser(dto.getNameOfAdvertiser());
        entity.setId(dto.getId());
        return entity;
    }

    public AddAndEditAnnouncementDto toDto(Announcement entity) {
        AddAndEditAnnouncementDto dto = new AddAndEditAnnouncementDto();
        dto.setCity(entity.getCity());
        dto.setDescription(entity.getDescription());
        dto.setEmail(entity.getEmail());
        dto.setHeader(entity.getHeader());
        dto.setCityDistrict(entity.getCityDistrict());
        dto.setType(entity.getType());
        dto.setServiceType(entity.getServiceType());
        dto.setUnit(entity.getUnit());
        dto.setVoivodeship(entity.getVoivodeship());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setIsPriceNegotiable(entity.getIsPriceNegotiable());
        dto.setPrice(entity.getPrice());
        dto.setPriceAdditionComment(entity.getPriceAdditionComment());
        dto.setNameOfAdvertiser(entity.getNameOfAdvertiser());
        dto.setId(entity.getId());
        return dto;
    }
}
