package com.yonatankarp.petclinic.services.jpa;

import java.util.HashSet;
import java.util.Set;
import com.yonatankarp.petclinic.model.Vet;
import com.yonatankarp.petclinic.repositories.VetRepository;
import com.yonatankarp.petclinic.services.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile("jpa")
public class VetJpaService implements VetService {

    private final VetRepository vetRepository;


    @Override
    public Set<Vet> findAll() {
        final Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(final Long id) {
        return vetRepository.findById(id).orElse(null);
    }

    @Override
    public Vet save(final Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public void delete(final Vet vet) {
        vetRepository.delete(vet);
    }

    @Override
    public void deleteById(final Long id) {
        vetRepository.deleteById(id);
    }
}
