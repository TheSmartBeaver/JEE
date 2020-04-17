package myapp.dao;

import java.util.Collection;
import java.util.List;

import myapp.model.Person;
import myapp.model.Group;

public interface IGroupDao {

	   // récupérer les groupes
	   List<Group> findAllGroups();

	   // modification ou ajout d'une nouvelle personne
	   void saveGroup(Group g);

	   }