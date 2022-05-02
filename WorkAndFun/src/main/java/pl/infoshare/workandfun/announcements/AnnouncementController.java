package pl.infoshare.workandfun.announcements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;
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
    public String getAllAnnouncementsDateDesc(Model model, @RequestParam(defaultValue = "1") Integer page) {
        LOGGER.info("Received request for all announcements");
        if (page < 1) {
            page = 1;
        }
        Page<QuickViewAnnouncementDto> dto = announcementService.findAllSortedByCreateDateDescConvertToDto(page - 1, 5);
        model.addAttribute("announcements", dto);

        int totalPages = dto.getTotalPages();
        List<Integer> threeClosestPage;
        if (dto.getTotalPages() < 3) {
            threeClosestPage = List.of(1, 2);
        } else if (page == 1) {
            threeClosestPage = List.of(page, page + 1, page + 2);
        } else if (page >= totalPages) {
            threeClosestPage = List.of(totalPages - 2, totalPages - 1, totalPages);
        } else {
            threeClosestPage = List.of(page - 1, page, page + 1);
        }
        model.addAttribute("threeClosestPage", threeClosestPage);

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
                       BindingResult bindingResult, HttpServletRequest request, Model model) {
        LOGGER.info("User tries to add new announcement");
        if (bindingResult.hasErrors()) {
            LOGGER.info("Announcement save failed due to incorrectly filled form");
            return "announcement-form";
        }
        LOGGER.info("Announcement form filled correctly, saving to database");
        Long id = announcementService.save(addAndEditAnnouncementDto, request.getUserPrincipal().getName());
        model.addAttribute("allDetails", announcementService.findByIdConvertToDto(id));
        model.addAttribute("service", quickViewAnnouncementService);
        model.addAttribute("isNew", true);
        return "announcement-details";
    }

    @GetMapping("edit/{id}")
    public String announcementEditForm(HttpServletRequest request, Model model, @PathVariable Long id) {
        LOGGER.info("Received request for announcement edit form (announcement id: {})", id);
        String username = request.getUserPrincipal().getName();
        Announcement foundAnnouncement = announcementService.findById(id);
        if (foundAnnouncement.getOwner().getUsername().equals(username)) {
            model.addAttribute("announcement", announcementService.findByIdConvertToDto(id));
        } else {
            return "redirect:error";
        }
        return "announcement-form-update";
    }

    @PutMapping("edit/{id}")
    public String saveAfterEdit(@PathVariable("id") Long id, @Valid @ModelAttribute("announcement") AddAndEditAnnouncementDto dto,
                                BindingResult bindingResult, Model model) {
        LOGGER.info("User tries to edit announcement (id: {})", dto.getId());
        if (bindingResult.hasErrors()) {
            LOGGER.info("Announcement edit failed due to incorrectly filled form (id: {})", dto.getId());
            return "announcement-form-update";
        }
        announcementService.update(id, dto);
        LOGGER.info("Announcement successfully edited (id: {})", dto.getId());
        model.addAttribute("allDetails", dto);
        model.addAttribute("service", quickViewAnnouncementService);
        model.addAttribute("isUpdated", true);
        return "announcement-details";
    }

    @GetMapping("search")
    public String searchAnnouncementsByParam(@RequestParam(value = "param", required = false, defaultValue = "") String param,
                                             Model model) {
        LOGGER.info("Received search request (query: {}) ", param);
        List<QuickViewAnnouncementDto> announcementDtoList = (List<QuickViewAnnouncementDto>) announcementService.searchAllByParameter(param);
        model.addAttribute("searchedAnnouncements", announcementDtoList);
        model.addAttribute("service", quickViewAnnouncementService);
        model.addAttribute("isSuccess", !announcementDtoList.isEmpty());
        if (announcementDtoList.isEmpty())
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
        String serviceTypeModified = serviceType.replace('_', ' ');
        serviceTypeModified = serviceTypeModified.substring(0, 1).toUpperCase() + serviceTypeModified.substring(1);
        model.addAttribute("enteredServiceType", serviceTypeModified);
        model.addAttribute("service", quickViewAnnouncementService);
        model.addAttribute("isSuccess", !announcementDtoList.isEmpty());
        if (announcementDtoList.isEmpty())
            LOGGER.info("No announcements found (service type: {})", serviceType);
        else
            LOGGER.info("Search list returned (service type: {})", serviceType);
        return "announcements-filtered-by-service-type";
    }

    @GetMapping("/my-announcements")
    public String getOwnAnnouncements(HttpServletRequest request,
                                      Model model) {
        String username = request.getUserPrincipal().getName();
        model.addAttribute("ownAnnouncements", announcementService.findAllByOwner(username));
        model.addAttribute("service", quickViewAnnouncementService);
        return "own-announcements";
    }

    @GetMapping("delete/{id}")
    public String getDeleteById(HttpServletRequest request, Model model, @PathVariable Long id) {
        LOGGER.info("Received request for announcement delete form (announcement id: {})", id);
        String username = request.getUserPrincipal().getName();
        Announcement foundAnnouncement = announcementService.findById(id);
        if (foundAnnouncement.getOwner().getUsername().equals(username)) {
            model.addAttribute("announcementToDelete", announcementService.findByIdConvertToDto(id));
        } else {
            LOGGER.info("ERROR: announcement doesn't belong to this user (announcement id: {})", id);
            return "redirect:error";
        }
        return "delete-confirmation-ask";
    }

    @DeleteMapping("delete/{id}")
    public String deleteById(@PathVariable("id") Long id, @ModelAttribute("announcementToDelete") AddAndEditAnnouncementDto dto,
                             Model model, HttpServletRequest request) {
        LOGGER.info("Start deleting the announcement by id: {}", id);
        String username = request.getUserPrincipal().getName();
        Announcement foundAnnouncement = announcementService.findById(id);
        if (foundAnnouncement.getOwner().getUsername().equals(username)) {
            announcementService.deleteById(id);
            LOGGER.info("Announcement {} successfully deleted", id);
            return "redirect:/announcement/my-announcements";
        } else {
            return "redirect:error";
        }
    }
}