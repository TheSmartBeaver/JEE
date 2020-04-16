package myapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import myapp.model.Person;

public class Dao {

	/*DAO = objet d'accès aux données */
	
	/*Pour agir sur les entités nous devons récupérer à partir de l'usine une instance
	 * de l'interface EntityManager. Celle-ci va permettre les opérations CRUD de persistance
	 * sur les entités (Create, Read, Update et Delete). Un EntityManager ne supporte pas le
	 * multi-threading. Il doit donc être créé et détruit à chaque utilisation par un thread.
	 * Cette création est une opération peu coûteuse qui peut se reproduire un grand nombre de
	 * fois (contrairement à l'usine).*/
	
   private EntityManagerFactory factory = null;

   public void init() {
      factory = Persistence.createEntityManagerFactory("myBase");
   }

   public void close() {
      if (factory != null) {
         factory.close();
      }
   }
   
// Créer un EM et ouvrir une transaction
private EntityManager newEntityManager() {
   EntityManager em = factory.createEntityManager();
   em.getTransaction().begin();
   return (em);
}

   
//Nouvelle version simplifiée
public Person addPerson(Person p) {
EntityManager em = null;
try {
   em = newEntityManager();
   // utilisation de l'EntityManager
   em.persist(p);
   em.getTransaction().commit();
   System.err.println("addPerson witdh id=" + p.getId());
   return (p);
} finally {
   closeEntityManager(em);
}
}

//Fermer un EM et défaire la transaction si nécessaire
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


	/*
	 * Supprimer toutes les instances pour faciliter les tests.
	 */
	public void clearDatabase() {
	    EntityManager em = null;
	    try {
	        em = factory.createEntityManager();
	        em.getTransaction().begin();
	        int nb = 0;
	        nb += em.createQuery("DELETE FROM Person").executeUpdate();
	        System.out.println(nb + " entities deleted");
	        em.getTransaction().commit();
	    } finally {
	      if (em != null) {
	         em.close();
	      }
	    }
	}
	
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

}