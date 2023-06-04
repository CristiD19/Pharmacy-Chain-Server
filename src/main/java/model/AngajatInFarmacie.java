package model;

import java.util.List;

//Proxy
public class AngajatInFarmacie {

    private Angajat angajat = new Angajat();
    private Farmacie farmacie = new Farmacie();


    private void  adaugareAngajatInFarmacie(){
        List<Angajat> angajati = farmacie.getAngajati();
        farmacie.setAngajati(angajati);
    }


}
