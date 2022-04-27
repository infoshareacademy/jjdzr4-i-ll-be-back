package pl.infoshare.workandfun.users.mappers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.infoshare.workandfun.announcements.mappers.AnnouncementDtoForUserDtoMapper;
import pl.infoshare.workandfun.users.User;
import pl.infoshare.workandfun.users.userrole.UserRole;
import pl.infoshare.workandfun.users.dto.UserAddingDto;
import pl.infoshare.workandfun.users.dto.UserSecurityDto;

import java.util.stream.Collectors;

@Component
public class UserToUserDtosMapper {

    private static final Logger LOGGER = LogManager.getLogger(UserToUserDtosMapper.class);
    private final AnnouncementDtoForUserDtoMapper announcementDtoForUserDto;

    @Autowired
    public UserToUserDtosMapper(AnnouncementDtoForUserDtoMapper announcementDtoForUserDto) {
        this.announcementDtoForUserDto = announcementDtoForUserDto;
    }

    public UserSecurityDto toSecurityDto(User entity) {
        LOGGER.trace("Converting entity to DTO");
        UserSecurityDto dto = new UserSecurityDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setRoles(entity.getRoles().stream().map(UserRole::getName).collect(Collectors.toSet()));
        dto.setOwnAnnouncements(entity.getOwnAnnouncements().stream().map(announcementDtoForUserDto::toDto).collect(Collectors.toList()));
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setEmail(entity.getEmail());
        dto.setAge(entity.getAge());
        dto.setVoivodeship(entity.getVoivodeship());
        dto.setCity(entity.getCity());
        dto.setCityDistrict(entity.getCityDistrict());
        LOGGER.trace("Conversion successfull");
        return dto;
    }

    public User toEntity(UserAddingDto addingDto) {
        LOGGER.trace("Converting adding DTO to entity");
        User userEntity = new User();
        userEntity.setUsername(addingDto.getUsername());
        userEntity.setPassword(addingDto.getPassword());
        userEntity.setFirstName(addingDto.getFirstName());
        userEntity.setLastName(addingDto.getLastName());
        userEntity.setEmail(addingDto.getEmail());
        userEntity.setVoivodeship(addingDto.getVoivodeship());
        userEntity.setCity(addingDto.getCity());
        LOGGER.trace("Conversion successfull");
        return userEntity;
    }
}