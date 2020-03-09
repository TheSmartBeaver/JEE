package myapp.dao;

import myapp.model.Person;

import javax.persistence.*;
import java.util.List;

public class Dao {

    private EntityManagerFactory factory = null;

    public void init() {
        /*Lors de l'initialisation, la classe Persistence est utilisée pour analyser
        les paramètres de connection (fichier persistence.xml) et trouver l'unité de
        persistance passée en paramètre (myBase dans cet exemple)*/
        factory = Persistence.createEntityManagerFactory("myBase");
        /*A l'issue de cette étape nous récupérons une instance de l'interface EntityManagerFactory.
        Cette usine, qui est généralement un singleton, nous permettra, dans un deuxième temps,
        d'ouvrir des connections vers la base de données.*/
    }

    public void close() {
        if (factory != null) {
            factory.close();
        }
    }

    /*Après création table "Person" ?? nous pouvons enrichir notre service DAO avec les quatre actions classiques :
    ajout, lecture, modification et destruction.
     */
    /*public Person addPerson(Person p) {
        /*Pour agir sur les entités nous devons récupérer à partir de l'usine une instance de l'interface EntityManager.
        Celle-ci va permettre les opérations CRUD de persistance sur les entités (Create, Read, Update et Delete).*/
        /*EntityManager em = null;
        try {
            /*Un EntityManager ne supporte pas le multi-threading. Il doit donc être créé et détruit à chaque utilisation
            par un thread. Cette création est une opération peu coûteuse qui peut se reproduire
            un grand nombre de fois (contrairement à l'usine).*/
            /*em = factory.createEntityManager();
            em.getTransaction().begin();
            // utilisation de l'EntityManager

            //TODO: Passage de persist à merge = marche ! Comment ça ? persist pour entités nouvelles !
            //em.persist(p);
            em.merge(p);
            em.getTransaction().commit();
            System.err.println("addPerson witdh id=" + p.getId());
            return (p);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    // Nouvelle version simplifiée
    public Person addPerson(Person p) {
        EntityManager em = null;
        try {
            em = newEntityManager();
            // utilisation de l'EntityManager
            //em.persist(p);
            /*Problème CASCADE*/
            em.merge(p);
            em.getTransaction().commit();
            System.err.println("addPerson witdh id=" + p.getId());
            return (p);
        } finally {
            closeEntityManager(em);
        }
    }

    // Créer un EM et ouvrir une transaction
    private EntityManager newEntityManager() {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        return (em);
    }

    // Fermer un EM et défaire la transaction si nécessaire
    private void closeEntityManager(EntityManager em) {
        if (em != null) {
            if (em.isOpen()) {
                EntityTransaction t = em.getTransaction();
                if (t.isActive()) {
                    try {
                        t.rollback();
                    } catch (PersistenceException e) {
                    }
                }
                em.close();
            }
        }
    }




    public Person findPerson(long id) {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();
            em.getTransaction().begin();
            // utilisation de l'EntityManager
            Person p = em.find(Person.class, id);
            return p;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
//listage des personnes
    public List<Person> findAllPersons() {
        EntityManager em = null;
        try {
            em = newEntityManager();
            String query = "SELECT p FROM Person p";
            TypedQuery<Person> q = em.createQuery(query, Person.class);
            return q.getResultList();
        } finally {
            closeEntityManager(em);
        }
    }

    public void updatePerson(Person p) {

    }

    public void removePerson(long id) {

    }

}