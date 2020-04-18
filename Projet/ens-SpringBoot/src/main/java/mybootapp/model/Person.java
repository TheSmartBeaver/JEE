package mybootapp.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

//import mybootapp.model.Group;

//import myapp.model.Group;

	@Entity
	public class Person {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;

		@Column(nullable = false)
		private String firstName;
		
		   @Basic(optional = false)
		   @Column(nullable = false)
		   private String lastName;
		   
		   @Basic(optional = true)
		   @Column(length = 200)
		   private String mail;
		   
		   @Basic(optional = true)
		   @Column(length = 200)
		   private String website;
		   
		   //TODO: Trouver moyen sécuriser de stocker MDP ??
		   @Basic(optional = false)
		   @Column(nullable = false)
		   private String password;

		   @Basic()
		   @Temporal(TemporalType.DATE)
		   @Column()
		   private Date birthDay;

			/*@ManyToOne(optional = true)
			@JoinColumn(name = "personParty")
			private Party personParty;*/

		   @Version()
		   private long version = 0;

		   @Transient
		   public static long updateCounter = 0;

		public Person() {
			
		}

		public Person(String firstName, String lastName, String password) {
		      super();
		      this.firstName = firstName;
		      this.lastName = lastName;
		      this.password = password;
		   }

		public Long getId() {
			return id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		
		/*public void setPersonGroup(Party personParty) {
			this.personParty = personParty;
		}*/

	}