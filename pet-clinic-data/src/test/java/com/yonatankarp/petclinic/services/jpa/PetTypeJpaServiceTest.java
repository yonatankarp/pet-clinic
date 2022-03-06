package com.yonatankarp.petclinic.services.jpa;

import java.util.HashSet;
import java.util.Optional;
import com.yonatankarp.petclinic.model.PetType;
import com.yonatankarp.petclinic.repositories.PetTypeRepository;
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
class PetTypeJpaServiceTest {

    private static final Long PET_TYPE_ID = 1L;

    @Mock
    private PetTypeRepository petTypeRepository;

    @InjectMocks
    private PetTypeJpaService petTypeJpaService;

    private PetType returnPetType;

    @BeforeEach
    void setUp() {
        returnPetType = PetType.builder().id(PET_TYPE_ID).build();
    }

    @Test
    void findAll() {
        final var returnPetTypes = new HashSet<PetType>();
        returnPetTypes.add(PetType.builder().id(PET_TYPE_ID).build());
        returnPetTypes.add(PetType.builder().id(2L).build());

        when(petTypeRepository.findAll()).thenReturn(returnPetTypes);

        final var petTypes = petTypeJpaService.findAll();

        assertNotNull(petTypes);
        assertEquals(2, petTypes.size());
        verify(petTypeRepository).findAll();
    }

    @Test
    void findById() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(returnPetType));

        final var petType = petTypeJpaService.findById(PET_TYPE_ID);

        assertNotNull(petType);

        verify(petTypeRepository).findById(eq(PET_TYPE_ID));
    }

    @Test
    void findByIdNotFound() {

        final Long nonExistPetId = 5L;

        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.empty());

        final var petType = petTypeJpaService.findById(nonExistPetId);

        assertNull(petType);

        verify(petTypeRepository).findById(eq(nonExistPetId));
    }

    @Test
    void save() {
        final var petTypeToSave = PetType.builder().id(PET_TYPE_ID).build();

        when(petTypeRepository.save(any())).thenReturn(returnPetType);

        final var savedPetType = petTypeJpaService.save(petTypeToSave);

        assertNotNull(savedPetType);

        verify(petTypeRepository).save(eq(petTypeToSave));
    }

    @Test
    void delete() {
        petTypeJpaService.delete(returnPetType);

        verify(petTypeRepository).delete(eq(returnPetType));
    }

    @Test
    void deleteById() {
        petTypeJpaService.deleteById(PET_TYPE_ID);

        verify(petTypeRepository).deleteById(eq(PET_TYPE_ID));
    }
}
