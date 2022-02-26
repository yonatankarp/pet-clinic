package com.yonatankarp.petclinic.services;

import java.util.Set;
import com.yonatankarp.petclinic.model.Vet;

public interface VetService {
    Vet findById(final Long id);

    Vet save(final Vet vet);

    Set<Vet> findAll();
}
