package com.yonatankarp.petclinic.repositories;

import com.yonatankarp.petclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
