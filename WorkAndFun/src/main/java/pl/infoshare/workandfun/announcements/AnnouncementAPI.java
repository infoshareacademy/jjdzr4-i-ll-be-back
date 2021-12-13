package pl.infoshare.workandfun.announcements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.infoshare.workandfun.announcements.announcement_repo.AnnouncementSpec;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;
import javax.validation.Valid;
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
    public ResponseEntity<Iterable<Announcement>> getAllAnnouncementsDateDesc() {
        return ResponseEntity.ok(announcementsService.findAllSortedByCreateDateDesc());
    }

    @GetMapping("search")
    public ResponseEntity<List<Announcement>> findAllByQuerySpec(AnnouncementSpec announcementSpec) {
        return ResponseEntity.ok(announcementsService.findAllByQuerySpec(announcementSpec));
    }

    @GetMapping("search/{id}")
    public ResponseEntity<Announcement> findById(@PathVariable Long id) {
        return ResponseEntity.ok(announcementsService.findById(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Announcement> update(@PathVariable Long id,
                                               @Valid @RequestBody  AnnouncementEditRequest announcementEditRequest) {
        return ResponseEntity.ok(announcementsService.update(id, announcementEditRequest));
    }

    @PostMapping("add-announcement")
    public ResponseEntity<Announcement> save(@RequestBody Announcement announcement) {
        return ResponseEntity.ok(announcementsService.save(announcement));
    }

    @DeleteMapping("delete-announcement/{id}")
    public void deleteById(@PathVariable Long id) {
        announcementsService.deleteById(id);
    }
}
