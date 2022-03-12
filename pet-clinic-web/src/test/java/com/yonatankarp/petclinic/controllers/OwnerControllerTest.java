package com.yonatankarp.petclinic.controllers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import com.yonatankarp.petclinic.model.Owner;
import com.yonatankarp.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final Long OWNER_ID_1 = 1L;
    private static final Long OWNER_ID_2 = 2L;

    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private OwnerController controller;

    private Set<Owner> owners;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(OWNER_ID_1).build());
        owners.add(Owner.builder().id(OWNER_ID_2).build());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/find_owners"))
                .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(ownerService);
    }

    @Test
    void processFindFormReturnMany() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(owners);

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/owners_list"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {
        final var owner = owners.iterator().next();

        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Collections.singleton(owner));

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/" + owner.getId()));
    }

    @Test
    void processFindFormEmptyReturnMany() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(owners);

        mockMvc.perform(get("/owners").param("lastName", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/owners_list"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void displayOwners() throws Exception {
        final var owner = owners.iterator().next();
        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(get("/owners/" + owner.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/owner_details"))
                .andExpect(model().attribute("owner", owner));
    }

    @Test
    void initCreateForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/create_or_update_owner_form"))
                .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(ownerService);
    }

    @Test
    void processCreationForm() throws Exception {
        final var owner = owners.iterator().next();
        when(ownerService.save(any())).thenReturn(owner);

        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/" + owner.getId()))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(any(Owner.class));
    }

    @Test
    void initUpdateOwnerForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/create_or_update_owner_form"))
                .andExpect(model().attributeExists("owner"));

        verifyNoMoreInteractions(ownerService);
    }

    @Test
    void processUpdateOwnerForm() throws Exception {
        final var owner = owners.iterator().next();
        when(ownerService.save(any())).thenReturn(owner);

        mockMvc.perform(post("/owners/" + owner.getId() + "/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/" + owner.getId()))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(any(Owner.class));
    }
}
