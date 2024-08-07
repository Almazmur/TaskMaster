package org.example.taskmanagement.controller;
import org.example.taskmanagement.entity.Task;
import org.example.taskmanagement.entity.User;
import org.example.taskmanagement.service.TaskService;
import org.example.taskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task, Principal principal) {
        String email = principal.getName(); // Получите email из Principal
        User author = userService.findByEmail(email); // Найдите пользователя по email
        task.setAuthor(author);
        return ResponseEntity.ok(taskService.save(task));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Task>> getTasksByAuthor(@PathVariable Long authorId, Pageable pageable) {
        return ResponseEntity.ok(taskService.findByAuthorId(authorId, pageable));
    }

    @GetMapping("/assignee/{assigneeId}")
    public ResponseEntity<List<Task>> getTasksByAssignee(@PathVariable Long assigneeId, Pageable pageable) {
        return ResponseEntity.ok(taskService.findByAssigneeId(assigneeId, pageable));
    }
}
