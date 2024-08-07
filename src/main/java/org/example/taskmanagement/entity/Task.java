package org.example.taskmanagement.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    private String status;

    @Column
    private String priority;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @OneToMany(mappedBy = "task")
    private List<Comment> comments;
}