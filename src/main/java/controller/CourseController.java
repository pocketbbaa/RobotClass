package controller;

import com.sun.xml.internal.messaging.saaj.util.FinalArrayList;
import enums.CourseEnum;
import enums.UserEnum;
import model.Comment;
import model.Course;
import model.User;
import model.Video;
import org.apache.commons.collections.FastArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.CommentService;
import service.CourseService;
import service.UserService;
import service.VideoService;
import utils.Page;
import vo.CommentVO;
import vo.coursesVO;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 课程详情页
 * 1、课程信息
 * 2、视频目录
 * 3、评论分页列表
 * Created by admin on 2016/6/28.
 */
@RequestMapping("/course")
@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;


     /**
     * 课程列表页（分页）
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/{pageIndex}/list", method = RequestMethod.GET)
    public ModelAndView list(ModelMap map, @PathVariable("pageIndex") Integer pageIndex, Integer pageSize) {
        if (pageIndex == null) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            pageSize = 3;
        }
        Page<Course> page = courseService.getCourseListByPage(pageIndex, pageSize);

        int totalCount = page.getTotalCount();
        int totalPage = page.getTotalPage();
        List<Course> courseList = page.getResult();
        coursesVO courseVO = new coursesVO(totalCount, totalPage, courseList,pageIndex);
        System.out.println(courseVO.getTotalPage()+"  "+courseVO.getTotalCount());
        map.put("coursePage", courseVO);

        return new ModelAndView("/choiceclass", map);
    }

    /**
     * 视频播放页跳转
     *
     * @param courseId
     * @param map
     * @return
     */
    @RequestMapping(value = "/{courseId}/detail", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("courseId") Long courseId, ModelMap map) {

        if (courseId == null) {
            map.put("message", CourseEnum.COURSE_ERROR.getStateInfo());
            return new ModelAndView("/404", map);
        }
        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            map.put("message", CourseEnum.COURSE_NOTEXIST.getStateInfo());
            return new ModelAndView("/404", map);
        }
        System.out.println(course);
        map.put("course", course);

        List<Video> videoList = videoService.getVideoListByCourseId(courseId);
        if (CollectionUtils.isEmpty(videoList)) {
            map.put("message", CourseEnum.VIDEO_NOTEXIST.getStateInfo());
            return new ModelAndView("/404", map);
        }
        for (Video v : videoList) {
            System.out.println(v);
        }

        map.put("videoList", videoList);
        List<Comment> commentList = commentService.getCommentList(courseId);
        if (CollectionUtils.isEmpty(commentList)) {
            map.put("message", CourseEnum.COMMENT_NOTEXIST.getStateInfo());
        }
        List<CommentVO> commentVOs = new ArrayList<CommentVO>();
        for(Comment comment : commentList){
            CommentVO vo = new CommentVO();
            Long userId =  comment.getUserId();
            if(userId != null){
                User user = userService.getUserById(userId);
                vo.setUseremail(user.getEmail());
            }
            vo.setCreateTime(comment.getCreateTime().toString());
            vo.setText(comment.getText());
            commentVOs.add(vo);
        }
        map.put("commentVOs", commentVOs);

        return new ModelAndView("/video", map);
    }


    /**
     * 添加评论
     * @param text
     * @param courseId
     * @param session
     * @param map
     * @return
     */
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public ModelAndView addComment(@RequestParam("text") String text, @RequestParam("courseId") Long courseId, HttpSession session, ModelMap map) {

        if (text == null || courseId == null || courseId == 0) {
            map.put("message",CourseEnum.PARAME_ERROR.getStateInfo());
            return new ModelAndView("redirect:/404",map);
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            map.put("message", UserEnum.USER_NOJD.getStateInfo());
            return new ModelAndView("redirect:/404",map);
        }
        Comment comment = new Comment();
        comment.setText(text);
        comment.setCreateTime(new Date());
        boolean addSuccess = commentService.addComment(user.getId(), courseId, comment);
        if (!addSuccess) {
            map.put("message",UserEnum.USER_ADDERROR.getStateInfo());
            return new ModelAndView("redirect:/404",map);
        }
        return new ModelAndView("redirect:/course/"+courseId+"/detail");
    }


}
