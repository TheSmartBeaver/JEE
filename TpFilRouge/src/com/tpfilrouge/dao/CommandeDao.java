package com.tpfilrouge.dao;

import java.util.List;

import com.tpfilrouge.beans.Commande;

public interface CommandeDao {
   void creer(Commande commande) throws DAOException;

   Commande trouver(long id) throws DAOException;

   List<Commande> lister() throws DAOException;

   void supprimer(Commande commande);
}
