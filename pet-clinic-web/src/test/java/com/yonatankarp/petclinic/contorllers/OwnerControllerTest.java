package com.yonatankarp.petclinic.contorllers;

import java.util.HashSet;
import java.util.Set;
import com.yonatankarp.petclinic.model.Owner;
import com.yonatankarp.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @ParameterizedTest
    @ValueSource(strings = {"/owners", "/owners/", "/owners/index", "/owners/index.html"})
    void listOwners(final String url) throws Exception {
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("not_implemented"));

        Mockito.verifyNoInteractions(ownerService);
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
}
