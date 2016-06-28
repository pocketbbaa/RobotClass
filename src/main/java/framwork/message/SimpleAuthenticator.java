package framwork.message;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 邮件认证器
 * Created by admin on 2016/6/28.
 */
public class SimpleAuthenticator extends Authenticator {

    private String username;
    private String password;

    public SimpleAuthenticator(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
