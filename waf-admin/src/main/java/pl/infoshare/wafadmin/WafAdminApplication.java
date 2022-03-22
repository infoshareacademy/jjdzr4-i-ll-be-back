package pl.infoshare.wafadmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class WafAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WafAdminApplication.class, args);
    }

}
