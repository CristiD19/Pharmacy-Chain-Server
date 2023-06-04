package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ADMINISTRATOR")
public class Administrator extends Utilizator{

    public Administrator(){}

    public Administrator(String nume, String cont, String parola) {
        super(nume, cont, parola);
    }

}
