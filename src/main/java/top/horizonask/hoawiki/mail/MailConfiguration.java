package top.horizonask.hoawiki.mail;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description: Offer Email configurations.
 * @author: Yanbo Han
 * @time: 2022/1/21 21:29
 */

@Component
@ConfigurationProperties(prefix = "mail")
public class MailConfiguration {
    private String domain;
    private String from;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

}
