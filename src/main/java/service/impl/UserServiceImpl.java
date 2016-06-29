package service.impl;

import dao.UserDao;
import model.User;
import org.springframework.stereotype.Service;
import service.UserService;

/**
 * Created by admin on 2016/6/27.
 */
@Service
public class UserServiceImpl implements UserService {


    @Override
    public boolean login(User user) {


        return false;
    }

    @Override
    public boolean regist(User user) {


        return false;
    }

    @Override
    public boolean usernameExist(String username) {


        return false;
    }

    @Override
    public boolean emailExist(String email) {


        return false;
    }


}
