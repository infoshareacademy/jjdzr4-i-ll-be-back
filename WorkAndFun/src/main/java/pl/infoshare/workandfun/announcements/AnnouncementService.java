package pl.infoshare.workandfun.announcements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.infoshare.workandfun.announcements.announcement_repo.AnnouncementSpec;
import pl.infoshare.workandfun.announcements.announcement_repo.AnnouncementsRepository;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.ServiceType;
import pl.infoshare.workandfun.announcements.dto.AddAndEditAnnouncementDto;
import pl.infoshare.workandfun.announcements.dto.QuickViewAnnouncementDto;
import pl.infoshare.workandfun.announcements.mappers.AddAndEditMapper;
import pl.infoshare.workandfun.announcements.mappers.QuickViewAnnouncementMapper;
import pl.infoshare.workandfun.exception.AnnouncementNotFoundException;
import pl.infoshare.workandfun.users.User;
import pl.infoshare.workandfun.users.UserRepository;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AnnouncementService {

    private final AnnouncementsRepository announcementsRepository;
    private final UserRepository userRepository;
    private final AddAndEditMapper addAndEditMapper;
    private final QuickViewAnnouncementMapper quickViewAnnouncementMapper;
    private static final Logger LOGGER = LogManager.getLogger(AnnouncementService.class);

    @Autowired
    public AnnouncementService(AnnouncementsRepository announcementsRepository, UserRepository userRepository, AddAndEditMapper addAndEditMapper, QuickViewAnnouncementMapper quickViewAnnouncementMapper) {
        this.announcementsRepository = announcementsRepository;
        this.userRepository = userRepository;
        this.addAndEditMapper = addAndEditMapper;
        this.quickViewAnnouncementMapper = quickViewAnnouncementMapper;
    }

    public Iterable<QuickViewAnnouncementDto> findAllSortedByCreateDateDescConvertToDto() {
        LOGGER.debug("Request for all announcements sorted by create date");
        List<Announcement> announcements = (List<Announcement>) announcementsRepository.findAllByOrderByDateDesc();
        return announcements
                .stream()
                .map(quickViewAnnouncementMapper::toDto)
                .collect(Collectors.<QuickViewAnnouncementDto>toList());
    }

    public Page<QuickViewAnnouncementDto> findAllSortedByCreateDateDescConvertToDto(Integer page, Integer size) {
        LOGGER.debug("Request for all announcements sorted by create date");
        PageRequest pr = PageRequest.of(page, size);
        Page<Announcement> announcements = announcementsRepository.findAllByOrderByDateDesc(pr);
        return announcements
                .map(quickViewAnnouncementMapper::toDto);
    }

    public List<Announcement> findAllByQuerySpec(AnnouncementSpec announcementSpec) {
        return announcementsRepository.findAll(announcementSpec);
    }

    public Announcement findById(Long id) {
        LOGGER.debug("Repository request to find by id: {}", id);
        return announcementsRepository.findById(id).orElseThrow(() -> new AnnouncementNotFoundException(id));
    }

    public AddAndEditAnnouncementDto findByIdConvertToDto(Long id) {
        LOGGER.debug("Repository request to find by id and convert to dto, id: {}", id);
        return addAndEditMapper.toDto(announcementsRepository.findById(id).orElseThrow(() -> new AnnouncementNotFoundException(id)));
    }

    public void deleteById(Long id) {
        LOGGER.debug("Repository request to delete by id: {}", id);
        announcementsRepository.findById(id)
                .ifPresentOrElse(announcementsRepository::delete, () -> {
                    throw new AnnouncementNotFoundException(id);
                });
        LOGGER.debug("Deleted announcement, id: {}", id);
    }

    public Long save(AddAndEditAnnouncementDto dto, String username) {
        Announcement announcement = addAndEditMapper.toEntity(dto);
        User foundUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User: " + username + " does not exist"));
        announcement.setOwner(foundUser);
        announcementsRepository.save(announcement);
        LOGGER.info("Announcement successfully saved to database (id: {})", announcement.getId());
        return announcement.getId();
    }

    public Announcement update(Long id, AddAndEditAnnouncementDto dto) {
        Announcement entity = findById(id);
        BeanUtils.copyProperties(dto, entity);
        LOGGER.info("Announcement successfully saved to database (id: {})", entity.getId());
        return announcementsRepository.save(entity);
    }

    public Iterable<QuickViewAnnouncementDto> searchAllByParameter(String parameter) {
        List<QuickViewAnnouncementDto> announcementDtoList = (List<QuickViewAnnouncementDto>) findAllSortedByCreateDateDescConvertToDto();
        if (parameter.isBlank()) {
            LOGGER.debug("Returning all announcements list - query is blank");
            return announcementDtoList;
        } else {
            LOGGER.debug("Returning search list (query: {})", parameter);
            return announcementDtoList.stream()
                    .filter(searchFilter(parameter)).collect(Collectors.<QuickViewAnnouncementDto>toList());
        }
    }

    public Iterable<QuickViewAnnouncementDto> findAllByServiceType(String serviceType) throws IllegalArgumentException {
        List<Announcement> announcementList = (List<Announcement>) announcementsRepository.findAllByServiceTypeEquals(ServiceType.valueOf(serviceType.toUpperCase()));
        LOGGER.debug("Returning all announcements list by selected service type: {}", serviceType);
        return announcementList.stream()
                .map(quickViewAnnouncementMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<QuickViewAnnouncementDto> findAllByOwner(String username) throws UsernameNotFoundException {
        User foundUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User: " + username + " does not exist"));;
        List<Announcement> announcementsOfUser = announcementsRepository.findAllByOwnerOrderByDateDesc(foundUser);
        return announcementsOfUser.stream().map(quickViewAnnouncementMapper::toDto).collect(Collectors.toList());
    }

    private Predicate<QuickViewAnnouncementDto> searchFilter(String param) {
        return quickViewAnnouncementDto -> quickViewAnnouncementDto.toString().toLowerCase().contains(param.toLowerCase());
    }
}