package com.yonatankarp.petclinic.controllers;

import java.util.HashSet;
import java.util.Set;
import com.yonatankarp.petclinic.model.Owner;
import com.yonatankarp.petclinic.model.Pet;
import com.yonatankarp.petclinic.model.PetType;
import com.yonatankarp.petclinic.services.OwnerService;
import com.yonatankarp.petclinic.services.PetService;
import com.yonatankarp.petclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.equalTo;
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
class PetControllerTest {

    private static final Long OWNER_ID = 1L;
    private static final Long PET_ID_1 = 1L;
    private static final String PET_NAME_1 = "Dog";
    private static final Long PET_ID_2 = 2L;
    private static final String PET_NAME_2 = "Cat";

    @Mock
    private PetService petService;

    @Mock
    private OwnerService ownerService;

    @Mock
    private PetTypeService petTypeService;

    @InjectMocks
    PetController petController;

    MockMvc mockMvc;

    private Owner owner;
    Set<PetType> petTypes;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(OWNER_ID).build();

        petTypes = new HashSet<>() {{
            add(PetType.builder().id(PET_ID_1).name(PET_NAME_1).build());
            add(PetType.builder().id(PET_ID_2).name(PET_NAME_2).build());
        }};

        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    void initCreationForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(get("/owners/" + owner.getId() + "/pets/new"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owner", equalTo(owner)))
                .andExpect(model().attribute("petTypes", equalTo(petTypes)))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("pets/create_or_update_pet_form"));
    }

    @Test
    void processCreationForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(post("/owners/" + owner.getId() + "/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attribute("owner", equalTo(owner)))
                .andExpect(model().attribute("petTypes", equalTo(petTypes)))
                .andExpect(view().name("redirect:/owners/" + owner.getId()));

        verify(petService).save(any());
    }

    @Test
    void initUpdateForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
        final var petToUpdated = Pet.builder().id(PET_ID_2).build();
        when(petService.findById(anyLong())).thenReturn(petToUpdated);

        mockMvc.perform(get("/owners/" + owner.getId() + "/pets/" + petToUpdated.getId() + "/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owner", equalTo(owner)))
                .andExpect(model().attribute("petTypes", equalTo(petTypes)))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("pets/create_or_update_pet_form"));
    }

    @Test
    void processUpdateForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
        final var petToUpdated = Pet.builder().id(PET_ID_2).build();

        mockMvc.perform(post("/owners/" + owner.getId() + "/pets/" + petToUpdated.getId() + "/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attribute("owner", equalTo(owner)))
                .andExpect(model().attribute("petTypes", equalTo(petTypes)))
                .andExpect(view().name("redirect:/owners/" + owner.getId()));

        verify(petService).save(any());
    }
}
