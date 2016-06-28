package service.impl;

import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.Date;

/**
 * Created by admin on 2016/6/27.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    public boolean login(User user) {
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        int i = dao.checkLogin(username, email, password);
        return i == 1;
    }

    @Override
    public boolean regist(User user) {

        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        Date createTime = user.getCreateTime();
        int i = dao.addUser(email, username, password, createTime);

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


}
