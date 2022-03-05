package com.yonatankarp.petclinic.services.jpa;

import java.util.HashSet;
import java.util.Set;
import com.yonatankarp.petclinic.model.PetType;
import com.yonatankarp.petclinic.repositories.PetTypeRepository;
import com.yonatankarp.petclinic.services.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile("jpa")
public class PetTypeJpaService implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    @Override
    public Set<PetType> findAll() {
        final Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType findById(final Long id) {
        return petTypeRepository.findById(id).orElse(null);
    }

    @Override
    public PetType save(final PetType petType) {
        return petTypeRepository.save(petType);
    }

    @Override
    public void delete(final PetType petType) {
        petTypeRepository.delete(petType);
    }

    @Override
    public void deleteById(final Long id) {
        petTypeRepository.deleteById(id);
    }
}
