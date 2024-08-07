package org.example.taskmanagement.controller;

import org.example.taskmanagement.entity.Task;
import org.example.taskmanagement.entity.User;
import org.example.taskmanagement.service.TaskService;
import org.example.taskmanagement.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @MockBean
    private UserService userService; // Мокируем UserService

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testCreateTask() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");

        User user = new User();
        user.setEmail("testuser");
        // Настройте остальные свойства пользователя

        when(userService.findByEmail("testuser")).thenReturn(user); // Мокируем метод findByEmail
        when(taskService.save(any(Task.class))).thenReturn(task);

        mockMvc.perform(post("/tasks")
                        .contentType("application/json")
                        .content("{\"title\": \"Test Task\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Test Task")));
    }
}