package service;

import model.User;

import javax.jms.Destination;

/**
 * Created by admin on 2016/6/27.
 */
public interface UserService {

    /**
     * 登录
     * @param user
     * @return
     */
    boolean login(User user);

    /**
     * 注册
     * @param user
     * @return
     */
    boolean regist(User user);

    /**
     * 用户名是否存在
     * @param username
     * @return
     */
    boolean usernameExist(String username);

    /**
     * 邮箱是否存在
     * @param email
     * @return
     */
    boolean emailExist(String email);

    /**
     * 电话是否存在
     * @param phone
     * @return
     */
    boolean phoneExist(String phone);

    /**
     * 发送消息
     * @param message
     */
    void sendMessage(String message);

}
