package model;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class FarmaciePersistent {



    public Farmacie cautareFarmacieDupaId(int id){
        Farmacie farmacie;
        try (Session sess = ConexiuneBazaDeDate.getEntityManager().openSession()) {
            farmacie = sess.get(Farmacie.class, id);
        }
        return farmacie;
    }

    public void salvareFarmacie(Farmacie farmacie){
        try (Session sess = ConexiuneBazaDeDate.getEntityManager().openSession()) {
            Transaction tx = sess.beginTransaction();
            sess.saveOrUpdate(farmacie);
            tx.commit();
        }
    }

    public void creareMedicamentInFarmacie(MedicamentInFarmacie medicamentInFarmacie){
        try (Session sess = ConexiuneBazaDeDate.getEntityManager().openSession()) {
            Transaction tx = sess.beginTransaction();
            sess.save(medicamentInFarmacie);
            tx.commit();
        }
    }

    public MedicamentInFarmacie cautareMedicamentInFarmacieDupaId(int id){
        MedicamentInFarmacie medicamentInFarmacie;
        try (Session sess = ConexiuneBazaDeDate.getEntityManager().openSession()) {
            medicamentInFarmacie = sess.get(MedicamentInFarmacie.class, id);
        }
        return medicamentInFarmacie;
    }


    public void stergeMedicamentInFarmacie(int id){
        MedicamentInFarmacie medicamentInFarmacie;
        try (Session sess = ConexiuneBazaDeDate.getEntityManager().openSession()) {
            //String hql = "DELETE FROM  Utilizator U WHERE U.id = :id";
            // Query query = sess.createQuery(hql);
            //query.setParameter("id", id);
            Transaction tx = sess.beginTransaction();
            medicamentInFarmacie = sess.get(MedicamentInFarmacie.class, id);
            sess.delete(medicamentInFarmacie);
            tx.commit();
        }
    }

    public MedicamentInFarmacie cautareMedicamentDupaNume(String nume, int idFarmacie){
        MedicamentInFarmacie medicamentInFarmacie;
        try (Session sess = ConexiuneBazaDeDate.getEntityManager().openSession()) {
            String hql = "FROM MedicamentInFarmacie M WHERE M.medicament.nume = :nume AND M.farmacie.id = :id";
            Query query = sess.createQuery(hql);
            query.setParameter("id", idFarmacie);
            query.setParameter("nume", nume);
            return (MedicamentInFarmacie) query.getResultList().get(0);
        }
    }

    public List<MedicamentInFarmacie> filtrareMedicamente(int id) {
        try (Session sess = ConexiuneBazaDeDate.getEntityManager().openSession()) {
            String hql = "FROM MedicamentInFarmacie M WHERE M.farmacie.id = :id ORDER BY M.medicament.disponibil DESC, M.medicament.valabil DESC , M.medicament.pret ASC, M.medicament.producator ASC ";
            Query query = sess.createQuery(hql);
            query.setParameter("id", id);
            return (List<MedicamentInFarmacie>) query.getResultList();
        }
    }


        public List<MedicamentInFarmacie> listaMedicamente(int id){
            try (Session sess = ConexiuneBazaDeDate.getEntityManager().openSession()) {
                String hql = "FROM MedicamentInFarmacie M WHERE M.farmacie.id = :id";
                Query query = sess.createQuery(hql);
                query.setParameter("id", id);
                return (List<MedicamentInFarmacie>) query.getResultList();
            }
    }

    public List<MedicamentInFarmacie> listaMedicamenteManager(int id) {
        try (Session sess = ConexiuneBazaDeDate.getEntityManager().openSession()) {
            String hql = "FROM MedicamentInFarmacie M WHERE M.farmacie.id = :id ORDER BY M.medicament.nume ASC, M.medicament.pret ASC , M.medicament.valabil DESC ";
            Query query = sess.createQuery(hql);
            query.setParameter("id", id);
            return (List<MedicamentInFarmacie>) query.getResultList();
        }
    }

    public List<Farmacie> listaFarmacii(){
        List<Farmacie> farmacieList;
        try (Session sess = ConexiuneBazaDeDate.getEntityManager().openSession()) {
            farmacieList = sess.createQuery("from Farmacie").list();
        }
        return farmacieList;
    }

    public void actualizareMedicamentInFarmacie(Boolean disponibil, String nume, int pret, String producator, Boolean valabil, int stoc, int id){
        try (Session sess = ConexiuneBazaDeDate.getEntityManager().openSession()) {
            Transaction tx = sess.beginTransaction();
            MedicamentInFarmacie medicamentInFarmacie = sess.get(MedicamentInFarmacie.class, id);
            medicamentInFarmacie.getMedicament().setDisponibil(disponibil);
            medicamentInFarmacie.getMedicament().setNume(nume);
            medicamentInFarmacie.getMedicament().setPret(pret);
            medicamentInFarmacie.getMedicament().setProducator(producator);
            medicamentInFarmacie.getMedicament().setValabil(valabil);
            medicamentInFarmacie.setStoc(stoc);
            sess.saveOrUpdate(medicamentInFarmacie);
            tx.commit();
        }
    }


}
