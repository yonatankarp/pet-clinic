package com.yonatankarp.petclinic.repositories;

import com.yonatankarp.petclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Speciality, Long> {
}
