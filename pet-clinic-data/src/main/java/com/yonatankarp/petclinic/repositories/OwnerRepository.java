package com.yonatankarp.petclinic.repositories;

import com.yonatankarp.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(final String lastName);
}
