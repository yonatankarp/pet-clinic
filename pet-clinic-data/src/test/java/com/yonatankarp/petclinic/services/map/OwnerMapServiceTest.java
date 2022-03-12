package com.yonatankarp.petclinic.services.map;

import com.yonatankarp.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class OwnerMapServiceTest {

    private static final Long OWNER_ID_1 = 1L;
    private static final String OWNER_LAST_NAME_1 = "Smith";
    private static final Long OWNER_ID_2 = 2L;
    private static final String OWNER_LAST_NAME_2 = "Smooth";

    private OwnerMapService ownerMapService;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        ownerMapService.save(Owner.builder().id(OWNER_ID_1).lastName(OWNER_LAST_NAME_1).build());
    }

    @Test
    void findAll() {
        final var ownerSet = ownerMapService.findAll();

        assertEquals(1, ownerSet.size());
    }

    @Test
    void findByExistingId() {
        final var owner = ownerMapService.findById(OWNER_ID_1);

        assertEquals(OWNER_ID_1, owner.getId());
    }

    @Test
    void findByIdNotExistingId() {

        final var owner = ownerMapService.findById(5L);

        assertNull(owner);
    }

    @Test
    void findByIdNullId() {

        final var owner = ownerMapService.findById(null);

        assertNull(owner);
    }

    @Test
    void saveExistingId() {
        final Long id = 2L;

        final var owner2 = Owner.builder().id(id).build();

        final var savedOwner = ownerMapService.save(owner2);

        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveDuplicateId() {

        final Long id = 1L;

        final var owner2 = Owner.builder().id(id).build();

        final var savedOwner = ownerMapService.save(owner2);

        assertEquals(id, savedOwner.getId());
        assertEquals(1, ownerMapService.findAll().size());
    }

    @Test
    void saveNoId() {

        final var savedOwner = ownerMapService.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void deleteOwner() {
        ownerMapService.delete(ownerMapService.findById(OWNER_ID_1));

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteWithWrongId() {

        final var owner = Owner.builder().id(5L).build();

        ownerMapService.delete(owner);

        assertEquals(1, ownerMapService.findAll().size());
    }

    @Test
    void deleteWithNullId() {

        final var owner = Owner.builder().build();

        ownerMapService.delete(owner);

        assertEquals(1, ownerMapService.findAll().size());
    }

    @Test
    void deleteNull() {

        ownerMapService.delete(null);

        assertEquals(1, ownerMapService.findAll().size());
    }

    @Test
    void deleteByIdCorrectId() {
        ownerMapService.deleteById(OWNER_ID_1);

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteByIdWrongId() {

        ownerMapService.deleteById(5L);

        assertEquals(1, ownerMapService.findAll().size());
    }

    @Test
    void deleteByIdNullId() {

        ownerMapService.deleteById(null);

        assertEquals(1, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        final var smith = ownerMapService.findByLastName(OWNER_LAST_NAME_1);

        assertNotNull(smith);

        assertEquals(OWNER_ID_1, smith.getId());
        assertEquals(OWNER_LAST_NAME_1, smith.getLastName());
    }

    @Test
    void findByLastNameLowerCase() {
        final var smith = ownerMapService.findByLastName(OWNER_LAST_NAME_1.toLowerCase());

        assertNotNull(smith);

        assertEquals(OWNER_ID_1, smith.getId());
        assertEquals(OWNER_LAST_NAME_1, smith.getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        final var owner = ownerMapService.findByLastName("foo");

        assertNull(owner);
    }

    @Test
    void findByLastNameLikeInMiddleName() {
        final var owners = ownerMapService.findAllByLastNameLike("th");

        assertNotNull(owners);
        assertEquals(1, owners.size());
        final var owner = owners.iterator().next();
        assertEquals(OWNER_ID_1, owner.getId());
        assertEquals(OWNER_LAST_NAME_1, owner.getLastName());
    }

    @Test
    void findByLastNameLikeCaseInsensitive() {
        final var owners = ownerMapService.findAllByLastNameLike("smi");

        assertNotNull(owners);
        assertEquals(1, owners.size());
        final var owner = owners.iterator().next();
        assertEquals(OWNER_ID_1, owner.getId());
        assertEquals(OWNER_LAST_NAME_1, owner.getLastName());
    }

    @Test
    void findByLastNameLikeReturnsOne() {
        ownerMapService.save(Owner.builder().id(OWNER_ID_2).lastName("smooth").build());

        final var owners = ownerMapService.findAllByLastNameLike("Smi");

        assertNotNull(owners);
        assertEquals(1, owners.size());
        final var owner = owners.iterator().next();
        assertEquals(OWNER_ID_1, owner.getId());
        assertEquals(OWNER_LAST_NAME_1, owner.getLastName());
    }

    @Test
    void findByLastNameLikeReturnsMany() {
        ownerMapService.save(Owner.builder().id(OWNER_ID_2).lastName(OWNER_LAST_NAME_2).build());

        final var owners = ownerMapService.findAllByLastNameLike("Sm");

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }
}
