package com.yonatankarp.petclinic.repositories;

import java.util.Optional;
import com.yonatankarp.petclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
    Optional<PetType> findByName(final String name);
}
