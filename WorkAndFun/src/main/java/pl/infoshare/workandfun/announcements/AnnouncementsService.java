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
import pl.infoshare.workandfun.announcements.dto.AddAndEditDto;
import pl.infoshare.workandfun.announcements.mappers.AddAndEditMapper;
import pl.infoshare.workandfun.exception.AnnouncementNotFoundException;
import java.util.List;

@Service
public class AnnouncementsService {

    private final AnnouncementsRepository announcementsRepository;
    private final AddAndEditMapper addAndEditMapper;

    @Autowired
    public AnnouncementsService(AnnouncementsRepository announcementsRepository, AddAndEditMapper addAndEditMapper) {
        this.announcementsRepository = announcementsRepository;
        this.addAndEditMapper = addAndEditMapper;
    }

    public Iterable<Announcement> findAllSortedByCreateDateDesc() {
        return announcementsRepository.findAllByOrderByDateDesc();
    }

    public List<Announcement> findAllByQuerySpec(AnnouncementSpec announcementSpec){
        return announcementsRepository.findAll(announcementSpec);
    }

    public Announcement findById(Long id) {
        return announcementsRepository.findById(id).orElseThrow(() -> new AnnouncementNotFoundException(id));
    }

    public AddAndEditDto findByIdConvertToDto(Long id) {
        return addAndEditMapper.toDto(announcementsRepository.findById(id).orElseThrow(() ->new AnnouncementNotFoundException(id)));
    }

    public void deleteById(Long id) {
        announcementsRepository.findById(id)
                .ifPresentOrElse(announcementsRepository::delete, () -> { throw new AnnouncementNotFoundException(id); });
    }

    public void save(AddAndEditDto dto) {
        Announcement announcement = addAndEditMapper.toEntity(dto);
        announcementsRepository.save(announcement);
    }

    public Announcement update(Long id, AddAndEditDto dto) {
        Announcement entity = findById(id);
        BeanUtils.copyProperties(dto, entity, "date");
        return announcementsRepository.save(entity);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        announcementsRepository.save(new Announcement(1L, Type.SERVICE_OFFER, "Wyprowadzam psy, koty, myszy, konie, słonie",
                ServiceType.INNE, "Warszawa","","", "200", null, Voivodeship.MAZOWIECKIE, null,"Andrzej",
                "andrzej@aa.pl", false, "Andrzej, czyli ja to miłośnik zwierząt chętnie spędzający z nimi czas, nie masz" +
                " co zrobić ze swoim zwierzakiem, zadzwoń do Andrzeja", "+48666666666", "z FV będzie drożej"));
    }
}
