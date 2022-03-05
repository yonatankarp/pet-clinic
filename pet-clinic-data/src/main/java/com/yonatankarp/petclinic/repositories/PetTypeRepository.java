package com.yonatankarp.petclinic.repositories;

import com.yonatankarp.petclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
