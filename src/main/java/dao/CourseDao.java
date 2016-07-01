package dao;

import model.Course;
import utils.Page;

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
    List<Course> getCourseListByPage(Page<Course> page);

    /**
     * 获取课程列表topN
     * @param top
     * @return
     */
    List<Course> getCourseTopN(int top);
}
