package myapp;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String mail;
    
    private String errorName;
    private String errorMail;

    public Person() {
    }

    public Person(Integer id, String name, String mail) {
        super();
        this.id = id;
        this.name = name;
        this.mail = mail;
    }

    public Person(Person p) {
        this(p.getId(), p.getName(), p.getMail());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

	public String getErrorMail() {
		return errorMail;
	}

	public void setErrorMail(String errorMail) {
		this.errorMail = errorMail;
	}

}