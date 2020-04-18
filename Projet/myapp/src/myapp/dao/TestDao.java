package myapp.dao;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import myapp.model.Person;
import mybootapp.model.Group;
import net.bytebuddy.asm.Advice.Exit;

public class TestDao {

   static DaoPerson daoPerson;
   static DaoGroup daoGroup;

   @BeforeAll
   public static void beforeAll() {
      daoPerson = new DaoPerson();
      daoGroup = new DaoGroup();
   }

   @AfterAll
   public static void afterAll() {
	  daoPerson.close();
      daoGroup.close();
   }

   @BeforeEach
   public void setUp() {
	   daoPerson.init();
	   daoPerson.clearDatabase(); /*A enlever après ??*/
	   
	   daoGroup.init();
	   daoGroup.clearDatabase();
   }

   @AfterEach
   public void tearDown() {
      // pour plus tard
   }

   @Test
   public void testVide() {
	   
   }
   
   @Test
   public void testAddPerson() {
	   //TODO: Il ne faut pas setId car AutoGenerated car sinon "detached entity problem"
	   EntityManagerFactory emf = Persistence.createEntityManagerFactory("myBase");    
	    EntityManager em = emf.createEntityManager();    
	    EntityTransaction transac = em.getTransaction();
	    transac.begin();
	    Person nouvellePersonne = new Person("Painbeurre", "Totor", "password");
	    nouvellePersonne.setId(1);
	    
	    daoPerson.addPerson(nouvellePersonne);
	    System.out.println("Liste de toutes les personnes "+daoPerson.findAllPersons());
	    
   }
   
   /*Vérification 1 : Ajoutez un test unitaire afin de valider la création d'une personne et sa relecture.
   Vérifiez dans la table les informations ajoutées.*/
   @Test
   public void testCreationEtRelecturePersonne(){
	   System.out.println();
	   
       Person p = new Person("Painbeurrette", "Totorinette", "password");
       p.setId(1);
       p.setBirthDay(new Date());
	   p.setMail("aaaaaaaa@gmail.com");
	   p.setWebsite("monWebsite");
	    
       daoPerson.savePerson(p); /*Utilisation de savePerson et non add*/
       System.out.println("On cherche à récup person d'id : "+p.getId());
       System.out.println("Liste de toutes les personnes "+daoPerson.findAllPersons());
       Person pulledPerson = daoPerson.findPerson(p.getId());
       System.out.println("J'ai récup Victorinette "+pulledPerson);
       System.out.println(pulledPerson.getMail() +" "+pulledPerson.getPassword());
       //System.exit(1);
       /*TODO: Comment comparer objets par champs et non références ???*/
       Assertions.assertEquals(pulledPerson, p);

   }
   
   @Test
   public void testCreationGroupePasVide(){
	   Person p = new Person("Painbeurre", "Tartiflette", "password");
	   p.setId(10);
	   System.out.println("111111111 "+p);
       daoPerson.addPerson(p);
       Group g = new Group("Noobs");
       g.setId(1);
       System.out.println("222222222 "+p);
       g.addPersonInGroup(p);
       System.err.println("Les personnes du groupe avant insertion : "+ g.getPersonsInGroup());
       daoGroup.saveGroup(g);
       System.err.println("Les personnes du groupe après insertion : "+daoGroup.findGroup(g.getId()).getPersonsInGroup());
       

   }
   
   @Test
   public void testAddInGroupAndRemoveIt() {
	   Person p = new Person("Painbeurre", "Tartiflette", "password");
	   p.setId(10);
	   System.out.println("111111111 "+p);
       daoPerson.addPerson(p);
       Group g = new Group("Noobs");
       g.setId(1);
       System.out.println("222222222 "+p);
       g.addPersonInGroup(p);
       System.err.println("Les personnes du groupe avant insertion : "+ g.getPersonsInGroup());
       daoGroup.saveGroup(g);
       System.err.println("Les personnes du groupe après insertion : "+daoGroup.findGroup(g.getId()).getPersonsInGroup());
       //TODO: faire remove Person pour voir si....
       daoPerson.removePerson(10);
       System.err.println("Les personnes du groupe après suppression : "+daoGroup.findGroup(g.getId()).getPersonsInGroup());
       System.exit(1);
   }
   

   /*Testez la génération des erreurs dues à deux personnes ayant le même prénom (remarquez la clause unique
   de la propriété firstName).*/
   @Test
   public void test2PersonWithSameId(){

   }

}