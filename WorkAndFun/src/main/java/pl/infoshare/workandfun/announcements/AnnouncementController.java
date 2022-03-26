package pl.infoshare.workandfun.announcements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.infoshare.workandfun.announcements.dto.AddAndEditAnnouncementDto;
import pl.infoshare.workandfun.announcements.dto.QuickViewAnnouncementDto;
import pl.infoshare.workandfun.announcements.dto.QuickViewAnnouncementService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("announcement")
public class AnnouncementController {

    private final AnnouncementService announcementService;
    private final QuickViewAnnouncementService quickViewAnnouncementService;
    private static final Logger LOGGER = LogManager.getLogger(AnnouncementController.class);

    @Autowired
    public AnnouncementController(AnnouncementService announcementService, QuickViewAnnouncementService quickViewAnnouncementService) {
        this.announcementService = announcementService;
        this.quickViewAnnouncementService = quickViewAnnouncementService;
    }

    @GetMapping("details/{id}")
    public String getAnnouncementDetails(@PathVariable Long id,
                                         Model model) {
        LOGGER.info("Received request for details of announcement (id: {})", id);
        model.addAttribute("allDetails", announcementService.findByIdConvertToDto(id));
        model.addAttribute("service", quickViewAnnouncementService);
        LOGGER.info("Showing announcement (id: {})", id);
        return "announcement-details";
    }

    @GetMapping("all")
    public String getAllAnnouncementsDateDesc(Model model) {
        LOGGER.info("Received request for all announcements");
        model.addAttribute("announcements", announcementService.findAllSortedByCreateDateDescConvertToDto());
        model.addAttribute("service", quickViewAnnouncementService);
        LOGGER.info("Showing all announcements");
        return "all-announcements";
    }

    @GetMapping("add-new")
    public String announcementForm(Model model) {
        LOGGER.info("Received request for new announcement form");
        model.addAttribute("announcement", new AddAndEditAnnouncementDto());
        return "announcement-form";
    }

    @PostMapping("add-new")
    public String save(@Valid @ModelAttribute("announcement") AddAndEditAnnouncementDto addAndEditAnnouncementDto,
                       BindingResult bindingResult, HttpServletRequest request) {
        LOGGER.info("User tries to add new announcement");
        if (bindingResult.hasErrors()) {
            LOGGER.info("Announcement save failed due to incorrectly filled form");
            return "announcement-form";
        }
        LOGGER.info("Announcement form filled correctly, saving to database");
        announcementService.save(addAndEditAnnouncementDto, request.getUserPrincipal().getName());
        return "announcement-form-success";
    }

    @GetMapping("edit/{id}")
    public String announcementEditForm(Model model, @PathVariable Long id) {
        LOGGER.info("Received request for announcement edit form (announcement id: {})", id);
        model.addAttribute("announcement", announcementService.findByIdConvertToDto(id));
        return "announcement-form-update";
    }

    @PutMapping("edit/{id}")
    public String saveAfterEdit(@PathVariable("id") Long id, @Valid @ModelAttribute("announcement") AddAndEditAnnouncementDto dto,
                                BindingResult bindingResult) {
        LOGGER.info("User tries to edit announcement (id: {})", dto.getId());
        if (bindingResult.hasErrors()) {
            LOGGER.info("Announcement edit failed due to incorrectly filled form (id: {})", dto.getId());
            return "announcement-form-update";
        }
        announcementService.update(id, dto);
        LOGGER.info("Announcement successfully edited (id: {})", dto.getId());
        return "announcement-form-update-success";
    }

    @GetMapping("search")
    public String searchAnnouncementsByParam(@RequestParam(value = "param", required = false, defaultValue = "") String param,
                                             Model model) {
        LOGGER.info("Received search request (query: {}) ", param);
        List<QuickViewAnnouncementDto> announcementDtoList = (List<QuickViewAnnouncementDto>) announcementService.searchAllByParameter(param);
        model.addAttribute("searchedAnnouncements", announcementDtoList);
        model.addAttribute("service", quickViewAnnouncementService);
        model.addAttribute("isSuccess", !announcementDtoList.isEmpty());
        if(announcementDtoList.isEmpty())
            LOGGER.info("No announcements found (query: {})", param);
        else
            LOGGER.info("Search list returned (query: {})", param);
        return "searched-announcements";
    }

    @GetMapping("/service-type")
    public String getAllByServiceType(@RequestParam(name = "serviceType") String serviceType,
                                             Model model) {
        LOGGER.info("Received search request (query: {}) ", serviceType);
        List<QuickViewAnnouncementDto> announcementDtoList = (List<QuickViewAnnouncementDto>) announcementService.findAllByServiceType(serviceType);
        model.addAttribute("searchedAnnouncementsByServiceType", announcementDtoList);
        String serviceTypeModified = serviceType.replace('_',' ');
        serviceTypeModified = serviceTypeModified.substring(0, 1).toUpperCase() + serviceTypeModified.substring(1);
        model.addAttribute("enteredServiceType", serviceTypeModified);
        model.addAttribute("service", quickViewAnnouncementService);
        model.addAttribute("isSuccess", !announcementDtoList.isEmpty());
        if(announcementDtoList.isEmpty())
            LOGGER.info("No announcements found (service type: {})", serviceType);
        else
            LOGGER.info("Search list returned (service type: {})", serviceType);
        return "announcements-filtered-by-service-type";
    }
}