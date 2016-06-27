package service;

import model.Video;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by admin on 2016/6/27.
 */
@Service
public interface VideoService {

    /**
     * 根据课程ID获取视频数据列表
     * @param courseId
     * @return
     */
    List<Video> getVideoListByCourseId(Long courseId);



}
