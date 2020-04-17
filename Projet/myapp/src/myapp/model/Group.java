package myapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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


import com.mysql.fabric.xmlrpc.base.Array;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Entity(name = "Group")

@Table(name = "TGroup",
   uniqueConstraints = {
      @UniqueConstraint(columnNames = {
         "group_name"
      })
   })

public class Group implements Serializable {

	private static final long serialVersionUID = 1L;

	   @Id()
	   //@GeneratedValue(strategy = GenerationType.AUTO)
	   private long id;

	   @Basic(optional = false)
	   @Column(name = "group_name", length = 200,
	      nullable = false, unique = true)
	   private String groupName;
	   
	   @Embedded
	   @ElementCollection
	   /*@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE },
			      fetch = FetchType.LAZY, mappedBy = "personGroup")*/
	   private List<Person> personsInGroup = new ArrayList<Person>();
	   

	   @Version()
	   private long version = 0;

	   @Transient
	   public static long updateCounter = 0;

	   public Group() {
	      super();
	   }

	   public Group(String groupName) {
	      super();
	      this.groupName = groupName;
	   }

	   @PreUpdate
	   public void beforeUpdate() {
	      System.err.println("PreUpdate of " + this);
	   }

	   @PostUpdate
	   public void afterUpdate() {
	      System.err.println("PostUpdate of " + this);
	      updateCounter++;
	   }

	   @Override
	   public String toString() {
	      return "Group(id=" + getId() + "," + groupName  + ","
	            + ",v" + getVersion() + ")";
	   }

	   public long getId() {
	      return id;
	   }

	   public void setId(long id) {
	      this.id = id;
	   }

	   public String getGroupName() {
	      return groupName;
	   }

	   public long getVersion() {
	      return version;
	   }

	   public void setVersion(long version) {
	      this.version = version;
	   }

	public void addPersonInGroup(Person p) {
		personsInGroup.add(p);
	}


	public List<Person> getPersonsInGroup() {
		return personsInGroup;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	   
	   
	
}
