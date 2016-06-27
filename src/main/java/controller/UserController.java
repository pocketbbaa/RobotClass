package controller;

import enums.UserEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;
import utils.RegexUtil;
import vo.BaseResult;

/**
 * Created by admin on 2016/6/27.
 */
@Controller
@RequestMapping("/regist")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/toRegist")
    public String toRegist() {
        return "/regist";
    }


    @RequestMapping(value = "/checkUsername",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult checkUsername(@PathVariable("username") String username){

        if(username == null){
            return new BaseResult(BaseResult.STATUS_ERROR, UserEnum.USER_NAMENULL.getStateInfo());
        }
        if(!RegexUtil.checkUsername(username)){
            return new BaseResult(BaseResult.STATUS_ERROR,UserEnum.USER_FORMATERROR.getStateInfo());
        }
        if(!userService.usernameExist(username)){
            return new BaseResult(BaseResult.STATUS_ERROR,UserEnum.USER_NOEXIST.getStateInfo());
        }

        return null;
    }



}
