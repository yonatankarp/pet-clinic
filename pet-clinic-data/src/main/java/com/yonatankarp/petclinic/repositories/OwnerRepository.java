package com.yonatankarp.petclinic.repositories;

import java.util.Set;
import com.yonatankarp.petclinic.model.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(final String lastName);

    @Query("FROM Owner WHERE LOWER(lastName) LIKE LOWER(:lastNameLike)")
    Set<Owner> findAllByLastName(final String lastNameLike);
}
