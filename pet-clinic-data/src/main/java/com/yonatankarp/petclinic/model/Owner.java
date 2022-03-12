package com.yonatankarp.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "owners")
@SuppressWarnings("JpaDataSourceORMInspection")
public class Owner extends Person {

    @Builder
    public Owner(final Long id,
                 final String firstName,
                 final String lastName,
                 final String address,
                 final String city,
                 final String phone,
                 final Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.phone = phone;

        if (pets != null) {
            this.pets = pets;
        }
    }

    @Column(name = "address")
    private String address;

    @Column(name = "ciry")
    private String city;

    @Column(name = "phone")
    private String phone;

    @Column
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @ToString.Exclude
    private Set<Pet> pets = new HashSet<>();

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    public Pet getPet(final String name) {
        return getPet(name, false);
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    public Pet getPet(final String name, final boolean ignoreNew) {
        return pets.stream()
                .filter(pet -> (!ignoreNew || !pet.isNew()) && pet.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
