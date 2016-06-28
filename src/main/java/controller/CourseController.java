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

import java.util.List;

/**
 * 课程详情页
 * 1、课程信息
 * 2、视频目录
 * 3、评论分页列表
 * Created by admin on 2016/6/28.
 */
@RequestMapping("course")
@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private CommentService commentService;

    @RequestMapping(value="/{courseId}/detail",method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("courseId") Long courseId, ModelMap map){

        if(courseId == null){
            map.put("message", CourseEnum.COURSE_ERROR);
            return new ModelAndView("/course_not_found",map);
        }
        Course course = courseService.getCourseById(courseId);
        if(course == null){
            map.put("message",CourseEnum.COURSE_NOTEXIST);
            return new ModelAndView("/course_not_found");
        }
        map.put("course",course);

        List<Video> videoList = videoService.getVideoListByCourseId(courseId);
        if(CollectionUtils.isEmpty(videoList)){
            map.put("message",CourseEnum.VIDEO_NOTEXIST);
            return new ModelAndView("/course_not_found",map);
        }
        map.put("videoList",videoList);
        List<Comment> commentList = commentService.getCommentListByPage(courseId);
        if(CollectionUtils.isEmpty(commentList)){
            map.put("message",CourseEnum.COMMENT_NOTEXIST);
        }
        map.put("commentList",commentList);

        return new ModelAndView("/course/detail",map);
    }


}
