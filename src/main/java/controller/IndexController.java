package controller;

import model.Course;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CourseService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 首页数据
 * 1、课程列表
 * Created by admin on 2016/6/27.
 */
@Controller
public class IndexController {

    @Autowired
    private CourseService courseService;


    @RequestMapping(value = "index")
    public ModelAndView showIndex(ModelMap map, HttpSession session) {

        System.out.println("-----index-----");
        //课程列表数据
        List<Course> courseList = courseService.getCourseListByPage();
        map.put("courseList", courseList);

        //登录后展示用户信息
        User user = (User) session.getAttribute("user");
        if (user != null) {
            map.put("user", user);
        }

        return new ModelAndView("/index", map);
    }


}
