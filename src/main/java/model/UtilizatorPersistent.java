package model;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilizatorPersistent {

    public void creareUtilizator(Utilizator utilizator){
        try (Session sess = ConexiuneBazaDeDate.getEntityManager().openSession()) {
            sess.save(utilizator);
        }
    }

    public void actualizareUtilizator(String nume, String cont, String parola, int id){
        try (Session sess = ConexiuneBazaDeDate.getEntityManager().openSession()) {
            Transaction tx = sess.beginTransaction();
            Utilizator utilizator = sess.get(Utilizator.class, id);
            utilizator.setNume(nume);
            utilizator.setCont(cont);
            utilizator.setParola(parola);
            sess.saveOrUpdate(utilizator);
            tx.commit();
        }
    }

    public Utilizator cautareUtilizatorDupaId(int id){
        Utilizator utilizator;
        try (Session sess = ConexiuneBazaDeDate.getEntityManager().openSession()) {
            utilizator = sess.get(Utilizator.class, id);
        }
        return utilizator;
    }

    public Utilizator cautareUtilizatorDupaCont(String nume, String parola){
        try (Session sess = ConexiuneBazaDeDate.getEntityManager().openSession()) {
            String hql = "FROM Utilizator U WHERE U.cont = :cont AND U.parola = :parola";
            Query query = sess.createQuery(hql);
            query.setParameter("cont", nume);
            query.setParameter("parola", parola);
            //System.out.println("numele este:" + nume);
          //  Hibernate.initialize(query.getResultList().get(0));
            return (Utilizator) query.getResultList().get(0);
        }
    }

    public List<Utilizator> filtrareUtilizatorDupaRol(String rol){
        List<Utilizator> utilizatorList;
        try (Session sess = ConexiuneBazaDeDate.getEntityManager().openSession()) {
            String hql = "FROM Utilizator U WHERE U.rol = :rol";
            Query query = sess.createQuery(hql);
            query.setParameter("rol", rol);

            utilizatorList = query.getResultList();
        }
        return utilizatorList;
    }

    public List<Utilizator> listaUtilizatori(){
        List<Utilizator> utilizatorList;
        try (Session sess = ConexiuneBazaDeDate.getEntityManager().openSession()) {
            utilizatorList = sess.createQuery("from Utilizator ").list();
        }
        return utilizatorList;
    }

    public void stergeUtilizator(int id){
        Utilizator utilizator;
        try (Session sess = ConexiuneBazaDeDate.getEntityManager().openSession()) {
            //String hql = "DELETE FROM  Utilizator U WHERE U.id = :id";
           // Query query = sess.createQuery(hql);
            //query.setParameter("id", id);
            Transaction tx = sess.beginTransaction();
            utilizator = sess.get(Utilizator.class, id);
            sess.delete(utilizator);
            tx.commit();
        }
    }






}
