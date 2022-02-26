package com.yonatankarp.petclinic.services;

import java.util.Set;
import com.yonatankarp.petclinic.model.Pet;

public interface PetService {
    Pet findById(final Long id);

    Pet save(final Pet pet);

    Set<Pet> findAll();
}
