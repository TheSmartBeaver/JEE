package mybootapp.authentif;


public class Utilisateur {

    private String email;
    private String motDePasse;
    private String nom;
    private Long id;

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setEmail(String email) {
	this.email = email;
    }
    public String getEmail() {
	return email;
    }

    public void setMotDePasse(String motDePasse) {
	this.motDePasse = motDePasse;
    }
    public String getMotDePasse() {
	return motDePasse;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }
    public String getNom() {
	return nom;
    }
}
