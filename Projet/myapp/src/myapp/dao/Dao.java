package myapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import myapp.model.Person;

public class Dao {

   private EntityManagerFactory factory = null;

   public void init() {
      factory = Persistence.createEntityManagerFactory("myBase");
   }

   public void close() {
      if (factory != null) {
         factory.close();
      }
   }
   
   public Person addPerson(Person p) {
	   EntityManager em = null;
	   try {
	      em = factory.createEntityManager();
	      em.getTransaction().begin();
	      // utilisation de l'EntityManager
	      em.persist(p);
	      em.getTransaction().commit();
	      System.err.println("addPerson witdh id=" + p.getId());
	      return (p);
	   } finally {
	      if (em != null) {
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

}