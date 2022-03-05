package com.yonatankarp.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "specialties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Specialty extends BaseEntity {
    @Column(name = "description")
    private String description;
}
