package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilizatorPersistentTest {

    @Test
    void creareUtilizator() {

        Utilizator utilizator = new Administrator( "andrei", "andrei9", "parola");

        UtilizatorPersistent utilizatorPersistent = new UtilizatorPersistent();
        utilizatorPersistent.creareUtilizator(utilizator);
        Utilizator utilizator1 = utilizatorPersistent.cautareUtilizatorDupaId(utilizator.getId());

        Assertions.assertEquals(utilizator, utilizator1);
    }

    @Test
    void actualizareUtilizator() {
        Utilizator utilizator = new Administrator("andrei", "andrei9", "parola");

        UtilizatorPersistent utilizatorPersistent = new UtilizatorPersistent();
        utilizatorPersistent.creareUtilizator(utilizator);

        utilizatorPersistent.actualizareUtilizator( "alex", "al", "parola", utilizator.getId());

        Utilizator utilizator1 = new Administrator("alex", "al", "parola");
        utilizator1.setId(utilizator.getId());

        Assertions.assertEquals(utilizator1, utilizatorPersistent.cautareUtilizatorDupaId(utilizator.getId()));

        utilizatorPersistent.stergeUtilizator(utilizator.getId());
    }

    @Test
    void cautareUtilizatorDupaId() {

        Utilizator utilizator = new Administrator( "alex", "alex19", "parola");

        UtilizatorPersistent utilizatorPersistent = new UtilizatorPersistent();
        utilizatorPersistent.creareUtilizator(utilizator);

        Utilizator utilizator1 = utilizatorPersistent.cautareUtilizatorDupaId(utilizator.getId());

        Assertions.assertEquals(utilizator, utilizator1);

        utilizatorPersistent.stergeUtilizator(utilizator.getId());
    }


    @Test
    void stergeUtilizator() {

        Farmacie farmacie = new Farmacie();
        Utilizator utilizator = new Angajat("cristi", "cd","cd", farmacie);

        UtilizatorPersistent utilizatorPersistent = new UtilizatorPersistent();
        utilizatorPersistent.creareUtilizator(utilizator);
        utilizatorPersistent.stergeUtilizator(utilizator.getId());

        Utilizator utilizator1 = utilizatorPersistent.cautareUtilizatorDupaId(utilizator.getId());

        Assertions.assertNull(utilizator1);
    }
}