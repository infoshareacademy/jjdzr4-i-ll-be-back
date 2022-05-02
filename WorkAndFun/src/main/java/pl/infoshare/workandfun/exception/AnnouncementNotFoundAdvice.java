package pl.infoshare.workandfun.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class AnnouncementNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(AnnouncementNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String announcementNotFoundHandler(AnnouncementNotFoundException exception) {
        return exception.getMessage();
    }
}
