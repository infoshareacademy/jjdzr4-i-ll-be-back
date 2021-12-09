package pl.infoshare.workandfun.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Paths {

    private final String usersPath;

    public Paths(@Value("${pl.infoshare.workandfun.users-path}") String usersPath) {
        this.usersPath = usersPath;
    }

    public String getUsersPath() {
        return usersPath;
    }
}
