package service.impl;

import dao.CourseDao;
import model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CourseService;
import utils.Page;

import java.util.List;

/**
 * Created by admin on 2016/6/27.
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao dao;

    @Override
    public Page<Course> getCourseListByPage(Integer pageIndex, Integer pageSize) {

        Page<Course> page = new Page<>(pageIndex, pageSize);
        List<Course> courseList = dao.getCourseListByPage(page);
        page.setResult(courseList);
        return page;
    }


    @Override
    public Course getCourseById(Long id) {

        return dao.getCourseById(id);
    }

    @Override
    public List<Course> getCourseTopN(int top) {

        return dao.getCourseTopN(top);
    }
}
