package com.yonatankarp.petclinic.model;

import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Visit extends BaseEntity {
    private LocalDate date;
    private String description;
    private Pet pet;
}
