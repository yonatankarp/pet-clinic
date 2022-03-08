package com.yonatankarp.petclinic.services.map;

import java.util.Set;
import com.yonatankarp.petclinic.exceptions.PetTypeNotExistsException;
import com.yonatankarp.petclinic.model.Owner;
import com.yonatankarp.petclinic.model.Pet;
import com.yonatankarp.petclinic.model.PetType;
import com.yonatankarp.petclinic.services.OwnerService;
import com.yonatankarp.petclinic.services.PetService;
import com.yonatankarp.petclinic.services.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    @Override
    public Owner findById(final Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner save(final Owner owner) {
        if (owner != null) {
            if (owner.getPets() != null) {
                owner.getPets().forEach(this::savePet);
            }

            return super.save(owner);
        }


        return null;
    }

    private void savePet(final Pet pet) {
        final var savedPetType = savePetType(pet.getPetType());
        pet.setPetType(savedPetType);

        // Ensure that pet id is stored to our persistence layer
        if (pet.getId() == null) {
            final Pet savedPet = petService.save(pet);
            pet.setId(savedPet.getId());
        }
    }

    private PetType savePetType(final PetType petType) {
        if (petType == null) {
            throw new PetTypeNotExistsException("Pet type is required.");
        }

        // Ensure that pet type id is stored to our persistence layer
        if (petType.getId() == null) {
            return petTypeService.save(petType);
        }

        return petType;
    }

    @Override
    public void delete(final Owner owner) {
        super.delete(owner);
    }

    @Override
    public void deleteById(final Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return findAll()
                .stream().filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }
}
