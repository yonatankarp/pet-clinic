package com.yonatankarp.petclinic.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class BaseEntity implements Serializable {
    private Long id;
}
