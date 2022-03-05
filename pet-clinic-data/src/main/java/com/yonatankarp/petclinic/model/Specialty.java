package com.yonatankarp.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "specialties")
@NoArgsConstructor
@AllArgsConstructor
public class Specialty extends BaseEntity {
    @Column(name = "description")
    private String description;
}
