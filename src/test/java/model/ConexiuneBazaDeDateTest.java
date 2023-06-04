package model;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConexiuneBazaDeDateTest {


    private ConexiuneBazaDeDate conexiuneBazaDeDate;
    private Session session;

    @BeforeEach
    void setUp(){
        session = ConexiuneBazaDeDate.getEntityManager().openSession();
    }

    @AfterEach()
    void tearDown(){
        session.close();
    }

    @Test
    void testCreateConnection(){
        Session sess = ConexiuneBazaDeDate.getEntityManager().openSession();
        assertNotNull(sess);
    }

    @Test
    void testCloseConnection(){
        Session sess = ConexiuneBazaDeDate.getEntityManager().openSession();
        sess.close();
        assertFalse(sess.isConnected());
    }


}