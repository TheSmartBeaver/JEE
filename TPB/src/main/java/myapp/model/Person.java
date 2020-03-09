package myapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity(name = "Person")

/*L'annotation Table permet de préciser le nom de la table associée à une classe ainsi que d'ajouter des conditions d'unicité.*/
@Table(name = "TPerson",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "first_name", "birth_day"
                })
        })

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic(optional = false)
    @Column(name = "first_name", length = 200,
            nullable = false, unique = true)
    private String firstName;

    @Basic()
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_day")
    private Date birthDay;

    /*Les classes embarquées permettent de coder plus facilement des relations un-un comme par exemple,
    le fait que chaque personne doit avoir une et une seule adresse.*/
    //@Embedded
    //private Address address;

    /*Indique la propriété à utiliser pour activer et gérer la version des données. Cette capacité est
    notamment utile pour implémenter une stratégie de concurrence optimiste*/
    @Version()
    private long version = 0;

    @Transient
    public static long updateCounter = 0;

    public Person() {
        super();
    }

    public Person(String firstName, Date birthDay) {
        super();
        this.firstName = firstName;
        this.birthDay = birthDay;
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
        return "Person(id=" + getId() + "," + firstName + "," + birthDay + ","
                + ",v" + getVersion() + ")";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    }