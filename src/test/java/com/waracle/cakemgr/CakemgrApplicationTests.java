package com.waracle.cakemgr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CakemgrApplicationTests {

    @Autowired
    private ApplicationContext context;

    @WithMockUser
    @Test
    void contextLoadsWithSecurityAuthentication() {
        assertThat(context).isInstanceOf(WebApplicationContext.class);
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNotNull();
    }

    @Test
    void contextLoadsWithoutSecurityAuthentication() {
        assertThat(context).isInstanceOf(WebApplicationContext.class);
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

}
