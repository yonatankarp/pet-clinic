package com.yonatankarp.petclinic.repositories;

import com.yonatankarp.petclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
