/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opdrachten;

import bank.dao.AccountDAOJPAImpl;
import bank.domain.Account;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
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
    AccountDAOJPAImpl DAO;

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
        DAO = new AccountDAOJPAImpl(em);
    }

    @After
    public void tearDown() {

        try {
            clean.clean();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void vraagEen() {
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
    public void vraagTwee() {
        Account account = new Account(111L);
        em.getTransaction().begin();
        em.persist(account);
        assertNull(account.getId());
        em.getTransaction().rollback();
        // TODO code om te testen dat table account geen records bevat. Hint: bestudeer/gebruik AccountDAOJPAImpl
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Account.class));
        List<Account> emptyList = em.createQuery(cq).getResultList();
        assertEquals(emptyList, DAO.findAll());
    }

    @Test
    public void vraagDrie() {
        Long expected = -100L;
        Account account = new Account(111L);
        account.setId(expected);
        em.getTransaction().begin();
        em.persist(account);
        //TODO: verklaar en pas eventueel aan IS ASSERTEQUALS EN GEEN NOTEQUALS
        assertEquals(expected, account.getId());
        em.flush();
        //TODO: verklaar en pas eventueel aan
        assertEquals(expected, account.getId());
        em.getTransaction().commit();
        //TODO: verklaar en pas eventueel aan

    }

    @Test
    public void vraagVier() {
        Long expectedBalance = 400L;
        Account account = new Account(114L);
        em.getTransaction().begin();
        em.persist(account);
        account.setBalance(expectedBalance);
        em.getTransaction().commit();
        assertEquals(expectedBalance, account.getBalance());
        //TODO: verklaar de waarde van account.getBalance
        Long cid = account.getId();
        account = null;
        EntityManager em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        Account found = em2.find(Account.class, cid);
        //TODO: verklaar de waarde van found.getBalance
        assertEquals(expectedBalance, found.getBalance());

    }

    @Test
    public void vraagVijf() {
        
    }
}
