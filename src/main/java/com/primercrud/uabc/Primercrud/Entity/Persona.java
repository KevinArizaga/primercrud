package com.primercrud.uabc.Primercrud.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Entity
public abstract class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer matricula;
    private String fisrtName, lastName, type, course;
    private Date birthDate;
    private Double salario;

    public Persona() {
    }

    public Persona(String fisrtName, String lastName, Date birthDate) {
        this.fisrtName = fisrtName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

}
