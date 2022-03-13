package pl.infoshare.workandfun.users.mappers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.infoshare.workandfun.announcements.mappers.AnnouncementDtoForUserDtoMapper;
import pl.infoshare.workandfun.users.User;
import pl.infoshare.workandfun.users.UserRole;
import pl.infoshare.workandfun.users.dto.UserDto;
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

    public UserDto toDto(UserSecurityDto userSecurityDto) {
        LOGGER.trace("Converting entity to DTO");
        UserDto userDto = new UserDto();
        userDto.setUsername(userSecurityDto.getUsername());
        userDto.setPassword(userSecurityDto.getPassword());
        userDto.setOwnAnnouncements(userSecurityDto.getOwnAnnouncements());
        userDto.setFirstName(userSecurityDto.getFirstName());
        userDto.setLastName(userSecurityDto.getLastName());
        userDto.setPhoneNumber(userSecurityDto.getPhoneNumber());
        userDto.setEmail(userSecurityDto.getEmail());
        userDto.setAge(userSecurityDto.getAge());
        userDto.setVoivodeship(userSecurityDto.getVoivodeship());
        userDto.setCity(userSecurityDto.getCity());
        userDto.setCityDistrict(userSecurityDto.getCityDistrict());
        LOGGER.trace("Conversion successfull");
        return userDto;
    }
}
