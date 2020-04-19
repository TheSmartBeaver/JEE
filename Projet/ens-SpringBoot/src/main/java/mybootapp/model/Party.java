package mybootapp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import mybootapp.model.Person;

//import mybootapp.model.Group;

//import myapp.model.Group;

	@Entity
	public class Party {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;

		@Column(nullable = false)
		private String partyName;

		   /*@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE },
				      fetch = FetchType.LAZY, mappedBy = "personParty")
		   private Set<Person> personsInParty = new HashSet<Person>();*/

		   @Version()
		   private long version = 0;

		   @Transient
		   public static long updateCounter = 0;

		public Party() {
			
		}

		public Party(String partyName) {
		      super();
		      this.partyName = partyName;
		   }

		public Long getId() {
			return id;
		}

		public String getPartyName() {
			return partyName;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public void setPartyName(String partyName) {
			this.partyName = partyName;
		}
		
		
		/*public void addPersonInGroup(Person p) {
			if (personsInParty == null) {
		         personsInParty = new HashSet<>();
		      }
		      personsInParty.add(p);
		      p.setPersonGroup(this);
		}*/

	}