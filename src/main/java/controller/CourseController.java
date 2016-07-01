package controller;

import enums.CourseEnum;
import model.Comment;
import model.Course;
import model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.CommentService;
import service.CourseService;
import service.VideoService;
import utils.Page;
import vo.coursesVO;

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
        List<Comment> commentList = commentService.getCommentListByPage(courseId);
        if (CollectionUtils.isEmpty(commentList)) {
            map.put("message", CourseEnum.COMMENT_NOTEXIST.getStateInfo());
        }
        map.put("commentList", commentList);

        return new ModelAndView("/video", map);
    }


}
