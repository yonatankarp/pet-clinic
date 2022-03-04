package com.yonatankarp.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
@Entity
public class Owner extends Person {
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
