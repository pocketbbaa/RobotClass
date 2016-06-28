package controller;

import enums.UserEnum;
import framwork.message.Attachment;
import framwork.message.MailSender;
import framwork.message.MailSenderConfig;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;
import utils.RegexUtil;
import vo.BaseResult;

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

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@PathVariable("username") String username, @PathVariable("password") String password,
                         @PathVariable("email") String email, ModelMap map) throws Exception {

        if (username == null || password == null || email == null) {
            return "redirect:/user/toRegist";
        }
        if (!RegexUtil.checkUsername(username) || !RegexUtil.checkPassword(password) || !RegexUtil.checkEmail(email)) {
            return "redirect:/user/toRegist";
        }
        if (!userService.usernameExist(username) || !userService.emailExist(email)) {
            return "redirect:/user/toRegist";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setCreateTime(new Date());

        if (!userService.regist(user)) {
            return "redirect:/user/toRegist";
        }
        //TODO 发送邮件验证
        String SMTP_MAIL_HOST = "smtp.163.com";// 此邮件服务器地址
        String EMAIL_USERNAME = "11133115wyd@163.com";
        String EMAIL_PASSWORD = "11133115";
        String TO_EMAIL_ADDRESS_1 = email;

        MailSenderConfig c = new MailSenderConfig(SMTP_MAIL_HOST
                , "激活账户邮件"
                , "<h3>请点击下面的链接激活你的账户</h3><dr/><a href=''></a>"
                , EMAIL_USERNAME);
        c.setUsername(EMAIL_USERNAME);
        c.setPassword(EMAIL_PASSWORD);
        c.addToMail(TO_EMAIL_ADDRESS_1);
        c.addCcMail(TO_EMAIL_ADDRESS_1);
        c.addAttachment(new Attachment(new File("d:/1.txt")));
        MailSender ms = new MailSender(c);
        ms.send();

        return "/sendemail";
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
        }else if (RegexUtil.checkEmail(account)) {
            email = account;
        }else{
            return "redirect:/user/toLogin";
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        if(!userService.login(user)){
            return "redirect:/user/toLogin";
        }
        return "/index";
    }


}
