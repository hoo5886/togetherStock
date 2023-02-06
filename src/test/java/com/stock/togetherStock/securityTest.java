package com.stock.togetherStock;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.stock.togetherStock.config.WithAccount;
import com.stock.togetherStock.member.repository.MemberRepository;
import com.stock.togetherStock.post.service.PostService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class securityTest {

//    @Autowired
//    MockMvc mvc;
//
//    @Autowired
//    PostService postService;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @AfterEach
//    void afterEach() {
//        memberRepository.deleteAll();
//    }
//
//
//    @WithAccount("test@test.com")
//    @Test
//    void example() throws Exception {
//
//    }
}
