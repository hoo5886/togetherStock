package com.stock.togetherStock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.stock.togetherStock.comment.domain.CommentDto;
import com.stock.togetherStock.comment.repository.CommentRepository;
import com.stock.togetherStock.post.repository.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class securityTest {

    @Autowired
    MockMvc mvc;

    PostRepository postRepository;

    protected CommentDto createCommentDto() {
        return CommentDto.builder()
            .commentContent("테스트용 코멘트")
            .build();
    }

    @Test
    @DisplayName("인증 후 댓글 작성 성공")
    @WithMockUser(username = "test@test.com", password="1234", roles="USER")
    @Transactional
    public void Post() throws Exception {

        String json = new Gson().toJson(createCommentDto());

        mvc.perform(post("/post/4/comment/write")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isFound())
            .andExpect(redirectedUrl("/post/4"))
            .andExpect(status().is3xxRedirection());
    }


}
