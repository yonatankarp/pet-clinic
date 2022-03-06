package com.yonatankarp.petclinic.services.map;

import java.util.Set;
import com.yonatankarp.petclinic.model.Speciality;
import com.yonatankarp.petclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class SpecialityMapService extends AbstractMapService<Speciality, Long> implements SpecialtyService {
    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(final Long id) {
        super.deleteById(id);
    }

    @Override
    public Speciality save(final Speciality speciality) {
        return super.save(speciality);
    }

    @Override
    public void delete(final Speciality speciality) {
        super.delete(speciality);
    }

    @Override
    public Speciality findById(final Long id) {
        return super.findById(id);
    }
}
