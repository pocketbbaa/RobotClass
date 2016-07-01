package service;

import model.Course;
import utils.Page;

import java.util.List;

/**
 * Created by admin on 2016/6/27.
 */
public interface CourseService {

    /**
     * 获取课程分页列表
     * @return
     */
    Page<Course> getCourseListByPage(Integer pageIndex, Integer pageSize);

    /**
     * 根据ID获取课程详情
     * @param id
     * @return
     */
    Course getCourseById(Long id);

    /**
     * 获取课程列表topN
     * @param top
     * @return
     */
    List<Course> getCourseTopN(int top);



}
