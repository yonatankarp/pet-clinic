package com.yonatankarp.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "specialties")
@SuppressWarnings("JpaDataSourceORMInspection")
public class Speciality extends BaseEntity {

    @Builder
    public Speciality(final Long id, final String description) {
        super(id);
        this.description = description;
    }

    @Column(name = "description")
    private String description;
}
