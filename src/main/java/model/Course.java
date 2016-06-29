package model;

import java.io.Serializable;

/**
 * 课程
 * Created by admin on 2016/6/27.
 */
public class Course {

    private Long id;
    private String name; //名字
    private int type; //类型
    private String introduce; //介绍
    private String url;//课程图片
    private long totalTime;//总时长

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", introduce='" + introduce + '\'' +
                ", url='" + url + '\'' +
                ", totalTime=" + totalTime +
                '}';
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
