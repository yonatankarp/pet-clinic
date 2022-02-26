package com.yonatankarp.petclinic.services;

import java.util.Set;
import com.yonatankarp.petclinic.model.Owner;

public interface OwnerService {

    Owner findByLastName(final String lastName);

    Owner findById(final Long id);

    Owner save(final Owner owner);

    Set<Owner> findAll();
}
