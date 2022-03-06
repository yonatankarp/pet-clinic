package com.yonatankarp.petclinic.services.jpa;

import java.util.HashSet;
import java.util.Set;
import com.yonatankarp.petclinic.model.Speciality;
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
    public Set<Speciality> findAll() {
        final Set<Speciality> specialties = new HashSet<>();
        specialtyRepository.findAll().forEach(specialties::add);
        return specialties;
    }

    @Override
    public Speciality findById(final Long id) {
        return specialtyRepository.findById(id).orElse(null);
    }

    @Override
    public Speciality save(final Speciality speciality) {
        return specialtyRepository.save(speciality);
    }

    @Override
    public void delete(final Speciality speciality) {
        specialtyRepository.delete(speciality);
    }

    @Override
    public void deleteById(final Long id) {
        specialtyRepository.deleteById(id);
    }
}
