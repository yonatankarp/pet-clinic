package com.yonatankarp.petclinic.services.map;

import com.yonatankarp.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    private static final long OWNER_ID = 1L;
    private static final String LAST_NAME = "Smith";

    private OwnerMapService ownerMapService;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        final var owner = new Owner();
        owner.setId(OWNER_ID);
        owner.setLastName(LAST_NAME);
        ownerMapService.save(owner);
    }

    @Test
    void findAll() {
        final var owners = ownerMapService.findAll();

        assertEquals(OWNER_ID, owners.size());
    }

    @Test
    void findById() {
        final var owner = ownerMapService.findById(OWNER_ID);

        assertEquals(OWNER_ID, owner.getId());
    }

    @Test
    void saveExistingId() {
        final var ownerId = 2L;
        final var owner = new Owner();
        owner.setId(ownerId);

        final var savedOwner = ownerMapService.save(owner);

        assertEquals(ownerId, savedOwner.getId());
    }

    @Test
    void saveWithNoId() {
        final var savedOwner = ownerMapService.save(new Owner());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(OWNER_ID));

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(OWNER_ID);

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        final var smith = ownerMapService.findByLastName(LAST_NAME);

        assertNotNull(smith);
        assertEquals(OWNER_ID, smith.getId());
        assertEquals(LAST_NAME, smith.getLastName());
    }

    @Test
    void findByLastNameLowerCase() {
        final var smith = ownerMapService.findByLastName(LAST_NAME.toLowerCase());

        assertNotNull(smith);
        assertEquals(OWNER_ID, smith.getId());
        assertEquals(LAST_NAME, smith.getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        final var smith = ownerMapService.findByLastName("foo");

        assertNull(smith);
    }
}
