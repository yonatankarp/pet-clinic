package com.yonatankarp.petclinic.services.jpa;

import java.util.HashSet;
import java.util.Set;
import com.yonatankarp.petclinic.model.Visit;
import com.yonatankarp.petclinic.repositories.VisitRepository;
import com.yonatankarp.petclinic.services.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile("jpa")
public class VisitJpaService implements VisitService {

    private final VisitRepository visitRepository;

    @Override
    public Set<Visit> findAll() {
        final Set<Visit> visits = new HashSet<>();
        visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Visit findById(final Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public Visit save(final Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public void delete(final Visit visit) {
        visitRepository.delete(visit);
    }

    @Override
    public void deleteById(final Long id) {
        visitRepository.deleteById(id);
    }
}
