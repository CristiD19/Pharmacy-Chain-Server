package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import model.*;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartFrame;
//import org.jfree.chart.JFreeChart;
//import org.jfree.data.category.DefaultCategoryDataset;
//import org.jfree.data.general.DefaultPieDataset;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerController {

    UtilizatorPersistent utilizatorPersistent = new UtilizatorPersistent();
    FarmaciePersistent farmaciePersistent = new FarmaciePersistent();
    MedicamentPersistent medicamentPersistent = new MedicamentPersistent();
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public String cautareMedicamentManager(String data){

        String[] split = data.split(",");

        MedicamentInFarmacie medicamentInFarmacie = farmaciePersistent.cautareMedicamentDupaNume(split[0], Integer.parseInt(split[1]));

        try {
            return objectMapper.writeValueAsString(medicamentInFarmacie);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String filtrareMedicamenteManager(String data){

        List<MedicamentInFarmacie> medicamentInFarmacieList = farmaciePersistent.filtrareMedicamente(Integer.parseInt(data));
        try {
            return objectMapper.writeValueAsString(medicamentInFarmacieList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String listaMedicamenteManager(String data){
        List<MedicamentInFarmacie> medicamentInFarmacieList = farmaciePersistent.listaMedicamenteManager(Integer.parseInt(data));
        try {
            return objectMapper.writeValueAsString(medicamentInFarmacieList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String listaFarmacii(String data){

        List<Farmacie> listaFarmacii = farmaciePersistent.listaFarmacii();

        try {
            return objectMapper.writeValueAsString(listaFarmacii);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String listaMedicamenteManagerFisiere(String data){

        List<MedicamentInFarmacie> medicamentInFarmacieList = farmaciePersistent.listaMedicamente(Integer.parseInt(data));
        try {
            return objectMapper.writeValueAsString(medicamentInFarmacieList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }

}
