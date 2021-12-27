package top.horizonask.hoawiki;

import cn.hutool.core.util.StrUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@RestController
@MapperScan("top.horizonask.hoawiki.mapper")
public class HoawikiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoawikiApplication.class, args);
    }
}
