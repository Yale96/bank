/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opdrachten;

import bank.dao.AccountDAOJPAImpl;
import bank.domain.Account;
import java.sql.SQLException;
import java.time.Clock;
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
//    @Test
//    public void vraagEen() {
//        Account account = new Account(111L);
//        em.getTransaction().begin();
//        em.persist(account);
//        //TODO: verklaar en pas eventueel aan (was al goed)
//        assertNull(account.getId());
//        em.getTransaction().commit();
//        System.out.println("AccountId: " + account.getId());
//        //TODO: verklaar en pas eventueel aan (was al goed)
//        assertTrue(account.getId() > 0L);
//    }
//
//    @Test
//    public void vraagTwee() {
//        Account account = new Account(111L);
//        em.getTransaction().begin();
//        em.persist(account);
//        assertNull(account.getId());
//        em.getTransaction().rollback();
//        // TODO code om te testen dat table account geen records bevat. Hint: bestudeer/gebruik AccountDAOJPAImpl
//        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//        cq.select(cq.from(Account.class));
//        List<Account> emptyList = em.createQuery(cq).getResultList();
//        assertEquals(emptyList, DAO.findAll());
//    }
//    @Test
//    public void vraagDrie() {
//        Long expected = -100L;
//        Account account = new Account(111L);
//        account.setId(expected);
//        em.getTransaction().begin();
//        em.persist(account);
//        //TODO: verklaar en pas eventueel aan IS ASSERTEQUALS EN GEEN NOTEQUALS
//        assertEquals(expected, account.getId());
//        System.out.println("First assert: " + account.getId());
//        em.flush();
//        //TODO: verklaar en pas eventueel aan vanaf flush wordt er een account naar de database gesynchronizeerd
//        assertNotEquals(expected, account.getId());
//        System.out.println("Second assert: " + account.getId());
//        em.getTransaction().commit();
//        //TODO: verklaar en pas eventueel aan
//    }
//
//    @Test
//    public void vraagVier() {
//        Long expectedBalance = 400L;
//        Account account = new Account(114L);
//        em.getTransaction().begin();
//        em.persist(account);
//        account.setBalance(expectedBalance);
//        em.getTransaction().commit();
//        assertEquals(expectedBalance, account.getBalance());
//        System.out.println("Account.getBalance: " + account.getBalance());
//        //TODO: verklaar de waarde van account.getBalance
//        Long cid = account.getId();
//        account = null;
//        EntityManager em2 = emf.createEntityManager();
//        em2.getTransaction().begin();
//        Account found = em2.find(Account.class, cid);
//        //TODO: verklaar de waarde van found.getBalance
//        assertEquals(expectedBalance, found.getBalance());
//        System.out.println("Found.getBalance: " + found.getBalance());
//    }
//    @Test
//    public void vraagVijf() {
//        Long expectedBalance = 400L;
//        Account account = new Account(114L);
//        em.getTransaction().begin();
//        em.persist(account);
//        account.setBalance(expectedBalance);
//        em.getTransaction().commit();
//        assertEquals(expectedBalance, account.getBalance());
//        //TODO: verklaar de waarde van account.getBalance
//        Long cid = account.getId();
//        account = null;
//        EntityManager em2 = emf.createEntityManager();
//        em2.getTransaction().begin();
//        Account found = em2.find(Account.class, cid);
//        
//        System.out.print("Balance of found: " + found.getBalance());
//        
//        em.getTransaction().begin();
//        Long changedBalance = 500L;
//        found.setBalance(changedBalance);
//        em.getTransaction().commit();
//        System.out.print("Changed balance of found: " + found.getBalance());
//        assertEquals(changedBalance, found.getBalance());
//    }
//
//    @Test
//    public void vraagZes() {
//        Account acc = new Account(1L);
//        Account acc2 = new Account(2L);
//        Account acc9 = new Account(9L);
//
//        // scenario 1
//        Long balance1 = 100L;
//        em.getTransaction().begin();
//        em.persist(acc);
//        acc.setBalance(balance1);
//        em.getTransaction().commit();
//        assertEquals(balance1, acc.getBalance());
//        //TODO: voeg asserties toe om je verwachte waarde van de attributen te verifieren.
//        //TODO: doe dit zowel voor de bovenstaande java objecten als voor opnieuw bij de entitymanager opgevraagde objecten met overeenkomstig Id.
//
//        Long balance2a = 211L;
//        Long balance2b = 222L;
//        em.getTransaction().begin();
//        acc2 = em.merge(acc);
//        acc.setBalance(balance2a);
//        acc2.setBalance(balance2b);
//        em.getTransaction().commit();
//        assertEquals(balance2b, acc.getBalance());
//        assertEquals(balance2b, acc2.getBalance());
//
//        // scenario 3
//        Long balance3c = 333L;
//        Long balance3d = 344L;
//        em.getTransaction().begin();
//        acc2 = em.merge(acc);
//        assertTrue(em.contains(acc));
//        assertTrue(em.contains(acc2));
//        assertEquals(acc, acc2);
//        acc2.setBalance(balance3c);
//        acc.setBalance(balance3d);
//        em.getTransaction().commit();
//        assertEquals(balance3d, acc.getBalance());
//        assertEquals(balance3d, acc2.getBalance());
//
//        // scenario 4
//        Account account = new Account(114L);
//        account.setBalance(450L);
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        em.persist(account);
//        em.getTransaction().commit();
//
//        Account account2 = new Account(114L);
//        Account tweedeAccountObject = account2;
//        tweedeAccountObject.setBalance(650l);
//        assertEquals((Long) 650L, account2.getBalance());  //blijkbaar worden account2 en tweedeAccountObject aan elkaar gelinkt.
//        account2.setId(account.getId());
//        em.getTransaction().begin();
//        account2 = em.merge(account2);
//        assertSame(account, account2);  //Beide objecten hebben hetzelfde ID gekregen bij het mergen
//        assertTrue(em.contains(account2));  //bij het mergen is account 2 samengevoegd en is dus onderdeel van de database
//        assertFalse(em.contains(tweedeAccountObject));  //eerder bleek al dat tweedeAccountObject gelink was aan account2 dus moet deze ook voorkomen in de database.
//        tweedeAccountObject.setBalance(850l);
//        assertEquals((Long) 650L, account.getBalance());  //bij het mergen is account overschreven met de waardes van account2
//        assertEquals((Long) 650L, account2.getBalance());  //de waardes van account2 zijn heftzelfde gebleven bij het mergen.
//        em.getTransaction().commit();
//        em.close();
//    }

