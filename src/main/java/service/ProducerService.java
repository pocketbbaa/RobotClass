package service;

import javax.jms.Destination;

/**
 *
 * Created by admin on 2016/6/24.
 */
public interface ProducerService {

    /**
     * 发送消息方法
     * @param destination
     * @param message
     */
    public void sendMessage(Destination destination, final String message);


}
