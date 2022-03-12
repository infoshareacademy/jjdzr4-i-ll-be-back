package pl.infoshare.workandfun.announcements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandlingController implements ErrorController {

    private static final Logger LOGGER = LogManager.getLogger(AnnouncementController.class);


    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            LOGGER.error("Sorry, can not open website");

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                LOGGER.error("Page not found 404");
                return "error-404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                LOGGER.error("Server error");
                return "error-500";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()){
                LOGGER.error("Someone tried enter to restricted area. User not logged in.");
                return "error-403";
            }
        }
        LOGGER.error("Other error, please contact with support: 666-999-666");
        return "error";
    }

}
