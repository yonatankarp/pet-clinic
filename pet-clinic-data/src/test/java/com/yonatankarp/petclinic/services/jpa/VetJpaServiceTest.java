package com.yonatankarp.petclinic.services.jpa;

import java.util.HashSet;
import java.util.Optional;
import com.yonatankarp.petclinic.model.Vet;
import com.yonatankarp.petclinic.repositories.VetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VetJpaServiceTest {

    private static final Long VET_ID = 1L;

    @Mock
    private VetRepository vetRepository;

    @InjectMocks
    private VetJpaService vetJpaService;

    private Vet returnVet;

    @BeforeEach
    void setUp() {
        returnVet = Vet.builder().id(VET_ID).build();
    }

    @Test
    void findAll() {
        final var returnVets = new HashSet<Vet>();
        returnVets.add(Vet.builder().id(VET_ID).build());
        returnVets.add(Vet.builder().id(2L).build());

        when(vetRepository.findAll()).thenReturn(returnVets);

        final var vets = vetJpaService.findAll();

        assertNotNull(vets);
        assertEquals(2, vets.size());
        verify(vetRepository).findAll();
    }

    @Test
    void findById() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(returnVet));

        final var vet = vetJpaService.findById(VET_ID);

        assertNotNull(vet);

        verify(vetRepository).findById(eq(VET_ID));
    }

    @Test
    void findByIdNotFound() {

        final Long nonExistPetId = 5L;

        when(vetRepository.findById(anyLong())).thenReturn(Optional.empty());

        final var vet = vetJpaService.findById(nonExistPetId);

        assertNull(vet);

        verify(vetRepository).findById(eq(nonExistPetId));
    }

    @Test
    void save() {
        final var vetToSave = Vet.builder().id(VET_ID).build();

        when(vetRepository.save(any())).thenReturn(returnVet);

        final var savedVet = vetJpaService.save(vetToSave);

        assertNotNull(savedVet);

        verify(vetRepository).save(eq(vetToSave));
    }

    @Test
    void delete() {
        vetJpaService.delete(returnVet);

        verify(vetRepository).delete(eq(returnVet));
    }

    @Test
    void deleteById() {
        vetJpaService.deleteById(VET_ID);

        verify(vetRepository).deleteById(eq(VET_ID));
    }
}
