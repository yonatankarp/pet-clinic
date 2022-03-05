package com.yonatankarp.petclinic.services.jpa;

import java.util.HashSet;
import java.util.Set;
import com.yonatankarp.petclinic.model.Owner;
import com.yonatankarp.petclinic.repositories.OwnerRepository;
import com.yonatankarp.petclinic.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile("jpa")
public class OwnerJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Override
    public Set<Owner> findAll() {
        final Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(final Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner save(final Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void delete(final Owner owner) {
        ownerRepository.delete(owner);
    }

    @Override
    public void deleteById(final Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public Owner findByLastName(final String lastName) {
        return ownerRepository.findByLastName(lastName);
    }
}
