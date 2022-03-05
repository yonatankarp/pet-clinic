package com.yonatankarp.petclinic.repositories;

import com.yonatankarp.petclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
