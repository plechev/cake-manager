package com.waracle.cakemgr.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.domain.Cake;
import com.waracle.cakemgr.service.CakeService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CakeResource.class)
@WithMockUser
class CakeResourceTest extends AbstractTestResource {

    @MockBean
    private CakeService serviceMock;

    @Captor
    private ArgumentCaptor<Cake> cakeCaptor;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldReturnAllCakes() throws Exception {

        final Cake cake1 = new Cake();
        cake1.setTitle("Cake 1");
        cake1.setDescription("Desc 1");
        cake1.setImage("image1");
        cake1.setId(1);

        final List<Cake> allCakes = List.of(cake1);
        when(serviceMock.getAvailableCakes()).thenReturn(allCakes);

        mvc.perform(get("/cakes"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().json("[{\"id\":1,\"title\":\"Cake 1\",\"description\":\"Desc 1\",\"image\":\"image1\"}]", true));
    }

    @Test
    void shouldReturnNoCakes() throws Exception {

        when(serviceMock.getAvailableCakes()).thenReturn(Collections.emptyList());

        mvc.perform(get("/cakes"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().json("[]", true));
    }

    @Test
    void shouldCreateACake() throws Exception {

        final CakeRequest cakeRequest = new CakeRequest();
        cakeRequest.setTitle("Cake 1");
        cakeRequest.setDescription("Desc 1");
        cakeRequest.setImage("image1");

        mvc.perform(
            post("/cakes")
                .contentType("application/json")
                .content(mapper.writeValueAsString(cakeRequest))
        )
            .andDo(print())
            .andExpect(status().isCreated());

        verify(serviceMock).createCake(cakeCaptor.capture());

        assertThat(cakeCaptor.getValue())
            .hasFieldOrPropertyWithValue("title", cakeRequest.getTitle())
            .hasFieldOrPropertyWithValue("description", cakeRequest.getDescription())
            .hasFieldOrPropertyWithValue("image", cakeRequest.getImage());
    }

    @Test
    void shouldRejectCreatingAnInvalidCake() throws Exception {

        final CakeRequest cakeRequest = new CakeRequest();

        mvc.perform(
            post("/cakes")
                .contentType("application/json")
                .content(mapper.writeValueAsString(cakeRequest))
        )
            .andDo(print())
            .andExpect(status().isBadRequest());

        verifyNoInteractions(serviceMock);
    }

    @Test
    void shouldDeleteACake() throws Exception {

        mvc.perform(delete("/cakes/1"))
            .andDo(print())
            .andExpect(status().isNoContent());

        verify(serviceMock).deleteCake(eq(1));
    }

}
