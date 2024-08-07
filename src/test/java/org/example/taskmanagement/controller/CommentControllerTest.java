package org.example.taskmanagement.controller;

import org.example.taskmanagement.entity.Comment;
import org.example.taskmanagement.security.JwtUtil;
import org.example.taskmanagement.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testAddComment() throws Exception {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setContent("Test Comment");

        when(commentService.save(any(Comment.class))).thenReturn(comment);

        mockMvc.perform(post("/comments")
                        .contentType("application/json")
                        .content("{\"content\": \"Test Comment\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", is("Test Comment")));
    }
}
