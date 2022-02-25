package com.yonatankarp.petclinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
}
