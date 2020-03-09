package myapp.dao;

import myapp.model.Person;
import org.junit.jupiter.api.*;

import java.util.Date;

/**La classe Dao est instanciée une seule fois (annotation @BeforeAll) car la création d'une EntityManagerFactory
 * est très coûteuse. C'est typiquement une opération réalisée à l'initialisation de l'application.
 */
public class TestDao {

    /*Après ces tests nous avons vérifié les paramètres et la connection avec le SGBDR.*/

    /*Avec l'ajout de la classe myapp.model.Person, on a des traces dans les logs de ce test unitaire de la
    création d'une table "Person"
     */
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

        Person p = new Person();
        p.setFirstName("Victorinne");

        Date birthday = new Date();
        birthday.setYear(1998);
        birthday.setMonth(3);
        birthday.setDate(17);
        p.setBirthDay(birthday);

        p.setId(1489);

        dao.addPerson(p);
        //Person pulledPerson = dao.findPerson(5);

        /*TODO: Comment comparer objets par champs et non références ???*/
        //Assertions.assertEquals(pulledPerson, p);

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
        System.out.println("Liste de toutes les personnes "+dao.findAllPersons());
    }

}

