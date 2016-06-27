package service.impl;

import model.Video;
import org.springframework.stereotype.Service;
import service.VideoService;

import java.util.List;

/**
 * Created by admin on 2016/6/27.
 */
@Service
public class VideoServiceImpl implements VideoService{


    @Override
    public List<Video> getVideoListByCourseId(Long courseId) {


        return null;
    }
}
