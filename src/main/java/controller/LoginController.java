package controller;

import model.*;
import org.hibernate.Hibernate;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginController {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    UtilizatorPersistent utilizatorPersistent = new UtilizatorPersistent();


    public String LoginCommand(String data) {
        String[] split = data.split(",");
        String numeUtilizator = split[0];
        String parola = split[1];

        Utilizator utilizator = utilizatorPersistent.cautareUtilizatorDupaCont(numeUtilizator, parola);



        try {
            return objectMapper.writeValueAsString(utilizator);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }

}



