package service.impl;

import dao.CourseDao;
import model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CourseService;

import java.util.List;

/**
 * Created by admin on 2016/6/27.
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao dao;

    @Override
    public List<Course> getCourseListByPage() {

        return null;
    }


    @Override
    public Course getCourseById(Long id) {

        return null;
    }
}
