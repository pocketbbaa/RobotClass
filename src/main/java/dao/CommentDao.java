package dao;

import model.Comment;

import java.util.List;

/**
 * Created by admin on 2016/6/27.
 */
public interface CommentDao {

    /**
     *根据课程ID获取课程评论分页列表
     * @param courseId
     * @return
     */
    List<Comment> getCommentListByPage(Long courseId);

}
