package com.yonatankarp.petclinic.services.jpa;

import java.util.HashSet;
import java.util.Set;
import com.yonatankarp.petclinic.model.Specialty;
import com.yonatankarp.petclinic.repositories.SpecialtyRepository;
import com.yonatankarp.petclinic.services.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile("jpa")
public class SpecialityJpaService implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    @Override
    public Set<Specialty> findAll() {
        final Set<Specialty> specialties = new HashSet<>();
        specialtyRepository.findAll().forEach(specialties::add);
        return specialties;
    }

    @Override
    public Specialty findById(final Long id) {
        return specialtyRepository.findById(id).orElse(null);
    }

    @Override
    public Specialty save(final Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public void delete(final Specialty specialty) {
        specialtyRepository.delete(specialty);
    }

    @Override
    public void deleteById(final Long id) {
        specialtyRepository.deleteById(id);
    }
}
