package mybootapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import mybootapp.model.Party;

@Entity(name = "Person")

@Table(name = "TPerson")
public class Person implements Serializable, Comparable<Person> {

   private static final long serialVersionUID = 1L;

   @Id()
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id_Person;

   @Basic(optional = false)
   @Column(name = "first_name", length = 200,
      nullable = false, unique = false)
   private String firstName;
   
   @Basic(optional = false)
   @Column(name = "last_name", length = 200,
      nullable = false, unique = false)
   private String lastName;
   
   @Basic(optional = true)
   @Column(name = "mail", length = 200, 
   		unique = true)
   private String mail;
   
   @Basic(optional = true)
   @Column(name = "site_web", length = 200, 
   		unique = false)
   private String website;
   
   //TODO: Trouver moyen sécuriser de stocker MDP ??
   @Basic(optional = false)
   @Column(name = "password", length = 200,
      nullable = false, unique = false)
   private String password;

   @Basic(optional = true)
   @Temporal(TemporalType.DATE)
   @Column(name = "birth_day")
   private Date birthDay;

	@ManyToOne(optional = true)
	@JoinColumn(name = "personParty")
	private Party personParty;

   @Version()
   private long version = 0;

   @Transient
   public static long updateCounter = 0;

   public Person() {
      super();
   }

   public Person(String firstName, String lastName, String email, String password) {
      super();
      this.firstName = firstName;
      this.lastName = lastName;
      this.mail = email;
      this.password = password;
   }

   @Override
   public String toString() {
      return "Person(id=" + getId() + "," + firstName
            + ",lastName : " + getLastName() +", email "+ mail + " password :"+ password +")";
   }

   public long getId() {
      return id_Person;
   }

   public void setId(long id) {
      this.id_Person = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public Date getBirthDay() {
      return birthDay;
   }

   public void setBirthDay(Date birthDay) {
      this.birthDay = birthDay;
   }

   public long getVersion() {
      return version;
   }

   public void setVersion(long version) {
      this.version = version;
   }
   
   public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getMail() {
	return mail;
}

public void setMail(String mail) {
	this.mail = mail;
}

public String getWebsite() {
	return website;
}

public void setWebsite(String website) {
	this.website = website;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public Party getPersonParty() {
	return personParty;
}

public void setPersonParty(Party personParty) {
	this.personParty= personParty;
}

@Override
public int compareTo(Person p) {
	if(this.firstName.compareTo(p.firstName)==0)
		return this.lastName.compareTo(p.lastName);
	else
		return this.firstName.compareTo(p.firstName);
	
}




}