package framwork.message;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by admin on 2016/6/28.
 */
public class MailSenderTest {

    /* 必需的信息 */
    String SMTP_MAIL_HOST = "smtp.163.com";// 此邮件服务器地址
    String EMAIL_USERNAME = "11133115wyd@163.com";
    String EMAIL_PASSWORD = "11133115";
    String TO_EMAIL_ADDRESS_1 = "541230721@qq.com";
    /* 选填的信息 */
    //String TO_EMAIL_ADDRESS_2 = "example@163.com";

    @Test
    public void send() throws Exception {

        /* 使用情况一，正常使用 */
        MailSenderConfig c = new MailSenderConfig(SMTP_MAIL_HOST
                , "aemail"
                , "<h1>this is content</h1>", EMAIL_USERNAME);
        c.setUsername(EMAIL_USERNAME);
        c.setPassword(EMAIL_PASSWORD);
        c.addToMail(TO_EMAIL_ADDRESS_1);
        c.addCcMail(TO_EMAIL_ADDRESS_1);
        c.addAttachment(new Attachment(new File("d:/1.txt")));
        MailSender ms = new MailSender(c);
        ms.send();
        System.out.println("sent...");
    }

}