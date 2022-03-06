package com.yonatankarp.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
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
@Table(name = "visits")
@SuppressWarnings("JpaDataSourceORMInspection")
public class Visit extends BaseEntity {

    @Builder
    public Visit(final Long id,
                 final LocalDate date,
                 final String description,
                 final Pet pet) {
        super(id);
        this.date = date;
        this.description = description;
        this.pet = pet;
    }

    @Column(name = "visit_date")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
