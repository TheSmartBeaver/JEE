package mybootapp.authentif;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component()
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
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
