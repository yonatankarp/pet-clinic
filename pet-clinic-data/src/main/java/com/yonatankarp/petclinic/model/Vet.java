package com.yonatankarp.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "vets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Vet extends Person {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialties",
            joinColumns = @JoinColumn(name = "vat_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties = new HashSet<>();
}
