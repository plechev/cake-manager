package com.waracle.cakemgr.rest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserResource.class)
class UserResourceTest extends AbstractTestResource {

    @Test
    @WithMockUser(value = "manager")
    void shouldReturnUserDetails() throws Exception {
        // TODO improve tests to cover oAuth security context

        mvc.perform(get("/user"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().json("{\"username\":\"manager\"}", true));
    }

}
