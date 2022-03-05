package com.yonatankarp.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "owners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Owner extends Person {
    @Column(name = "address")
    private String address;

    @Column(name = "ciry")
    private String city;

    @Column(name = "phone")
    private String phone;

    @Column
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();
}
