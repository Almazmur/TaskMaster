package org.example.taskmanagement.controller;

import org.example.taskmanagement.entity.Comment;
import org.example.taskmanagement.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment savedComment = commentService.save(comment);
        return ResponseEntity.ok(savedComment);
    }
}