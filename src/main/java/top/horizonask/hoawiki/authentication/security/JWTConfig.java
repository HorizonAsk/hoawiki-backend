package top.horizonask.hoawiki.authentication.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2021/12/31 0:27
 */

@Component
@ConfigurationProperties(prefix="jwt")
public class JWTConfig {
    public static String secret;

    public static String tokenHeader;

    public static String tokenPrefix;

    public static Integer expiration;

    public static String antMatchers;
    public static String issuer;


    public void setExpiration(Integer expiration){
        this.expiration = expiration*1000;
    }

    public void setSecret(String secret){
        this.secret=secret;
    }

    public void setTokenHeader(String tokenHeader){
        this.tokenHeader=tokenHeader;
    }

    public void setTokenPrefix(String tokenPrefix){
        this.tokenPrefix=tokenPrefix+" ";
    }

    public void setAntMatchers(String antMatchers){
        this.antMatchers=antMatchers;
    }
}
