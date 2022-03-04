package com.yonatankarp.petclinic.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Vet extends Person {
    private Set<Specialty> specialties = new HashSet<>();
}
