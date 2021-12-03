package pl.infoshare.workandfun.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Paths {

    private final String announcementPath;
    private final String usersPath;

    public Paths(@Value("${pl.infoshare.workandfun.announcements-path}") String announcementPath,
                 @Value("${pl.infoshare.workandfun.users-path}") String usersPath) {
        this.announcementPath = announcementPath;
        this.usersPath = usersPath;
    }

    public String getAnnouncementPath() {
        return announcementPath;
    }

    public String getUsersPath() {
        return usersPath;
    }

}
