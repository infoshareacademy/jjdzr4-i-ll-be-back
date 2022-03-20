package pl.infoshare.workandfun.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.infoshare.workandfun.users.dto.UserAddingDto;

@Controller
public class UserController {

    //TODO: implement LOGGERS
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sign-up")
    public String userGetAddForm(Model model) {
        model.addAttribute("newUser", new UserAddingDto());
        return "user-add";
    }

    @PostMapping("/sign-up")
    public String userAddForm(@RequestBody @ModelAttribute("newUser") UserAddingDto userAddingDto) {
        userService.registerNewUserAccount(userAddingDto);
        return "user-add-success";
    }
}