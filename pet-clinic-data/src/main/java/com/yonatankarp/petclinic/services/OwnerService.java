package com.yonatankarp.petclinic.services;

import com.yonatankarp.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(final String lastName);

}
