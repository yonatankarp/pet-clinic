package com.yonatankarp.petclinic.services;

import java.util.Set;

/**
 * Hibernate-like interface for a repository
 */
public interface CrudService<T, ID> {
    Set<T> findAll();

    T findById(final ID id);

    T save(final T object);

    void delete(final T object);

    void deleteById(final ID id);
}
