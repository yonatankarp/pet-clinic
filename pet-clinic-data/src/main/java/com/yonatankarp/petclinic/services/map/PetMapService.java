package com.yonatankarp.petclinic.services.map;

import java.util.Set;
import com.yonatankarp.petclinic.model.Pet;
import com.yonatankarp.petclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {

    @Override
    public Pet findById(final Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(final Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(final Pet pet) {
        super.delete(pet);
    }

    @Override
    public Pet save(final Pet pet) {
        return super.save(pet);
    }
}
