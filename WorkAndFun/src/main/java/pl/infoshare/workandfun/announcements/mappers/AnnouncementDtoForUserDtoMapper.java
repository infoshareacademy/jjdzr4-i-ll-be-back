package pl.infoshare.workandfun.announcements.mappers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;
import pl.infoshare.workandfun.announcements.dto.AnnouncementDtoForUserDto;

@Component
public class AnnouncementDtoForUserDtoMapper {

    private static final Logger LOGGER = LogManager.getLogger(AnnouncementDtoForUserDtoMapper.class);

    public Announcement toEntity(AnnouncementDtoForUserDto dto) {
        LOGGER.trace("Converting DTO to entity");
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
        entity.setId(dto.getId());
        LOGGER.trace("Conversion successfull");
        return entity;
    }

    public AnnouncementDtoForUserDto toDto(Announcement entity) {
        LOGGER.trace("Converting entity to DTO");
        AnnouncementDtoForUserDto dto = new AnnouncementDtoForUserDto();
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
        dto.setId(entity.getId());
        LOGGER.trace("Conversion successfull");
        return dto;
    }
}
