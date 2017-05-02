/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opdrachten;

import bank.domain.Account;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.DatabaseCleaner;

/**
 *
 * @author yanni
 */
public class OpdrachtEen {
    
    EntityManagerFactory emf;
    EntityManager em;
    DatabaseCleaner clean;
    
    public OpdrachtEen() {
    }

    @BeforeClass
    public static void setUpClass() {
        
    }

    @AfterClass
    public static void tearDownClass() {
        
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("bankPU");
        em = emf.createEntityManager();
        clean = new DatabaseCleaner(em);
        
    }

    @After
    public void tearDown(){
        
        try
        {
            clean.clean();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.toString());
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
    
    @Test
    public void opdrachtEen() {
        Account account = new Account(111L);
        em.getTransaction().begin();
        em.persist(account);
        //TODO: verklaar en pas eventueel aan (was al goed)
        assertNull(account.getId());
        em.getTransaction().commit();
        System.out.println("AccountId: " + account.getId());
        //TODO: verklaar en pas eventueel aan (was al goed)
        assertTrue(account.getId() > 0L);
    }
    
    @Test
    public void opdrachtTwee()
    {
        
    }
}
