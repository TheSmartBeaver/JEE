package myapp.dao;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myapp.model.Person;

public class TestDao {

   static Dao dao;

   @BeforeAll
   public static void beforeAll() {
      dao = new Dao();
      dao.init();
   }

   @AfterAll
   public static void afterAll() {
      dao.close();
   }

   @BeforeEach
   public void setUp() {
      // pour plus tard
   }

   @AfterEach
   public void tearDown() {
      // pour plus tard
   }

   @Test
   public void testVide() {
	   
   }
   
   /*Vérification 1 : Ajoutez un test unitaire afin de valider la création d'une personne et sa relecture.
   Vérifiez dans la table les informations ajoutées.*/
   @Test
   public void testCreationEtRelecturePersonne(){
	   System.out.println();
       Person p = new Person();
       p.setFirstName("Victorinne");

       Date birthday = new Date();
       birthday.setYear(1998);
       birthday.setMonth(3);
       birthday.setDate(17);
       p.setBirthDay(birthday);

       p.setId(1489);

       System.out.println("Je add Victorinette");
       dao.addPerson(p);
       Person pulledPerson = dao.findPerson(5);
       System.out.println("J'ai récup Victorinette");
       /*TODO: Comment comparer objets par champs et non références ???*/
       Assertions.assertEquals(pulledPerson, p);

   }

   /*Testez la génération des erreurs dues à deux personnes ayant le même prénom (remarquez la clause unique
   de la propriété firstName).*/
   @Test
   public void test2PersonWithSameId(){
       Person p = new Person();
       p.setFirstName("Victorinnette");

       Date birthday = new Date();
       birthday.setYear(1987);
       birthday.setMonth(3);
       birthday.setDate(29);
       p.setBirthDay(birthday);

       p.setId(185485);

       dao.addPerson(p);
       //Person pulledPerson = dao.findPerson(5);

       /*TODO: Comment comparer objets par champs et non références ???*/
       //Assertions.assertEquals(pulledPerson, p);

       Person p2 = new Person();
       p.setFirstName("Victorette");

       Date birthday2 = new Date();
       birthday.setYear(1997);
       birthday.setMonth(2);
       birthday.setDate(19);
       p.setBirthDay(birthday);

       p.setId(1445);

       dao.addPerson(p);
       //Person pulledPerson = dao.findPerson(5);

       /*TODO: Comment comparer objets par champs et non références ???*/
       //Assertions.assertEquals(pulledPerson, p);
   }

   @Test
   public void testFindAllPerson(){
       //System.out.println("Liste de toutes les personnes "+dao.findAllPersons());
   }

}