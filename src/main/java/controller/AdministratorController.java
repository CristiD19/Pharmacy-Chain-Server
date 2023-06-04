package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class AdministratorController {
    UtilizatorPersistent utilizatorPersistent = new UtilizatorPersistent();
    FarmaciePersistent farmaciePersistent = new FarmaciePersistent();
    private static final ObjectMapper objectMapper = new ObjectMapper();




    public String getFarmacie(String data){
        int id = Integer.parseInt(data);

        System.out.println(id);
        Farmacie farmacie = farmaciePersistent.cautareFarmacieDupaId(id);
        System.out.println(farmacie);

        try {
            return objectMapper.writeValueAsString(farmacie);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String updateUtilizator(String data){
        String[] split = data.split(",");
        String numeUtilizator = split[0];
        String cont = split[1];
        String parola = split[2];
        String id = split[3];

        utilizatorPersistent.actualizareUtilizator(numeUtilizator, cont, parola, Integer.parseInt(id));

        EmailSender emailSender = new EmailSenderAdapter();
        try {
            emailSender.sendEmail(numeUtilizator, cont, parola, "cristideac1120@gmail.com");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "utilizator actualizat";

    }

    public String stergeUtilizator(String data){
        int id = Integer.parseInt(data);
        utilizatorPersistent.stergeUtilizator(id);

        return "utilizator sters";
    }


    public String filtrareUtilizatori(String data){


        System.out.println(data);
        List<Utilizator> listaUtilizatori = utilizatorPersistent.filtrareUtilizatorDupaRol(data);

        try {
            return objectMapper.writeValueAsString(listaUtilizatori);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String listaUtilizatori(String data){
        List<Utilizator> listaUtilizatori = utilizatorPersistent.listaUtilizatori();

        try {
            return objectMapper.writeValueAsString(listaUtilizatori);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String citireUtilizator(String data){
        int id = Integer.parseInt(data);
        Utilizator utilizator = utilizatorPersistent.cautareUtilizatorDupaId(id);

        try {
            return objectMapper.writeValueAsString(utilizator);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }


    public String creareUtilizator(String data) {

        Utilizator utilizator = null;
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(data);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        String type = rootNode.get("rol").asText();
        System.out.println(data);
        if ("ANGAJAT".equals(type)) {
            try {
                utilizator = objectMapper.reader().forType(Angajat.class).readValue(data);
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            }
        } else if ("MANAGER".equals(type)) {
            try {
                utilizator = objectMapper.reader().forType(Manager.class).readValue(data);
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            }
        } else if ("ADMINISTRATOR".equals(type)) {
            try {
                utilizator = objectMapper.reader().forType(Administrator.class).readValue(data);
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            }
        }

        utilizatorPersistent.creareUtilizator(utilizator);
        return "am adaugat utilziatorul";
    }


}





