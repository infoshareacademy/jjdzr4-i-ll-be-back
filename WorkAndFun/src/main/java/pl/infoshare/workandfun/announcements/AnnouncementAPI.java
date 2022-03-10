package pl.infoshare.workandfun.announcements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.infoshare.workandfun.announcements.announcement_repo.AnnouncementSpec;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;
import java.util.List;

@RestController
@RequestMapping("")
public class AnnouncementAPI {

    private final AnnouncementService announcementService;
    private static final Logger LOGGER = LogManager.getLogger(AnnouncementAPI.class);

    @Autowired
    public AnnouncementAPI(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping("search-announcement")
    public ResponseEntity<List<Announcement>> findAllByQuerySpec(AnnouncementSpec announcementSpec) {
        LOGGER.debug("API request to find all announcemets");
        return ResponseEntity.ok(announcementService.findAllByQuerySpec(announcementSpec));
    }

    @GetMapping("search/{id}")
    public ResponseEntity<Announcement> findById(@PathVariable Long id) {
        LOGGER.debug("API request to search by id: {}", id);
        return ResponseEntity.ok(announcementService.findById(id));
    }

    @DeleteMapping("delete-announcement/{id}")
    public void deleteById(@PathVariable Long id) {
        LOGGER.debug("API request to delete by id: {}", id);
        announcementService.deleteById(id);
    }
}