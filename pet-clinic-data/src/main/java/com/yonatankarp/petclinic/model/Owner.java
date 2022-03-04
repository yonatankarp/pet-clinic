package com.yonatankarp.petclinic.model;

import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Owner extends Person {
    private String address;
    private String city;
    private String phone;
    private Set<Pet> pets;
}
