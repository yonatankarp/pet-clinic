package com.yonatankarp.petclinic.contorllers;

import java.util.HashSet;
import java.util.Set;
import com.yonatankarp.petclinic.model.Vet;
import com.yonatankarp.petclinic.services.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    private VetService vetService;

    @InjectMocks
    private VetController controller;

    private Set<Vet> vets;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        vets = new HashSet<>();
        vets.add(Vet.builder().id(1L).build());
        vets.add(Vet.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }



    @ParameterizedTest
    @ValueSource(strings = {"/vets", "/vets/", "/vets/index", "/vets/index.html", "/vets.html"})
    void listVets(final String url) throws Exception {
        when(vetService.findAll()).thenReturn(vets);

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("vets/index"))
                .andExpect(model().attribute("vets", hasSize(2)));
    }
}
