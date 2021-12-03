package pl.infoshare.workandfun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.infoshare.workandfun.config.Paths;

@RestController
public class TestController {

    private final Paths paths;

    @Autowired
    public TestController(Paths paths) {
        this.paths = paths;
    }

    @GetMapping("test")
    public String getPaths(){
        return paths.getAnnouncementPath();
    }
}
