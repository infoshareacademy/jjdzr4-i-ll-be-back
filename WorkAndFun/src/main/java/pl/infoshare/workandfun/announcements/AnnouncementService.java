package pl.infoshare.workandfun.announcements;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.infoshare.workandfun.announcements.announcement_repo.AnnouncementSpec;
import pl.infoshare.workandfun.announcements.announcement_repo.AnnouncementsRepository;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.ServiceType;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.Type;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.Voivodeship;
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

    @Autowired
    public AnnouncementService(AnnouncementsRepository announcementsRepository, AddAndEditMapper addAndEditMapper, QuickViewAnnouncementMapper quickViewAnnouncementMapper) {
        this.announcementsRepository = announcementsRepository;
        this.addAndEditMapper = addAndEditMapper;
        this.quickViewAnnouncementMapper = quickViewAnnouncementMapper;
    }

    public Iterable<QuickViewAnnouncementDto> findAllSortedByCreateDateDescConvertToDto() {
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
        return announcementsRepository.findById(id).orElseThrow(() -> new AnnouncementNotFoundException(id));
    }

    public AddAndEditAnnouncementDto findByIdConvertToDto(Long id) {
        return addAndEditMapper.toDto(announcementsRepository.findById(id).orElseThrow(() -> new AnnouncementNotFoundException(id)));
    }

    public void deleteById(Long id) {
        announcementsRepository.findById(id)
                .ifPresentOrElse(announcementsRepository::delete, () -> {
                    throw new AnnouncementNotFoundException(id);
                });
    }

    public void save(AddAndEditAnnouncementDto dto) {
        Announcement announcement = addAndEditMapper.toEntity(dto);
        announcementsRepository.save(announcement);
    }

    public Announcement update(Long id, AddAndEditAnnouncementDto dto) {
        Announcement entity = findById(id);
        BeanUtils.copyProperties(dto, entity, "date");
        return announcementsRepository.save(entity);
    }

    public Iterable<QuickViewAnnouncementDto> searchAllByParameter(String parameter) {
        List<QuickViewAnnouncementDto> announcementDtoList = (List<QuickViewAnnouncementDto>) findAllSortedByCreateDateDescConvertToDto();
        if (parameter.isBlank()) {
            return announcementDtoList;
        } else {
            return announcementDtoList.stream()
                    .filter(searchFilter(parameter)).collect(Collectors.<QuickViewAnnouncementDto>toList());
        }
    }

    private Predicate<QuickViewAnnouncementDto> searchFilter(String param) {
        return quickViewAnnouncementDto -> quickViewAnnouncementDto.toString().toLowerCase().contains(param.toLowerCase());
    }
}