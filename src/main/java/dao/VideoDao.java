package dao;

import model.Video;

import java.util.List;

/**
 * Created by admin on 2016/6/27.
 */
public interface VideoDao {

    /**
     * 根据课程ID获取视频列表
     * @param courseId
     * @return
     */
    List<Video> getVideoListByCourseId(Long courseId);

}
