package pl.infoshare.workandfun.announcements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.infoshare.workandfun.announcements.announcement_repo.AnnouncementSpec;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;

import java.util.List;

@RestController
@RequestMapping("")
public class AnnouncementAPI {

    private final AnnouncementsService announcementsService;

    @Autowired
    public AnnouncementAPI(AnnouncementsService announcementsService) {
        this.announcementsService = announcementsService;
    }

    @GetMapping
    public ModelAndView getAllAnnouncementsDateDesc() {
        ModelAndView modelAndView = new ModelAndView("announcementsList");
        modelAndView.addObject("announcements", announcementsService.findAllSortedByCreateDateDesc());
        return modelAndView;
    }

    @GetMapping("search")
    public ResponseEntity<List<Announcement>> findAllByQuerySpec(AnnouncementSpec announcementSpec) {
        return ResponseEntity.ok(announcementsService.findAllByQuerySpec(announcementSpec));
    }

    @GetMapping("search/{id}")
    public ResponseEntity<Announcement> findById(@PathVariable Long id) {
        return ResponseEntity.ok(announcementsService.findById(id));
    }

    @DeleteMapping("delete-announcement/{id}")
    public void deleteById(@PathVariable Long id) {
        announcementsService.deleteById(id);
    }
}
