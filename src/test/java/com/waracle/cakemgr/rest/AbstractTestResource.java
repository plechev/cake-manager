package com.waracle.cakemgr.rest;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class AbstractTestResource {

    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = webAppContextSetup(wac)
            .apply(springSecurity())
            .build();
    }
}
