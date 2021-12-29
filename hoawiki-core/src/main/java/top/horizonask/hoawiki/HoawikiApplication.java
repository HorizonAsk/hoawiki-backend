package top.horizonask.hoawiki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication()
@RestController
public class HoawikiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoawikiApplication.class, args);
    }
}
