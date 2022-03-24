package pl.infoshare.workandfun.announcements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.infoshare.workandfun.announcements.announcement_repo.AnnouncementSpec;
import pl.infoshare.workandfun.announcements.announcement_repo.AnnouncementsRepository;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;
import pl.infoshare.workandfun.announcements.dto.AddAndEditAnnouncementDto;
import pl.infoshare.workandfun.announcements.dto.QuickViewAnnouncementDto;
import pl.infoshare.workandfun.announcements.mappers.AddAndEditMapper;
import pl.infoshare.workandfun.announcements.mappers.QuickViewAnnouncementMapper;
import pl.infoshare.workandfun.exception.AnnouncementNotFoundException;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AnnouncementService {

    private final AnnouncementsRepository announcementsRepository;
    private final AddAndEditMapper addAndEditMapper;
    private final QuickViewAnnouncementMapper quickViewAnnouncementMapper;
    private static final Logger LOGGER = LogManager.getLogger(AnnouncementService.class);

    @Autowired
    public AnnouncementService(AnnouncementsRepository announcementsRepository, AddAndEditMapper addAndEditMapper, QuickViewAnnouncementMapper quickViewAnnouncementMapper) {
        this.announcementsRepository = announcementsRepository;
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

    public Long save(AddAndEditAnnouncementDto dto) {
        Announcement announcement = addAndEditMapper.toEntity(dto);
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

    private Predicate<QuickViewAnnouncementDto> searchFilter(String param) {
        return quickViewAnnouncementDto -> quickViewAnnouncementDto.toString().toLowerCase().contains(param.toLowerCase());
    }
}