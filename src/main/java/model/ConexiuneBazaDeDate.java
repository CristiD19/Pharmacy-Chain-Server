package model;

import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;


public class ConexiuneBazaDeDate {

        private static ConexiuneBazaDeDate singleton = null;

        private SessionFactory sessionFactory;

        private ConexiuneBazaDeDate() {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            try {
                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        public static SessionFactory getEntityManager() {
            if (singleton == null)
                singleton = new ConexiuneBazaDeDate();
            return singleton.sessionFactory;
        }


}