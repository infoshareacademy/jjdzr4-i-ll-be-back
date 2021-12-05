package pl.infoshare.workandfun.announcement_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import pl.infoshare.workandfun.announcement_repository.entity.Announcement;
import pl.infoshare.workandfun.config.Paths;
import pl.infoshare.workandfun.util.FileActions;

@Service
public class AnnouncementsRepositoryManager {

    private Paths paths;
    private FileActions<Announcement> fileActions;
    private AnnouncementsRepository announcementsRepository;

    @Autowired
    public AnnouncementsRepositoryManager(Paths paths, FileActions<Announcement> fileActions, AnnouncementsRepository announcementsRepository) {
        this.paths = paths;
        this.fileActions = fileActions;
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

//    @EventListener(ApplicationReadyEvent.class)
//    public void initializeDB(){
//        announcementsRepository.saveAll(fileActions.readObjectListFromBase(paths.getAnnouncementPath(), Announcement.class));
//    }

}


