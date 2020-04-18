package mybootapp.model;

import java.io.Serializable;
import java.util.Date;
//import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
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


	@Entity
	public class Person {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;

		@Column(nullable = false)
		private String firstName;


		public Person(String firstName) {
			super();
			this.firstName = firstName;
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

		public void setName(String firstName) {
			this.firstName = firstName;
		}

	}