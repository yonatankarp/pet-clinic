package com.yonatankarp.petclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    private IndexController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        controller = new IndexController();

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void index() throws Exception {
          mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void indexUrl() throws Exception {
        mockMvc.perform(get("/index/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void indexHtml() throws Exception {
        mockMvc.perform(get("/index.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void oupsHandler() throws Exception {
        mockMvc.perform(get("/oups"))
                .andExpect(status().isOk())
                .andExpect(view().name("not_implemented"));

    }
}
