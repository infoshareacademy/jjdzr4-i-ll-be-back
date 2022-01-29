package pl.infoshare.workandfun.mainpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.infoshare.workandfun.announcements.AnnouncementService;
import pl.infoshare.workandfun.announcements.dto.QuickViewAnnouncementService;

@Controller
public class Index {

    private final AnnouncementService announcementService;
    private final QuickViewAnnouncementService quickViewAnnouncementService;

    @Autowired
    public Index(AnnouncementService announcementService, QuickViewAnnouncementService quickViewAnnouncementService) {
        this.announcementService = announcementService;
        this.quickViewAnnouncementService = quickViewAnnouncementService;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("announcements", announcementService.findAllSortedByCreateDateDescConvertToDto());
        model.addAttribute("service", quickViewAnnouncementService);
        return "index";
    }
}