package service.impl;

import dao.CommentDao;
import model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CommentService;
import utils.Page;

import java.util.List;

/**
 * Created by admin on 2016/6/27.
 */
@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentDao dao;

    @Override
    public List<Comment> getCommentList(Long courseId) {

        return dao.getCommentList(courseId);
    }

    @Override
    public boolean addComment(Long userId, Long courseId, Comment comment) {
        int i = dao.addComment(userId, courseId, comment.getText(), comment.getCreateTime());
        return i == 1;
    }

    @Override
    public boolean delComment(Long id) {
        int i = dao.delComment(id);
        return i == 1;
    }
}
