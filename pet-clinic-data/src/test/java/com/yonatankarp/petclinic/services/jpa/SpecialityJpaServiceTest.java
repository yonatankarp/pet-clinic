package com.yonatankarp.petclinic.services.jpa;

import java.util.HashSet;
import java.util.Optional;
import com.yonatankarp.petclinic.model.Speciality;
import com.yonatankarp.petclinic.repositories.SpecialtyRepository;
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
class SpecialityJpaServiceTest {

    private static final Long SPECIALITY_ID = 1L;

    @Mock
    private SpecialtyRepository specialtyRepository;

    @InjectMocks
    private SpecialityJpaService specialityJpaService;

    private Speciality returnSpeciality;

    @BeforeEach
    void setUp() {
        returnSpeciality = Speciality.builder().id(SPECIALITY_ID).build();
    }

    @Test
    void findAll() {
        final var returnSpecialities = new HashSet<Speciality>();
        returnSpecialities.add(Speciality.builder().id(SPECIALITY_ID).build());
        returnSpecialities.add(Speciality.builder().id(2L).build());

        when(specialtyRepository.findAll()).thenReturn(returnSpecialities);

        final var specialities = specialityJpaService.findAll();

        assertNotNull(specialities);
        assertEquals(2, specialities.size());
        verify(specialtyRepository).findAll();
    }

    @Test
    void findById() {
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.of(returnSpeciality));

        final var speciality = specialityJpaService.findById(SPECIALITY_ID);

        assertNotNull(speciality);

        verify(specialtyRepository).findById(eq(SPECIALITY_ID));
    }

    @Test
    void findByIdNotFound() {

        final Long nonExistPetId = 5L;

        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.empty());

        final var speciality = specialityJpaService.findById(nonExistPetId);

        assertNull(speciality);

        verify(specialtyRepository).findById(eq(nonExistPetId));
    }

    @Test
    void save() {
        final var specialityToSave = Speciality.builder().id(SPECIALITY_ID).build();

        when(specialtyRepository.save(any())).thenReturn(returnSpeciality);

        final var savedSpeciality = specialityJpaService.save(specialityToSave);

        assertNotNull(savedSpeciality);

        verify(specialtyRepository).save(eq(specialityToSave));
    }

    @Test
    void delete() {
        specialityJpaService.delete(returnSpeciality);

        verify(specialtyRepository).delete(eq(returnSpeciality));
    }

    @Test
    void deleteById() {
        specialityJpaService.deleteById(SPECIALITY_ID);

        verify(specialtyRepository).deleteById(eq(SPECIALITY_ID));
    }
}
