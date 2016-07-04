package exception;

/**
 * 发送MQ消息异常类
 * Created by admin on 2016/7/4.
 */
public class MQException extends RuntimeException {

    public MQException(String message) {
        super(message);
    }

    public MQException(String message, Throwable cause) {
        super(message, cause);
    }
}
