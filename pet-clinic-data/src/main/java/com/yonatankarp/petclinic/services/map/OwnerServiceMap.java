package com.yonatankarp.petclinic.services.map;

import java.util.Set;
import com.yonatankarp.petclinic.model.Owner;
import com.yonatankarp.petclinic.services.CrudService;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements CrudService<Owner, Long> {

    @Override
    public Owner findById(final Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner save(final Owner owner) {
        return super.save(owner.getId(), owner);
    }

    @Override
    public void delete(final Owner owner) {
        super.delete(owner);
    }

    @Override
    public void deleteById(final Long id) {
        super.deleteById(id);
    }
}
