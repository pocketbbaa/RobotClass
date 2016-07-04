package framwork.activeMQ.listener;

import framwork.message.MailSender;
import framwork.message.MailSenderConfig;
import utils.BCryptUtil;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.servlet.http.HttpSession;

/**
 * 消息处理监听器
 * Created by admin on 2016/6/28.
 */
public class ConsumerMessageListener implements MessageListener {

    private HttpSession session;

    @Override
    public void onMessage(Message message) {

        TextMessage textMessage = (TextMessage) message;
        String email = null;
        try {
            email = textMessage.getText();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        String key = createKey();
        System.out.println("接收到MQ消息：" + email);

         /* 必需的信息 */
        String SMTP_MAIL_HOST = "smtp.163.com";// 此邮件服务器地址,固定，可在网上查看
        String EMAIL_USERNAME = "11133115wyd@163.com"; // 发送者邮件地址
        String EMAIL_PASSWORD = "11133115"; //发送者的邮件密码
        String TO_EMAIL_ADDRESS_1 = email; //接收邮件的地址

        //创建MailSenderConfig对象
        //参数说明：邮件服务器地址，邮件主题，邮件类容，发送者邮件地址
        MailSenderConfig c = new MailSenderConfig(SMTP_MAIL_HOST
                , "认证信息"
                , "<h1>I AM ROBOT</h1><dr/><h3><a href='192.168.2.103:8087/regist.jsp'>请点击我，完成信息校验!</a></h3>", EMAIL_USERNAME);
        c.setUsername(EMAIL_USERNAME);
        c.setPassword(EMAIL_PASSWORD);
        c.addToMail(TO_EMAIL_ADDRESS_1);
        MailSender ms = null;
        try {
            System.out.println("开始发送邮件");
            ms = new MailSender(c);
            ms.send();
            System.out.println("邮件已发送！");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 生成随机KEY
     *
     * @return
     */
    public String createKey() {
        String key = BCryptUtil.randomUUID();
        return key;
    }

}
