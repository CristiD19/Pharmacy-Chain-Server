package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "MANAGER")
public class Manager extends Utilizator{

    public Manager() {}

    public Manager(String nume, String cont, String parola) {
        super(nume, cont, parola);
    }


}
