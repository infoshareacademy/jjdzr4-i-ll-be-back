package pl.infoshare.workandfun.announcements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;

import javax.validation.Valid;
@Controller
public class AnnouncementController {

    private final AnnouncementsService announcementsService;

    @Autowired
    public AnnouncementController(AnnouncementsService announcementsService) {
        this.announcementsService = announcementsService;
    }

    @GetMapping("add-announcement")
    public String announcementForm(Model model){
        model.addAttribute("announcement", new Announcement());
        return "announcement-form";
    }

    @PostMapping("add-announcement")
    public String save(@Valid @ModelAttribute("announcement") @RequestBody Announcement announcement,
                       BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "announcement-form";
        }
        ResponseEntity.ok(announcementsService.save(announcement));
        return "announcement-form-success";
    }
}
