package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class AngajatController {
    UtilizatorPersistent utilizatorPersistent = new UtilizatorPersistent();
    FarmaciePersistent farmaciePersistent = new FarmaciePersistent();
    MedicamentPersistent medicamentPersistent = new MedicamentPersistent();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public String actualizareMedicament(String data){

        String[] split = data.split(",");

        Boolean disponibil = Boolean.valueOf(split[0]);
        String nume = split[0];
        int pret = Integer.parseInt(split[1]);
        String producator = split[2];
        Boolean valabil = Boolean.valueOf(split[3]);
        int id = Integer.parseInt(split[4]);
        int stoc = Integer.parseInt(split[5]);
        farmaciePersistent.actualizareMedicamentInFarmacie(disponibil, nume, pret, producator, valabil, stoc, id);


        return "medicament actualizat";
    }

    public String adaugaMedicament(String data){

        String[] split = data.split(",");
        Farmacie farmacie = null;
        try {
            farmacie = objectMapper.reader().forType(Angajat.class).readValue(split[0]);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        Medicament medicament = medicamentPersistent.cautareMedicamentDupaId(Integer.parseInt(split[1]));
        MedicamentInFarmacie medicamentInFarmacie = farmacie.adaugareMedicament(medicament, Integer.parseInt(split[2]));
        farmaciePersistent.creareMedicamentInFarmacie(medicamentInFarmacie);

        return "am adaugat medicamentul";
    }

    public String cautareMedicament(String data){

        String[] split = data.split(",");

        MedicamentInFarmacie medicamentInFarmacie = farmaciePersistent.cautareMedicamentDupaNume(split[0], Integer.parseInt(split[1]));

        try {
            return objectMapper.writeValueAsString(medicamentInFarmacie);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String listaMedicament(String data){

        System.out.println(data);
        List<MedicamentInFarmacie> medicamentInFarmacieList = farmaciePersistent.listaMedicamente(Integer.parseInt(data));

        try {
            return objectMapper.writeValueAsString(medicamentInFarmacieList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String filtrareMedicament(String data){

        List<MedicamentInFarmacie> medicamentInFarmacieList = farmaciePersistent.filtrareMedicamente(Integer.parseInt(data));

        try {
            return objectMapper.writeValueAsString(medicamentInFarmacieList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String stergeMedicament(String data){

        String[] split = data.split(",");

        Farmacie farmacie = null;
        try {
            farmacie = objectMapper.reader().forType(Farmacie.class).readValue(split[1]);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        int idFromMedicamentInFarmacie = Integer.parseInt(split[0]);
        farmacie.stergeMedicament(farmaciePersistent.cautareMedicamentInFarmacieDupaId(idFromMedicamentInFarmacie));
        farmaciePersistent.stergeMedicamentInFarmacie(idFromMedicamentInFarmacie);


        return "medicament sters";
    }

    public String cautareDupaNumeMedicament(String data){

        String[] split = data.split(",");

        MedicamentInFarmacie medicamentInFarmacie = farmaciePersistent.cautareMedicamentDupaNume(split[0], Integer.parseInt(split[1]));

        try {
            return objectMapper.writeValueAsString(medicamentInFarmacie);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String listaMedicamentGeneral(String data){

        List<Medicament> medicamentList = medicamentPersistent.listaMedicament();
        try {
            return objectMapper.writeValueAsString(medicamentList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }

}
