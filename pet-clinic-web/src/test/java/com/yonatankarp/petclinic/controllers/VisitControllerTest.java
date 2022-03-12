package com.yonatankarp.petclinic.controllers;

import java.time.LocalDate;
import com.yonatankarp.petclinic.model.Pet;
import com.yonatankarp.petclinic.services.PetService;
import com.yonatankarp.petclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    private static final Long OWNER_ID = 1L;
    private static final Long PET_ID = 1L;
    private static final String PETS_CREATE_OR_UPDATE_VISIT_FORM = "pets/create_or_update_visit_form";
    private static final String REDIRECT_OWNERS_1 = "redirect:/owners/{ownerId}";
    private static final String YET_ANOTHER_VISIT_DESCRIPTION = "yet another visit";

    private static final LocalDate VISIT_DATE = LocalDate.of(2018,11,11);

    @Mock
    private PetService petService;

    @Mock
    private VisitService visitService;

    @InjectMocks
    VisitController visitController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void initNewVisitForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(Pet.builder().build());

        mockMvc.perform(get("/owners/" + OWNER_ID + "/pets/" + PET_ID + "/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(PETS_CREATE_OR_UPDATE_VISIT_FORM));
    }

    @Test
    void processNewVisitForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(Pet.builder().build());

        mockMvc.perform(post("/owners/" + OWNER_ID + "/pets/" + PET_ID + "/visits/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("date",VISIT_DATE.toString())
                        .param("description", YET_ANOTHER_VISIT_DESCRIPTION))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_OWNERS_1))
                .andExpect(model().attributeExists("visit"));

        verify(visitService).save(any());
    }
}
