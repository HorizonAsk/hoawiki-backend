package top.horizonask.hoawiki.mail;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2022/1/21 21:36
 */

@Slf4j
@Service
public class MailService {
    private final MailConfiguration mailConfiguration;

    private final JavaMailSender javaMailSender;

    public MailService(MailConfiguration mailConfiguration, JavaMailSender javaMailSender) {
        this.mailConfiguration = mailConfiguration;
        this.javaMailSender = javaMailSender;
    }

    public void sendSimpleMailMessage(MailEntity mailEntity){
        SimpleMailMessage simpleMailMessage = mailEntity.toSimpleMailMessage();
        if (StrUtil.isEmpty(simpleMailMessage.getFrom())){
            simpleMailMessage.setFrom(mailConfiguration.getFrom());
        }
        try {
            javaMailSender.send(simpleMailMessage);
        }
        catch(Exception e){
            log.error(String.valueOf(e));
            throw e;
        }
    }

    public void sendMimeMessage(MailEntity mailEntity){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try{
            messageHelper = new MimeMessageHelper(mimeMessage,true);
            if (StrUtil.isEmpty(mailEntity.getFrom())){
                messageHelper.setFrom(mailConfiguration.getFrom());
            }
            messageHelper.setTo(mailEntity.getTo());
            messageHelper.setSubject(mailEntity.getSubject());

            mimeMessage = messageHelper.getMimeMessage();
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(mailEntity.getText(),"text/html;charset=UTF-8");

            // Data relationship
            MimeMultipart mm=new MimeMultipart();
            mm.setSubType("related");
            mm.addBodyPart(mimeBodyPart);

            // Add attach files.
            for (String filename:mailEntity.getFilenames()){
                MimeBodyPart attachPart = new MimeBodyPart();
                try{
                    attachPart.attachFile(filename);
                }catch(IOException e){
                    e.printStackTrace();
                }
                mm.addBodyPart(attachPart);
            }
            mimeMessage.setContent(mm);
            mimeMessage.saveChanges();
        }
        catch(MessagingException e){
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }
}
