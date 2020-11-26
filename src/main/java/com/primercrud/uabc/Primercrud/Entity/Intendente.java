package com.primercrud.uabc.Primercrud.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.util.AbstractMap;

@Entity
@Data
public class Intendente extends Persona {


    public Intendente() {
    }

    public Intendente(String fisrtName, String lastName, Date birthDate, Double salario) {
        super(fisrtName, lastName, birthDate);
        super.setSalario(salario);
        super.setType("Intendente");
    }
}
