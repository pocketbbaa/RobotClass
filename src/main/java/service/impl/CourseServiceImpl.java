package service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.CourseDao;
import dao.cache.RedisDao;
import model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import service.CourseService;
import utils.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/6/27.
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao dao;
    @Autowired
    private RedisDao<Course> redisDao;

    @Override
    public Page<Course> getCourseListByPage(Integer pageIndex, Integer pageSize) {

        Page<Course> page = new Page<>(pageIndex, pageSize);
        List<Course> courseList = dao.getCourseListByPage(page);
        page.setResult(courseList);
        return page;
    }

    @Override
    public Course getCourseById(Long id) {
        String key = "courseId" + id;
        Course course = (Course) redisDao.getObject(key);
        if (course == null) {
            course = dao.getCourseById(id);
            redisDao.setObject(key, course);
        }
        return course;
    }

    @Override
    public List<Course> getCourseTopN(int top) {
        String key = "top";
        List<String> list = redisDao.getList(key, new Long(top));
        List<Course> courseList = new ArrayList<>();
        for (String str : list) {
            Course course = JSON.parseObject(str, Course.class);
            courseList.add(course);
        }
        if (CollectionUtils.isEmpty(courseList)) {
            courseList = dao.getCourseTopN(top);
            redisDao.setList(key, courseList);
        }

        return courseList;
    }
}
