package top.horizonask.hoawiki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication()
@MapperScan("top.horizonask.hoawiki.*.mapper")
public class HoawikiApplication {
    public static void main(String[] args) {
        SpringApplication.run(HoawikiApplication.class, args);
    }
}
