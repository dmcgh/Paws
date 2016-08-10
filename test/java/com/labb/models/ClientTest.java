package com.labb.models;

import com.labb.util.Mysql;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by localadmin on 8/10/16.
 */
public class ClientTest {
    @Before
    public void setUp() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        session.createNativeQuery("truncate table clients").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateNewClientAndSave() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Client c = new Client("James");
        session.save(c);
        session.getTransaction().commit();
        session.close();
        assertEquals(1, c.getId());
    }
    @Test(expected = org.hibernate.exception.DataException.class)
    public void shouldNotCreateNewClientNameTooLong() throws Exception {
        Session session = Mysql.getSession();
        try {
            session.beginTransaction();
            Client c = new Client("Jamessssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
            session.save(c);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            session.close();
            throw e;

        }
    }

}