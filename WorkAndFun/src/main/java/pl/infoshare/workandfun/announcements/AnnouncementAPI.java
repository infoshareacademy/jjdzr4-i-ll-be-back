package pl.infoshare.workandfun.announcements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;

@RestController
@RequestMapping("")
public class AnnouncementAPI {

    private AnnouncementsService announcementsService;

    @Autowired
    public AnnouncementAPI(AnnouncementsService announcementsService) {
        this.announcementsService = announcementsService;
    }

    @GetMapping
    public Iterable<Announcement> getAllAnnouncementsDateDesc() {
        return announcementsService.findAllSortedByCreateDateDesc();
    }

}
