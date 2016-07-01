package controller;

import enums.UserEnum;
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
     * @param email
     * @param map
     * @return
     */
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public ModelAndView sendEmail(@RequestParam("email") String email, ModelMap map, HttpSession session) {
        if(email == null){
            map.put("message",UserEnum.EMAIL_ERROR.getStateInfo());
            return new ModelAndView("/regist_1",map);
        }

        System.out.println(RegexUtil.checkEmail(email));

        if(!RegexUtil.checkEmail(email)){
            map.put("message",UserEnum.EMAIL_NOTRIGHT.getStateInfo());
            return new ModelAndView("/regist_1",map);
        }
        if(userService.emailExist(email)){
            map.put("message",UserEnum.EMAIL_EXIST.getStateInfo());
            return new ModelAndView("/regist_1",map);
        }
        //将用户邮箱存入session
        session.setAttribute("email",email);
        //发送邮件消息

        return new ModelAndView("/regist_2",map);
    }

    /**
     * 添加用户
     * @param username
     * @param password
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView addUser(@PathVariable("username") String username, @PathVariable("password") String password,
                           ModelMap map,HttpSession session) throws Exception {

        String email = (String) session.getAttribute("email");
        if (username == null || password == null || email == null) {
            map.put("message",UserEnum.USER_PARAMEERROR.getStateInfo());
            return new ModelAndView("/regist",map);
        }
        if (!RegexUtil.checkUsername(username) || !RegexUtil.checkPassword(password) || !RegexUtil.checkEmail(email)) {
            map.put("message",UserEnum.USER_PARAMEERROR.getStateInfo());
            return new ModelAndView("/regist",map);
        }
        if (userService.usernameExist(username)) {
            map.put("message",UserEnum.USERNAME_EXIST.getStateInfo());
            return new ModelAndView("/regist",map);
        }
        if(userService.emailExist(email)){
            map.put("message",UserEnum.EMAIL_EXIST.getStateInfo());
            return new ModelAndView("/regist",map);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setCreateTime(new Date());

        if (!userService.regist(user)) {
            map.put("message",UserEnum.USER_ADDERROR.getStateInfo());
            return new ModelAndView("/regist",map);
        }
        //TODO 发送邮件验证

        return new ModelAndView("/regist_3");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@PathVariable("account") String account, @PathVariable("password") String password) {

        String username = null;
        String email = null;

        if (account == null || password == null) {
            return "redirect:/user/toLogin";
        }
        if (RegexUtil.checkUsername(account)) {
            username = account;
        } else if (RegexUtil.checkEmail(account)) {
            email = account;
        } else {
            return "redirect:/user/toLogin";
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        if (!userService.login(user)) {
            return "redirect:/user/toLogin";
        }
        return "/index";
    }


}
