package dao;

import model.Course;

import java.util.List;

/**
 * Created by admin on 2016/6/27.
 */
public interface CourseDao {

    /**
     * 根据课程ID获取课程详情
     * @param id
     * @return
     */
    Course getCourseById(Long id);

    /**
     * 获取课程分页信息
     * @return
     */
    List<Course> getCourseListByPage();
}
