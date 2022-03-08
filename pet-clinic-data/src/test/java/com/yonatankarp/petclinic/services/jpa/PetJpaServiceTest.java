package com.yonatankarp.petclinic.services.jpa;

import java.util.HashSet;
import java.util.Optional;
import com.yonatankarp.petclinic.model.Pet;
import com.yonatankarp.petclinic.repositories.PetRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetJpaServiceTest {

    private static final Long PET_ID = 1L;

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetJpaService petJpaService;

    private Pet returnPet;

    @BeforeEach
    void setUp() {
        returnPet = Pet.builder().id(PET_ID).build();
    }

    @Test
    void findAll() {
        final var returnPets = new HashSet<Pet>();
        returnPets.add(Pet.builder().id(PET_ID).build());
        returnPets.add(Pet.builder().id(2L).build());

        when(petRepository.findAll()).thenReturn(returnPets);

        final var pets = petJpaService.findAll();

        assertNotNull(pets);
        assertEquals(2, pets.size());
        verify(petRepository).findAll();
    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnPet));

        final var pet = petJpaService.findById(PET_ID);

        assertNotNull(pet);

        verify(petRepository).findById(PET_ID);
    }

    @Test
    void findByIdNotFound() {

        final Long nonExistPetId = 5L;

        when(petRepository.findById(anyLong())).thenReturn(Optional.empty());

        final var pet = petJpaService.findById(nonExistPetId);

        assertNull(pet);

        verify(petRepository).findById(nonExistPetId);
    }

    @Test
    void save() {
        final var petToSave = Pet.builder().id(PET_ID).build();

        when(petRepository.save(any())).thenReturn(returnPet);

        final var savedPet = petJpaService.save(petToSave);

        assertNotNull(savedPet);

        verify(petRepository).save(petToSave);
    }

    @Test
    void delete() {
        petJpaService.delete(returnPet);

        verify(petRepository).delete(returnPet);
    }

    @Test
    void deleteById() {
        petJpaService.deleteById(PET_ID);

        verify(petRepository).deleteById(PET_ID);
    }
}
