package myapp.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import myapp.model.Person;
import myapp.model.Group;

public class DaoGroup implements IGroupDao {
	
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


	public Group findGroup(long id) {
	    EntityManager em = null;
	    try {
	        em = factory.createEntityManager();
	        em.getTransaction().begin();
	        // utilisation de l'EntityManager
	        Group g = em.find(Group.class, id);
	        return g;
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
	        nb += em.createQuery("DELETE FROM Group").executeUpdate();
	        System.out.println(nb + " entities Group deleted");
	        em.getTransaction().commit();
	    } finally {
	      if (em != null) {
	         em.close();
	      }
	    }
	}

	@Override
	public List<Group> findAllGroups() {
		EntityManager em = null;
	    try {
	        em = newEntityManager();
	        String query = "SELECT q FROM Group q";
	        TypedQuery<Group> q = em.createQuery(query, Group.class);
	        return q.getResultList();
	    } finally {
	        closeEntityManager(em);
	    }
	}

	@Override
	public void saveGroup(Group g) {
		EntityManager em = null;
		try {
		   em = newEntityManager();
		   //merge à la pace de persist pour remplacer au cas où existe ??
		   em.merge(g);
		   em.getTransaction().commit();
		   System.err.println("addGroup witdh id=" + g.getId());
		} finally {
		   closeEntityManager(em);
		}
	}
	
	public void addPersonInGroup(Person p, long id_group) {
		EntityManager em = null;
		try {
		   em = newEntityManager();
		   Group g = findGroup(id_group);
		   g.addPersonInGroup(p);
		   System.out.println("Nous avons ajouté la personnes dans ce groupe : "+g );
		   saveGroup(g);
		} finally {
		   closeEntityManager(em);
		}
	}

}