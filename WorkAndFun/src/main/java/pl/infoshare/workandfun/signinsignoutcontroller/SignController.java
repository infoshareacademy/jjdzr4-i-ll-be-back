package pl.infoshare.workandfun.signinsignoutcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.infoshare.workandfun.announcements.dto.AddAndEditAnnouncementDto;

import javax.validation.Valid;

@Controller
public class SignController {

    @GetMapping("/sign-in")
    public String signIn() {
        return "sign-in";
    }

    @GetMapping("/sign-out")
    public String signOut() {
        return "sign-out";
    }

}