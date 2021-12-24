package top.horizonask.hoawiki;

import cn.hutool.core.util.StrUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HoawikiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoawikiApplication.class, args);
    }

    @GetMapping("/hello1")
    public String sayHello1(@RequestParam(required = false, name = "who") String who) {
        if (StrUtil.isBlank(who)) {
            who = "World";
        }
        return StrUtil.format("Hello, {}!", who);
    }

    @GetMapping("/hello2")
    public String sayHello2(@RequestParam(required = false, name = "who") String who) {
        if (StrUtil.isBlank(who)) {
            who = "World2";
        }
        return StrUtil.format("Hello, {}!", who);
    }
}
