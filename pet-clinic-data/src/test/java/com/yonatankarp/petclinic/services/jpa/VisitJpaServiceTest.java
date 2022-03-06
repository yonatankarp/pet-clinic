package com.yonatankarp.petclinic.services.jpa;

import java.util.HashSet;
import java.util.Optional;
import com.yonatankarp.petclinic.model.Visit;
import com.yonatankarp.petclinic.repositories.VisitRepository;
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
class VisitJpaServiceTest {

    private static final Long VISIT_ID = 1L;

    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    private VisitJpaService visitJpaService;

    private Visit returnVisit;

    @BeforeEach
    void setUp() {
        returnVisit = Visit.builder().id(VISIT_ID).build();
    }

    @Test
    void findAll() {
        final var returnVisits = new HashSet<Visit>();
        returnVisits.add(Visit.builder().id(VISIT_ID).build());
        returnVisits.add(Visit.builder().id(2L).build());

        when(visitRepository.findAll()).thenReturn(returnVisits);

        final var visits = visitJpaService.findAll();

        assertNotNull(visits);
        assertEquals(2, visits.size());
        verify(visitRepository).findAll();
    }

    @Test
    void findById() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(returnVisit));

        final var visit = visitJpaService.findById(VISIT_ID);

        assertNotNull(visit);

        verify(visitRepository).findById(eq(VISIT_ID));
    }

    @Test
    void findByIdNotFound() {

        final Long nonExistPetId = 5L;

        when(visitRepository.findById(anyLong())).thenReturn(Optional.empty());

        final var visit = visitJpaService.findById(nonExistPetId);

        assertNull(visit);

        verify(visitRepository).findById(eq(nonExistPetId));
    }

    @Test
    void save() {
        final var visitToSave = Visit.builder().id(VISIT_ID).build();

        when(visitRepository.save(any())).thenReturn(returnVisit);

        final var savedVisit = visitJpaService.save(visitToSave);

        assertNotNull(savedVisit);

        verify(visitRepository).save(eq(visitToSave));
    }

    @Test
    void delete() {
        visitJpaService.delete(returnVisit);

        verify(visitRepository).delete(eq(returnVisit));
    }

    @Test
    void deleteById() {
        visitJpaService.deleteById(VISIT_ID);

        verify(visitRepository).deleteById(eq(VISIT_ID));
    }
}
