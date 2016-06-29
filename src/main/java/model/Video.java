package model;

import java.io.Serializable;

/**
 * 视频
 * Created by admin on 2016/6/27.
 */
public class Video {

    private Long id;
    private String name; //视频名字
    private String videoId; //视频ID
    private String videoUrl; //视频URL
    private Long courseId; //课程ID
    private String introduce; //介绍
    private long time; //视频时长

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", videoId='" + videoId + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", courseId=" + courseId +
                ", introduce='" + introduce + '\'' +
                ", time=" + time +
                '}';
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
