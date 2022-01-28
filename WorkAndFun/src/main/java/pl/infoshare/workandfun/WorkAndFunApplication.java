package pl.infoshare.workandfun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorkAndFunApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkAndFunApplication.class, args);
        System.out.println("Witaj w aplikacji WorkAndFun");
    }

}