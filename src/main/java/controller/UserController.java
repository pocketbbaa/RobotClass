package controller;

import enums.UserEnum;
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

import java.util.Date;

/**
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
                         @PathVariable("email") String email, ModelMap map) {

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
