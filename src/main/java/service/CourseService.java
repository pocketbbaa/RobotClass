package service;

import model.Course;

import java.util.List;

/**
 * Created by admin on 2016/6/27.
 */
public interface CourseService {

    /**
     * 获取课程分页列表
     * @return
     */
    List<Course> getCourseListByPage();

    /**
     * 根据ID获取课程详情
     * @param id
     * @return
     */
    Course getCourseById(Long id);



}
