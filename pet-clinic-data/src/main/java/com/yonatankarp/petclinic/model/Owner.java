package com.yonatankarp.petclinic.model;

import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Owner extends Person {
    private Set<Pet> pets;
}
