package service;

import model.Comment;

import java.util.List;

/**
 * Created by admin on 2016/6/27.
 */
public interface CommentService {

    /**
     * 根据课程ID获取课程评论分页列表
     * @param courseId
     * @return
     */
    List<Comment> getCommentList(Long courseId);

    /**
     * 添加评论
     * @param comment
     * @return
     */
    boolean addComment(Long userId,Long courseId,Comment comment);

    /**
     * 根据ID删除评论
     * @param id
     * @return
     */
    boolean delComment(Long id);



}
