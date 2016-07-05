package controller;

import enums.UserEnum;
import exception.MQException;
import framwork.message.Attachment;
import framwork.message.MailSender;
import framwork.message.MailSenderConfig;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;
import utils.RegexUtil;
import vo.BaseResult;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;

/**
 * 用户
 * Created by admin on 2016/6/27.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/toRegist")
    public String toRegist() {
        return "/regist";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "/login";
    }

    /**
     * 异步校验用户名
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/checkUsername", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult checkUsername(@PathVariable("username") String username) {

        if (username == null) {
            return new BaseResult(BaseResult.STATUS_ERROR, UserEnum.USER_PARAMEERROR.getStateInfo());
        }
        if (!RegexUtil.checkUsername(username)) {
            return new BaseResult(BaseResult.STATUS_ERROR, UserEnum.USER_FORMATERROR.getStateInfo());
        }
        if (!userService.usernameExist(username)) {
            return new BaseResult(BaseResult.STATUS_ERROR, UserEnum.USER_NOEXIST.getStateInfo());
        }
        return new BaseResult(BaseResult.STATUS_OK);
    }

    /**
     * 异步校验密码
     *
     * @param password
     * @return
     */
    @RequestMapping(value = "/checkPassword", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult checkPassword(@PathVariable("password") String password) {

        if (password == null) {
            return new BaseResult(BaseResult.STATUS_ERROR, UserEnum.USER_PARAMEERROR.getStateInfo());
        }
        if (!RegexUtil.checkPassword(password)) {
            return new BaseResult(BaseResult.STATUS_ERROR, UserEnum.USER_FORMATERROR.getStateInfo());
        }
        return new BaseResult(BaseResult.STATUS_OK);
    }

    /**
     * 异步校验邮箱
     *
     * @param email
     * @return
     */
    @RequestMapping(value = "/checkEmail", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult checkEmail(@PathVariable("email") String email) {

        if (email == null) {
            return new BaseResult(BaseResult.STATUS_ERROR, UserEnum.USER_PARAMEERROR.getStateInfo());
        }
        if (!RegexUtil.checkEmail(email)) {
            return new BaseResult(BaseResult.STATUS_ERROR, UserEnum.USER_FORMATERROR.getStateInfo());
        }
        if (!userService.emailExist(email)) {
            return new BaseResult(BaseResult.STATUS_ERROR, UserEnum.USER_NOEXIST.getStateInfo());
        }
        return new BaseResult(BaseResult.STATUS_OK);
    }

    /**
     * 注册第一步：校验邮箱，发送验证邮件
     *
     * @param email
     * @param map
     * @return
     */
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public ModelAndView sendEmail(@RequestParam("email") String email, ModelMap map, HttpSession session) {
        if (email == null) {
            map.put("message", UserEnum.EMAIL_ERROR.getStateInfo());
            return new ModelAndView("/regist_1", map);
        }

        System.out.println(RegexUtil.checkEmail(email));

        if (!RegexUtil.checkEmail(email)) {
            map.put("message", UserEnum.EMAIL_NOTRIGHT.getStateInfo());
            return new ModelAndView("/regist_1", map);
        }
        if (userService.emailExist(email)) {
            map.put("message", UserEnum.EMAIL_EXIST.getStateInfo());
            return new ModelAndView("/regist_1", map);
        }
        //将用户邮箱存入session
        session.setAttribute("email", email);

        try {
            //发送MQ消息
            userService.sendMessage(email);
        } catch (MQException e) {
            e.printStackTrace();
            return new ModelAndView("/404");
        }

        return new ModelAndView("/regist_2", map);
    }

    /**
     * 添加用户
     *
     * @param phone
     * @param password
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView addUser(@RequestParam("phone") String phone, @RequestParam("password") String password,
                                ModelMap map, HttpSession session) throws Exception {

        String email = (String) session.getAttribute("email");

        if (phone == null || password == null) {
            map.put("message", UserEnum.USER_PARAMEERROR.getStateInfo());
            return new ModelAndView("redirect:/regist", map);
        }
        if (!RegexUtil.checkCellphone(phone) || !RegexUtil.checkPassword(password)) {
            map.put("message", UserEnum.USER_PARAMEERROR.getStateInfo());
            return new ModelAndView("redirect:/regist", map);
        }
        if (userService.phoneExist(phone)) {
            map.put("message", UserEnum.USERNAME_EXIST.getStateInfo());
            return new ModelAndView("redirect:/regist", map);
        }


        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        user.setEmail(email);
        user.setCreateTime(new Date());

        if (!userService.regist(user)) {
            map.put("message", UserEnum.USER_ADDERROR.getStateInfo());
            return new ModelAndView("redirect:/regist", map);
        }
        //注册成功，将用户信息存入session
        user = userService.getUserByEmail(email);
        session.setAttribute("user", user);
        return new ModelAndView("/regist_success");
    }

    /**
     * 登录
     * @param account
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("account") String account, @RequestParam("password") String password, HttpSession session) {

        String phone = null;
        String email = null;

        if (account == null || password == null) {
            return "redirect:/user/toLogin";
        }
        if (RegexUtil.checkCellphone(account)) {
            phone = account;
        } else if (RegexUtil.checkEmail(account)) {
            email = account;
        } else {
            return "redirect:/user/toLogin";
        }
        User user = new User();
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);
        if (!userService.login(user)) {
            return "redirect:/user/toLogin";
        }
        user = userService.getUserByEmail(email);
        session.setAttribute("user", user);
        return "redirect:/index";
    }

    /**
     * 用户登出
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user != null){
            session.removeAttribute("user");
            return "redirect:/index";
        }
        return "redirect:/index";
    }


}
