package com.stock.togetherStock.member;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private MockMvc mvc ;

    @MockBean
    private FilterChainProxy proxy;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(ctx)
            .apply(springSecurity()) // (1)
            .addFilter(proxy)
            .build();
    }

    @Test
    @DisplayName("로그인 기능 테스트")
    public void loginTest() throws Exception {
        mvc
            .perform(formLogin().user("u").password("123"));
    }

}