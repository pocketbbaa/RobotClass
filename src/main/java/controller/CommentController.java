package controller;

import enums.CourseEnum;
import enums.UserEnum;
import model.Comment;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addComment(@PathVariable("text") String text, @PathVariable("courseId") Long courseId, HttpSession session) {

        if (text == null || courseId == null || courseId == 0) {
            return new BaseResult(BaseResult.STATUS_ERROR, CourseEnum.PARAME_ERROR.getStateInfo());
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new BaseResult(BaseResult.STATUS_ERROR, UserEnum.USER_NOJD.getStateInfo());
        }
        Comment comment = new Comment();
        comment.setText(text);
        comment.setCreateTime(new Date());
        boolean addSuccess = commentService.addComment(user.getId(), courseId, comment);
        if (!addSuccess) {
            return new BaseResult(BaseResult.STATUS_ERROR, UserEnum.USER_ADDERROR.getStateInfo());
        }
        return new BaseResult(BaseResult.STATUS_OK, UserEnum.USER_ADDSUCCESS.getStateInfo());
    }

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
