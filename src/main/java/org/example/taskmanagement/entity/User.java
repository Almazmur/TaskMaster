package org.example.taskmanagement.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy = "author")
    private List<Task> tasksAuthored;

    @OneToMany(mappedBy = "assignee")
    private List<Task> tasksAssigned;
}
