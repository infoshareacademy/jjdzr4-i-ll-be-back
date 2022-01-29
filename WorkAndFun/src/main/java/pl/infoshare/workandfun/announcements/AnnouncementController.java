package pl.infoshare.workandfun.announcements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.infoshare.workandfun.announcements.dto.AddAndEditAnnouncementDto;
import pl.infoshare.workandfun.announcements.dto.QuickViewAnnouncementDto;
import pl.infoshare.workandfun.announcements.dto.QuickViewAnnouncementService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("announcement")
public class AnnouncementController {

    private final AnnouncementService announcementService;
    private final QuickViewAnnouncementService quickViewAnnouncementService;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService, QuickViewAnnouncementService quickViewAnnouncementService) {
        this.announcementService = announcementService;
        this.quickViewAnnouncementService = quickViewAnnouncementService;
    }

    @GetMapping("details/{id}")
    public String getAnnouncementDetails(@PathVariable Long id,
                                         Model model) {
        model.addAttribute("allDetails", announcementService.findByIdConvertToDto(id));
        model.addAttribute("service", quickViewAnnouncementService);
        return "announcement-details";
    }

    @GetMapping("all")
    public String getAllAnnouncementsDateDesc(Model model) {
        model.addAttribute("announcements", announcementService.findAllSortedByCreateDateDescConvertToDto());
        model.addAttribute("service", quickViewAnnouncementService);
        return "all-announcements";
    }


    @GetMapping("add-new")
    public String announcementForm(Model model) {
        model.addAttribute("announcement", new AddAndEditAnnouncementDto());
        return "announcement-form";
    }

    @PostMapping("add-new")
    public String save(@Valid @ModelAttribute("announcement") AddAndEditAnnouncementDto addAndEditAnnouncementDto,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "announcement-form";
        }
        announcementService.save(addAndEditAnnouncementDto);
        return "announcement-form-success";
    }

    @GetMapping("edit/{id}")
    public String announcementEditForm(Model model, @PathVariable Long id) {
        model.addAttribute("announcement", announcementService.findByIdConvertToDto(id));
        return "announcement-form-update";
    }

    @PutMapping("edit/{id}")
    public String saveAfterEdit(@PathVariable("id") Long id, @Valid @ModelAttribute("announcement") AddAndEditAnnouncementDto dto,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "announcement-form-update";
        }
        announcementService.update(id, dto);
        return "announcement-form-update-success";
    }

    @GetMapping("search")
    public String searchAnnouncementsByParam(@RequestParam(value = "param", required = false, defaultValue = "") String param,
                                             Model model) {
        List<QuickViewAnnouncementDto> announcementDtoList = (List<QuickViewAnnouncementDto>) announcementService.searchAllByParameter(param);
        model.addAttribute("searchedAnnouncements", announcementDtoList);
        model.addAttribute("service", quickViewAnnouncementService);
        model.addAttribute("isSuccess", !announcementDtoList.isEmpty());
        return "searched-announcements";
    }
}