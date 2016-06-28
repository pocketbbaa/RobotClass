package dao;

import model.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2016/6/27.
 */
public interface CommentDao {

    /**
     * 根据课程ID获取课程评论分页列表
     *
     * @param courseId
     * @return
     */
    List<Comment> getCommentListByPage(Long courseId);

    /**
     * 添加评论
     *
     * @param userId
     * @param courseId
     * @param text
     * @param createTime
     * @return
     */
    int addComment(@Param("userId") Long userId, @Param("courseId") Long courseId,
                   @Param("text") String text, @Param("createTime") Date createTime);

    /**
     * 根据ID删除评论
     *
     * @param id
     * @return
     */
    int delComment(Long id);

}
