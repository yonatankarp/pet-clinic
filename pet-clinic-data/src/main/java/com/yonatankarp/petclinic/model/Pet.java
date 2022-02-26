package com.yonatankarp.petclinic.model;

import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Pet extends BaseEntity {
    private PetType petType;
    private Owner owner;
    private LocalDate birthDate;
}
