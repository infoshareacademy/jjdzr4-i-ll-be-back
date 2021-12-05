package pl.infoshare.workandfun.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.infoshare.workandfun.announcement_repository.AnnouncementsRepositoryManager;
import pl.infoshare.workandfun.announcement_repository.entity.Announcement;

@RestController
@RequestMapping("")
public class TestController {

    private AnnouncementsRepositoryManager announcementsRepositoryManager;

    @Autowired
    public TestController(AnnouncementsRepositoryManager announcementsRepositoryManager) {
        this.announcementsRepositoryManager = announcementsRepositoryManager;
    }

    @GetMapping("qwe")
    public Iterable<Announcement> getAllAnnouncementsDateDesc() {
        return announcementsRepositoryManager.findAllSortedByCreateDateDesc();
    }
}
