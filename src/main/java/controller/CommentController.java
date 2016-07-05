package controller;

import enums.CourseEnum;
import enums.UserEnum;
import model.Comment;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.CommentService;
import vo.BaseResult;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 评论
 * 1、添加
 * 2、删除
 * Created by admin on 2016/6/28.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    /**
     * 删除评论
     * @param id
     * @return
     */
    @RequestMapping(value = "/delComment", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult delComment(@PathVariable("id") Long id) {

        if (id == null || id == 0) {
            return new BaseResult(BaseResult.STATUS_ERROR, CourseEnum.PARAME_ERROR.getStateInfo());
        }
        boolean delSuccess = commentService.delComment(id);
        if (!delSuccess) {
            return new BaseResult(BaseResult.STATUS_ERROR, CourseEnum.DEL_ERROR.getStateInfo());
        }
        return new BaseResult(BaseResult.STATUS_OK, CourseEnum.DEL_SUCCESS.getStateInfo());
    }


}
