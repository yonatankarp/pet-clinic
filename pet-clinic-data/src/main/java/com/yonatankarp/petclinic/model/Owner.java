package com.yonatankarp.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "owners")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Owner extends Person {

    @Builder
    public Owner(final String firstName,
                 final String lastName,
                 final String address,
                 final String city,
                 final String phone,
                 final Set<Pet> pets) {
        super(firstName, lastName);
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.pets = pets;
    }

    @Column(name = "address")
    private String address;

    @Column(name = "ciry")
    private String city;

    @Column(name = "phone")
    private String phone;

    @Column
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();
}
