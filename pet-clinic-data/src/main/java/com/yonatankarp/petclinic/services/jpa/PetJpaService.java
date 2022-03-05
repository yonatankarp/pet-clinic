package com.yonatankarp.petclinic.services.jpa;

import java.util.HashSet;
import java.util.Set;
import com.yonatankarp.petclinic.model.Pet;
import com.yonatankarp.petclinic.repositories.PetRepository;
import com.yonatankarp.petclinic.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile("jpa")
public class PetJpaService implements PetService {

    private final PetRepository petRepository;

    @Override
    public Set<Pet> findAll() {
        final Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(final Long id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public Pet save(final Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public void delete(final Pet pet) {
        petRepository.delete(pet);
    }

    @Override
    public void deleteById(final Long id) {
        petRepository.deleteById(id);
    }
}
