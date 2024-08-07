package org.example.taskmanagement.service;

import org.example.taskmanagement.entity.Task;
import org.springframework.data.domain.Pageable;

import org.example.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> findByAuthorId(Long authorId, Pageable pageable) {
        return taskRepository.findByAuthorId(authorId, pageable);
    }

    public List<Task> findByAssigneeId(Long assigneeId, Pageable pageable) {
        return taskRepository.findByAssigneeId(assigneeId, pageable);
    }
}

