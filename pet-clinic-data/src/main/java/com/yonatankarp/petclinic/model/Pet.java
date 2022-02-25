package com.yonatankarp.petclinic.model;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pet {
    private PetType petType;
    private Owner owner;
    private LocalDate birthDate;
}
