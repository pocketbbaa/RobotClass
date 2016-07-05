package service.impl;

import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import service.ProducerService;
import service.UserService;

import javax.jms.Destination;
import java.util.Date;

/**
 * Created by admin on 2016/6/27.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private ProducerService producerService;
    @Autowired
    @Qualifier("queueDestination")
    private Destination destination;

    @Override
    public boolean login(User user) {
        String phone = user.getPhone();
        String email = user.getEmail();
        String password = user.getPassword();
        int i = dao.checkLogin(phone, email, password);
        return i == 1;
    }

    @Override
    public boolean regist(User user) {

        String phone = user.getPhone();
        String password = user.getPassword();
        String email = user.getEmail();
        Date createTime = user.getCreateTime();
        int i = dao.addUser(email, phone, password, createTime);

        return i == 1;
    }

    @Override
    public boolean usernameExist(String username) {
        int i = dao.usernameExist(username);
        return i == 1;
    }

    @Override
    public boolean emailExist(String email) {
        int i = dao.emailExist(email);
        return i == 1;
    }

    @Override
    public boolean phoneExist(String phone) {
        int i = dao.phoneExits(phone);
        return i == 1;
    }

    @Override
    public void sendMessage(String message) {
        try {
            producerService.sendMessage(destination, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByEmail(String email) {

        return dao.getUserByEmail(email);
    }

    @Override
    public User getUserById(Long id) {
        return dao.getUserById(id);
    }


}
