package com.primercrud.uabc.Primercrud.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@Data
public class Alumno extends Persona {


    public Alumno() {
    }

    public Alumno(String fisrtName, String lastName, Date birthDate, String course) {
        super(fisrtName, lastName, birthDate);
        super.setType("Alumno");
        super.setCourse(course);
        super.setSalario(null);
    }
}
