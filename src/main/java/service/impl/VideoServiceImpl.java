package service.impl;

import dao.VideoDao;
import model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.VideoService;

import java.util.List;

/**
 * Created by admin on 2016/6/27.
 */
@Service
public class VideoServiceImpl implements VideoService{

    @Autowired
    private VideoDao dao;

    @Override
    public List<Video> getVideoListByCourseId(Long courseId) {
        return dao.getVideoListByCourseId(courseId);
    }
}
