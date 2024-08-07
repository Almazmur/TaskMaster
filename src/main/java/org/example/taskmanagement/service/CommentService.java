package org.example.taskmanagement.service;

import org.example.taskmanagement.entity.Comment;
import org.example.taskmanagement.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> findByTaskId(Long taskId) {
        return commentRepository.findByTaskId(taskId);
    }
}
