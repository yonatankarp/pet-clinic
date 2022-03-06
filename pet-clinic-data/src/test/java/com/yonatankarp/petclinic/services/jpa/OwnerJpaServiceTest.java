package com.yonatankarp.petclinic.services.jpa;

import java.util.HashSet;
import java.util.Optional;
import com.yonatankarp.petclinic.model.Owner;
import com.yonatankarp.petclinic.repositories.OwnerRepository;
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
class OwnerJpaServiceTest {

    private static final Long OWNER_ID = 1L;
    private static final String LAST_NAME = "Smith";

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerJpaService ownerJpaService;

    private Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(OWNER_ID).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        final var smith = ownerJpaService.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, smith.getLastName());

        verify(ownerRepository).findByLastName(eq("Smith"));
    }

    @Test
    void findAll() {
        final var returnOwners = new HashSet<Owner>();
        returnOwners.add(Owner.builder().id(OWNER_ID).build());
        returnOwners.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(returnOwners);

        final var owners = ownerJpaService.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
        verify(ownerRepository).findAll();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        final var owner = ownerJpaService.findById(OWNER_ID);

        assertNotNull(owner);

        verify(ownerRepository).findById(eq(OWNER_ID));
    }

    @Test
    void findByIdNotFound() {

        final Long nonExistOwnerId = 5L;

        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        final var owner = ownerJpaService.findById(nonExistOwnerId);

        assertNull(owner);

        verify(ownerRepository).findById(eq(nonExistOwnerId));
    }

    @Test
    void save() {
        final var ownerToSave = Owner.builder().id(OWNER_ID).build();

        when(ownerRepository.save(any())).thenReturn(returnOwner);

        final var savedOwner = ownerJpaService.save(ownerToSave);

        assertNotNull(savedOwner);

        verify(ownerRepository).save(eq(ownerToSave));
    }

    @Test
    void delete() {
        ownerJpaService.delete(returnOwner);

        verify(ownerRepository).delete(eq(returnOwner));
    }

    @Test
    void deleteById() {
        ownerJpaService.deleteById(OWNER_ID);

        verify(ownerRepository).deleteById(eq(OWNER_ID));
    }
}
