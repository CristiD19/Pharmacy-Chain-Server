package server;

import controller.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Server instance;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    LoginController loginController = new LoginController();
    AdministratorController administratorController = new AdministratorController();
    AngajatController angajatController = new AngajatController();
    ManagerController managerController = new ManagerController();

    private Server() {
        // Private constructor to prevent instantiation
    }

    //SingleTone design pattern
    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        String inputLine = "";
        try {
            while ((inputLine = in.readLine()) != null) {
                String[] splitString = separateEndPoint(inputLine);
                String endpoint = splitString[0];
                String data = splitString[1];

                String send = handleEndpoint(endpoint, data);
                out.println(send);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public String handleEndpoint(String endpoint, String data) {
        if(endpoint.equals("login")) {
            // System.out.println("ffdsfdsfdsfdsfdsfds  datele aici:    " + data);
            return loginController.LoginCommand(data);
        }
        if(endpoint.equals("getFarmacie")){
            System.out.println("am ajuns");
            return administratorController.getFarmacie(data);
        }
        if(endpoint.equals("creareUtilizator")){
            return administratorController.creareUtilizator(data);
        }
        if(endpoint.equals("listaUtilizator")){
            return administratorController.listaUtilizatori(data);
        }
        if(endpoint.equals("citireUtilizator")){
            return administratorController.citireUtilizator(data);
        }
        if(endpoint.equals("updateUtilizator")){
            return administratorController.updateUtilizator(data);
        }
        if(endpoint.equals("stergeUtilizator")){
            return administratorController.stergeUtilizator(data);
        }
        if(endpoint.equals("filtrareUtilizator")){
            return administratorController.filtrareUtilizatori(data);
        }
        if(endpoint.equals("filtrareMedicament")){
            return angajatController.filtrareMedicament(data);
        }
        if(endpoint.equals("actualizareMedicament")){
            return angajatController.actualizareMedicament(data);
        }
        if(endpoint.equals("adaugaMedicament")){
            return angajatController.adaugaMedicament(data);
        }
        if(endpoint.equals("cautareMedicament")){
            return angajatController.cautareMedicament(data);
        }
        if(endpoint.equals("listaMedicament")){
            return angajatController.listaMedicament(data);
        }
        if(endpoint.equals("stergeMedicament")){
            return angajatController.stergeMedicament(data);
        }
        if(endpoint.equals("cautareDupaNumeMedicament")){
            return angajatController.cautareDupaNumeMedicament(data);
        }
        if(endpoint.equals("listaMedicamentGeneral")){
            return angajatController.listaMedicamentGeneral(data);
        }
        if(endpoint.equals("cautareMedicamentManager")){
            return managerController.cautareMedicamentManager(data);
        }
        if(endpoint.equals("filtrareMedicamenteManager")){
            return managerController.filtrareMedicamenteManager(data);
        }
        if(endpoint.equals("listaMedicamenteManager")){
            return managerController.listaMedicamenteManager(data);
        }
        if(endpoint.equals("listaFarmacii")){
            return managerController.listaFarmacii(data);
        }
        if(endpoint.equals("listaMedicamenteManagerFisiere")){
            return managerController.listaMedicamenteManagerFisiere(data);
        }
        return "";
    }

    public String[] separateEndPoint(String endpoint) {
        String[] splitString = endpoint.split("/");

        return splitString;
    }
}