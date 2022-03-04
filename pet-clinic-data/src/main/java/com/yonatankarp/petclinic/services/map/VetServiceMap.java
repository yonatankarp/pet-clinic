package com.yonatankarp.petclinic.services.map;

import java.util.Set;
import com.yonatankarp.petclinic.model.Specialty;
import com.yonatankarp.petclinic.model.Vet;
import com.yonatankarp.petclinic.services.SpecialtyService;
import com.yonatankarp.petclinic.services.VetService;
import org.springframework.stereotype.Service;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Vet findById(final Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(final Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(final Vet vet) {
        super.delete(vet);
    }

    @Override
    public Vet save(final Vet vet) {
        if (vet != null) {
            if (vet.getSpecialties().size() > 0) {
                vet.getSpecialties().forEach(specialty -> {
                    if (specialty.getId() == null) {
                        final Specialty savedSpeciality = specialtyService.save(specialty);
                        specialty.setId(savedSpeciality.getId());
                    }
                });
            }

            return super.save(vet);
        }

        return null;
    }
}
