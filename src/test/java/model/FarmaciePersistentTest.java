package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FarmaciePersistentTest {


    @Test
    void creareMedicamentInFarmacie() {

        FarmaciePersistent farmaciePersistent = new FarmaciePersistent();
        MedicamentPersistent medicamentPersistent = new MedicamentPersistent();

        Farmacie farmacie = farmaciePersistent.cautareFarmacieDupaId(1);
        Medicament medicament = medicamentPersistent.cautareMedicamentDupaId(1);
        MedicamentInFarmacie medicamentInFarmacie = new MedicamentInFarmacie(farmacie,medicament,15);



        farmaciePersistent.creareMedicamentInFarmacie(medicamentInFarmacie);
        MedicamentInFarmacie medicamentInFarmacie1 = farmaciePersistent.cautareMedicamentInFarmacieDupaId(medicamentInFarmacie.getId());

        Assertions.assertEquals(medicamentInFarmacie, medicamentInFarmacie);

        farmaciePersistent.stergeMedicamentInFarmacie(medicamentInFarmacie.getId());
    }

    @Test
    void cautareMedicamentInFarmacieDupaId() {

        FarmaciePersistent farmaciePersistent = new FarmaciePersistent();
        MedicamentPersistent medicamentPersistent = new MedicamentPersistent();

        Farmacie farmacie = farmaciePersistent.cautareFarmacieDupaId(1);
        Medicament medicament = medicamentPersistent.cautareMedicamentDupaId(6);
        MedicamentInFarmacie medicamentInFarmacie = new MedicamentInFarmacie(farmacie,medicament,20);


        MedicamentInFarmacie medicamentInFarmacie1 = farmaciePersistent.cautareMedicamentInFarmacieDupaId(24);
        Assertions.assertEquals(medicamentInFarmacie1, medicamentInFarmacie1);


    }

    @Test
    void stergeMedicamentInFarmacie() {

        FarmaciePersistent farmaciePersistent = new FarmaciePersistent();
        MedicamentPersistent medicamentPersistent = new MedicamentPersistent();

        Farmacie farmacie = farmaciePersistent.cautareFarmacieDupaId(1);
        Medicament medicament = medicamentPersistent.cautareMedicamentDupaId(1);
        MedicamentInFarmacie medicamentInFarmacie = new MedicamentInFarmacie(farmacie,medicament,15);

        farmaciePersistent.creareMedicamentInFarmacie(medicamentInFarmacie);

        farmaciePersistent.stergeMedicamentInFarmacie(medicamentInFarmacie.getId());

        MedicamentInFarmacie medicamentInFarmacie1 = farmaciePersistent.cautareMedicamentInFarmacieDupaId(medicamentInFarmacie.getId());

        Assertions.assertNull(medicamentInFarmacie1);
    }


    @Test
    void actualizareMedicamentInFarmacie() {
        FarmaciePersistent farmaciePersistent = new FarmaciePersistent();
        MedicamentPersistent medicamentPersistent = new MedicamentPersistent();

        Farmacie farmacie = farmaciePersistent.cautareFarmacieDupaId(1);
        Medicament medicament = medicamentPersistent.cautareMedicamentDupaId(1);
        MedicamentInFarmacie medicamentInFarmacie = new MedicamentInFarmacie(farmacie,medicament,15);

        farmaciePersistent.creareMedicamentInFarmacie(medicamentInFarmacie);



        farmaciePersistent.stergeMedicamentInFarmacie(medicamentInFarmacie.getId());

        //farmaciePersistent.actualizareMedicamentInFarmacie();

        MedicamentInFarmacie medicamentInFarmacie1 = farmaciePersistent.cautareMedicamentInFarmacieDupaId(medicamentInFarmacie.getId());

        Assertions.assertNull(medicamentInFarmacie1);
    }
}