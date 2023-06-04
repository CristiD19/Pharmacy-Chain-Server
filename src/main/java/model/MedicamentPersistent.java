package model;

import org.hibernate.Session;

import java.util.List;

public class MedicamentPersistent {
    public Medicament cautareMedicamentDupaId(int id){
        Medicament medicament;
        try (Session sess = ConexiuneBazaDeDate.getEntityManager().openSession()) {
            medicament= sess.get(Medicament.class, id);
        }
        return medicament;
    }

    public List<Medicament> listaMedicament(){
        List<Medicament> medicamentList;
        try (Session sess = ConexiuneBazaDeDate.getEntityManager().openSession()) {
            medicamentList = sess.createQuery("from Medicament ").list();
        }
        return medicamentList;
  }
}
