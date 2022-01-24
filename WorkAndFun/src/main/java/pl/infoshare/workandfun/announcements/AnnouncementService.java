package pl.infoshare.workandfun.announcements;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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

    public List<Announcement> findAllByQuerySpec(AnnouncementSpec announcementSpec){
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
                .ifPresentOrElse(announcementsRepository::delete, () -> { throw new AnnouncementNotFoundException(id); });
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

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        announcementsRepository.save(new Announcement(1L, Type.SERVICE_OFFER, "Wyprowadzam psy, koty, myszy, konie, słonie",
                ServiceType.INNE, "Warszawa","","", "200", null, Voivodeship.MAZOWIECKIE, null,"Andrzej",
                "andrzej@aa.pl", false, "Andrzej, czyli ja to miłośnik zwierząt chętnie spędzający z nimi czas, nie masz" +
                " co zrobić ze swoim zwierzakiem, zadzwoń do Andrzeja", "+48666666666", "z FV będzie drożej"));
        announcementsRepository.save(new Announcement(2L, Type.SERVICE_OFFER, "Zbuduję Ci chatę",
                ServiceType.BUDOWA_DOMU_BUDOWA_OD_PODSTAW, "Wrocław","","", "50000", null, Voivodeship.DOLNOSLASKIE, null,"Piotrek",
                "piotrek@b.pl", true, "Nie masz chaty? Zbuduję! Mach chatę? Zbuduję Ci nową! Nie krępuj się i dzwoń śmiało!"
                , "+48777777777", ""));
        announcementsRepository.save(new Announcement(3L, Type.SERVICE_OFFER, "Profesjonalne obieranie cebuli",
                ServiceType.INNE, "Gdańsk","","", "10", null, Voivodeship.POMORSKIE, null,"Polcebulex",
                "polcebulex@wp.pl", false, "1kg obranej cebuli za 10gr. Czas realizacji na zamówienia do pół tony to 5 dni roboczych." +
                "Zapraszamy do składania zamówień w wiodącej firmie obierającej cebulę w regionie pomorskim.", "+48888888888", ""));
    }
}
