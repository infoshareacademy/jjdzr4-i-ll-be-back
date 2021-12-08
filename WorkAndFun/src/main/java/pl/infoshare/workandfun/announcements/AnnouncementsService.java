package pl.infoshare.workandfun.announcements;

import org.springframework.stereotype.Service;
import pl.infoshare.workandfun.announcements.announcement_repo.AnnouncementsRepository;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;

@Service
public class AnnouncementsService {

    private final AnnouncementsRepository announcementsRepository;

    public AnnouncementsService(AnnouncementsRepository announcementsRepository) {
        this.announcementsRepository = announcementsRepository;
    }

    public Iterable<Announcement> findAll() {
        return announcementsRepository.findAll();
    }

    public Iterable<Announcement> findAllSortedByCreateDateAsc() {
        return announcementsRepository.findAllByOrderByDateAsc();
    }

    public Iterable<Announcement> findAllSortedByCreateDateDesc() {
        return announcementsRepository.findAllByOrderByDateDesc();
    }
}


