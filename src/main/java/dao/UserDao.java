package dao;

import model.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Created by admin on 2016/6/27.
 */
public interface UserDao {

    /**
     * 邮箱是否存在
     * @param email
     * @return
     */
    int emailExist(String email);

    /**
     * 用户名是否存在
     * @param username
     * @return
     */
    int usernameExist(String username);

    /**
     * 添加用户
     * @param email
     * @param username
     * @param password
     * @param createTime
     * @return
     */
    int addUser(@Param("email") String email, @Param("username") String username,
                    @Param("password") String password, @Param("createTime") Date createTime);

    /**
     * 校验账号密码是否正确
     * @param account
     * @param password
     * @return
     */
    int checkLogin(String account,String password);

    /**
     * 根据ID获取用户信息
     * @param id
     * @return
     */
    User getUserById(Long id);


}
