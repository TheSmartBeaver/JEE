package mybootapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mybootapp.model.Party;

//sudo lsof -i -P -n | grep LISTEN
@Repository
public interface PartyRepository extends CrudRepository<Party, Long> {

	List<Party> findByPartyName(String partyName);
	List<Party> findByPartyNameLike(String partyName);

}