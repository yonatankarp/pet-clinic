package com.yonatankarp.petclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Person extends BaseEntity {
    private String firstName;
    private String lastName;
}
