package mybootapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;


import mybootapp.model.Person;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Entity(name = "Party")

@Table(name = "TParty",
   uniqueConstraints = {
      @UniqueConstraint(columnNames = {
         "party_name"
      })
   })

public class Party implements Serializable {

	private static final long serialVersionUID = 1L;

	   @Id()
	   @GeneratedValue(strategy = GenerationType.AUTO)
	   private long id;

	   @Basic(optional = false)
	   @Column(name = "party_name", length = 200,
	      nullable = false, unique = true)
	   private String partyName;
	   
	   @OneToMany(cascade = { CascadeType.ALL },
			      fetch = FetchType.LAZY, mappedBy = "personParty")
	   private Set<Person> personsInParty = new HashSet<Person>();
	   

	   @Version()
	   private long version = 0;

	   @Transient
	   public static long updateCounter = 0;

	   public Party() {
	      super();
	   }

	   public Party(String partyName) {
	      super();
	      this.partyName = partyName;
	   }


	   public long getId() {
	      return id;
	   }

	   public void setId(long id) {
	      this.id = id;
	   }

	   public String getPartyName() {
	      return partyName;
	   }

	   public long getVersion() {
	      return version;
	   }

	   public void setVersion(long version) {
	      this.version = version;
	   }

	public void addPersonInParty(Person p) {
		if (personsInParty == null) {
	         personsInParty = new HashSet<>();
	      }
	      personsInParty.add(p);
	      p.setPersonParty(this);
	}


	public Set<Person> getPersonsInParty() {
		return personsInParty;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	
	@Override
	public String toString() {
		return partyName + " " + id;
	}
	   
	   
	
}