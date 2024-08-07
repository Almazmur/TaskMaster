package org.example.taskmanagement.repository;
import org.example.taskmanagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAuthorId(Long authorId, Pageable pageable);
    List<Task> findByAssigneeId(Long assigneeId, Pageable pageable);
}

