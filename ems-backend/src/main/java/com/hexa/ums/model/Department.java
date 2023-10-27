package com.hexa.ums.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Department {
    @Id
    @GeneratedValue(generator = "sequenceDepartmentIdGenerator")
    @GenericGenerator(name = "sequenceDepartmentIdGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@org.hibernate.annotations.Parameter(name = "sequence_name", value = "sequence_department")})
    private Integer departmentId;
    private String name;
}
