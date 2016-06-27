package model;

import java.util.Date;

/**
 * 评论
 * Created by admin on 2016/6/27.
 */
public class Comment {

    private Long id;
    private String text; //评论类容
    private Date createTime; //创建时间
    private Long userId; //用户ID
    private Long courseId; //课程ID

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", createTime=" + createTime +
                ", userId=" + userId +
                ", courseId=" + courseId +
                '}';
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
