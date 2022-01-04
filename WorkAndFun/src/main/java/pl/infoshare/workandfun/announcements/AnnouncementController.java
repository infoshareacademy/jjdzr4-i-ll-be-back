package pl.infoshare.workandfun.announcements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.infoshare.workandfun.announcements.dto.AddAndEditDto;
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
        model.addAttribute("announcement", new AddAndEditDto());
        return "announcement-form";
    }

    @PostMapping("add-announcement")
    public String save(@Valid @ModelAttribute("announcement") AddAndEditDto addAndEditDto,
                       BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "announcement-form";
        }
        announcementsService.save(addAndEditDto);
        return "announcement-form-success";
    }

    @GetMapping("edit/{id}")
    public String announcementEditForm(Model model, @PathVariable Long id){
        model.addAttribute("announcement", announcementsService.findByIdConvertToDto(id));
        return "announcement-form-update";
    }

    @PutMapping("edit/{id}")
    public String saveAfterEdit(@PathVariable("id") Long id, @Valid @ModelAttribute("announcement") AddAndEditDto dto,
                                BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "announcement-form-update";
        }
        announcementsService.update(id, dto);
        return "announcement-form-update-success";
    }
}