//    @Test
//    public void vraagZeven() {
//        Account acc1 = new Account(77L);
//        em.getTransaction().begin();
//        em.persist(acc1);
//        em.getTransaction().commit();
//        //Database bevat nu een account.
//
//        // scenario 1        
//        Account accF1;
//        Account accF2;
//        accF1 = em.find(Account.class, acc1.getId());
//        accF2 = em.find(Account.class, acc1.getId());
//        assertSame(accF1, accF2);
//        System.out.println("First assert: " + accF1 + ", " + accF2);
//
//        // scenario 2        
//        accF1 = em.find(Account.class, acc1.getId());
//        em.clear();
//        accF2 = em.find(Account.class, acc1.getId());
//        //assertSame(accF1, accF2);
//        assertNotEquals(accF1, accF2);
//        //TODO verklaar verschil tussen beide scenario’s account wordt verwijderd, dus kan niet hetzelfde zijn.
//        System.out.println("Second assert: " + accF1 + ", " + accF2);
//    }
//    @Test
//    public void vraagAcht() {
//        Account acc1 = new Account(88L);
//        em.getTransaction().begin();
//        em.persist(acc1);
//        em.getTransaction().commit();
//        Long id = acc1.getId();
//        //Database bevat nu een account.
//        
//
//        em.remove(acc1);
//        assertEquals(id, acc1.getId());
//        Account accFound = em.find(Account.class, id);
//        assertNull(accFound);
//        //TODO: verklaar bovenstaande asserts
//    }
//    @Test
//    public void vraagNegen()
//    {
//        
////        //GenerationType.SEQUENCE
////        Account account = new Account(111L);
////        em.getTransaction().begin();
////        em.persist(account);
////        //TODO: verklaar en pas eventueel aan (was al goed)
////        assertNull(account.getId());
////        em.getTransaction().commit();
////        System.out.println("AccountId: " + account.getId());
////        //TODO: verklaar en pas eventueel aan (was al goed)
////        assertTrue(account.getId() > 0L);
//
////        //GenerationType.TABLE
////        Account account = new Account(111L);
////        em.getTransaction().begin();
////        em.persist(account);
////        //TODO: verklaar en pas eventueel aan (was al goed)
////        Long expected = 701L;
////        assertEquals(expected, account.getId());
////        em.getTransaction().commit();
////        System.out.println("AccountId: " + account.getId());
////        //TODO: verklaar en pas eventueel aan (was al goed)
////        assertTrue(account.getId() > 0L);
//    }
}
