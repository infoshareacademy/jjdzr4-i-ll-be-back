package pl.infoshare.workandfun.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnnouncementNotFoundException extends RuntimeException{

    private static final Logger LOGGER = LogManager.getLogger(AnnouncementNotFoundException.class);

    public AnnouncementNotFoundException(Long id) {
        super("Brak ogłoszenia o numerze ID = " + id);
        LOGGER.info("Announcement not found (id: {})", id);
    }
}
