package top.horizonask.hoawiki.mail;

import lombok.Data;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

/**
 * @description: Offer entity of mail.
 * @author: Yanbo Han
 * @time: 2022/1/21 21:33
 */

@Data
public class MailEntity {
    private String from;
    private String replyTo;
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private Date sentDate;
    private String subject;
    private String text;
    private String[] filenames;

    public SimpleMailMessage toSimpleMailMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setReplyTo(replyTo);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setCc(cc);
        simpleMailMessage.setBcc(bcc);
        simpleMailMessage.setSentDate(sentDate);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        return simpleMailMessage;
    }
}
