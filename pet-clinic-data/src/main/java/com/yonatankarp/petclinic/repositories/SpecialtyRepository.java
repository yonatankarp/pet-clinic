package com.yonatankarp.petclinic.repositories;

import com.yonatankarp.petclinic.model.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
