package fr.dawan.yourbank.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long code;
	
	private String nom;
	
	private String email;
	
	/*
	 * mappedBy(L'entité esclave) : Dans la classe compte, un client est representé par l'attribut client 
	 * L'entité esclave doit contenir une annotation @OneToMany et une attribut mappedBy, qui doit referencer 
	 * le champ qui porte la relation coté maitre (ici client)
	 */
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<Compte> comptes;
	
	public Client() {
		super();
		comptes = new ArrayList<Compte>();
	}

	public Client(String nom, String email) {
		super();
		this.nom = nom;
		this.email = email;
		comptes = new ArrayList<Compte>();
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}
	
	public void addComptes(Compte compte) {
		if(!comptes.contains(compte) && compte != null) {
			this.comptes.add(compte);
		}
	}
	
	public void removeComptes(Compte compte) {
		this.comptes.remove(compte);
		compte.setClient(null);
	}

	@Override
	public String toString() {
		return "Client [code=" + code + ", nom=" + nom + ", email=" + email + "]";
	}
	
	

}
