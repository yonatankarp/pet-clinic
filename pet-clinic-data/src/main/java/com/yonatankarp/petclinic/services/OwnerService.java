package com.yonatankarp.petclinic.services;

import java.util.Set;
import com.yonatankarp.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(final String lastName);

    Set<Owner> findAllByLastNameLike(final String lastNameLike);
}
