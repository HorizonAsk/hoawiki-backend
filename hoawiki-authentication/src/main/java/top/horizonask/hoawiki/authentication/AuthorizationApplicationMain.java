package top.horizonask.hoawiki.authentication;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.horizonask.hoawiki.authentication.mapper")
public class AuthorizationApplicationMain {
    public static void main(String[] args){
        SpringApplication.run(AuthorizationApplicationMain.class,args);
    }
}
