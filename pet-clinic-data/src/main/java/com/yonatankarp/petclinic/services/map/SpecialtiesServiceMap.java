package com.yonatankarp.petclinic.services.map;

import java.util.Set;
import com.yonatankarp.petclinic.model.Specialty;
import com.yonatankarp.petclinic.services.SpecialtyService;
import org.springframework.stereotype.Service;

@Service
public class SpecialtiesServiceMap extends AbstractMapService<Specialty, Long> implements SpecialtyService {
    @Override
    public Set<Specialty> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(final Long id) {
        super.deleteById(id);
    }

    @Override
    public Specialty save(final Specialty specialty) {
        return super.save(specialty);
    }

    @Override
    public void delete(final Specialty specialty) {
        super.delete(specialty);
    }

    @Override
    public Specialty findById(final Long id) {
        return super.findById(id);
    }
}
