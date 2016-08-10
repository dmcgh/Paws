package com.labb.models;

import com.labb.util.Mysql;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by localadmin on 8/10/16.
 */
public class ShelterTest {
    @Before
    public void setUp() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        session.createNativeQuery("truncate table shelters").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateNewShelterAndSave() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Shelter s = new Shelter("JamesTown", new Date());
        session.save(s);
        session.getTransaction().commit();
        session.close();
        assertEquals(1, s.getId());
        assertEquals("JamesTown", s.getName());
    }
    @Test(expected = org.hibernate.exception.DataException.class)
    public void shouldNotCreateNewShelterNameTooLong() throws Exception {
        Session session = Mysql.getSession();
        try {
            session.beginTransaction();
            Shelter s = new Shelter("JamesTownjhjhjhjhjjhjhjhjjhjhjhjhjhjhjhjhhjhjhjhhjhjhjhjhjhjhjhjhjhj", new Date());
            session.save(s);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            session.close();
            throw e;

        }
    }

    @Test(expected = org.hibernate.exception.ConstraintViolationException.class)
    public void shouldNotCreateNewShelterDuplicateName() throws Exception {
        Session session = Mysql.getSession();
        try {
            session.beginTransaction();
            Shelter s = new Shelter("JamesTown", new Date());
            session.save(s);
            s = new Shelter("JamesTown", new Date());
            session.save(s);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            session.close();
            throw e;

        }
    }

}