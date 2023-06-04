package model;

//Factory design pattern
public class UtilizatorFactory {

    public Utilizator getShape(String shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("ANGAJAT")){
            return new Angajat();

        } else if(shapeType.equalsIgnoreCase("MANAGER")){
            return new Manager();

        } else if(shapeType.equalsIgnoreCase("ADMINISTRATOR")){
            return new Administrator();
        }

        return null;
    }

}
