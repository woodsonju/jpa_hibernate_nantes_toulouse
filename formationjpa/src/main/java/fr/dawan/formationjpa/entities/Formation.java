package fr.dawan.formationjpa.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Formation extends DbObject{
	
	@Column(length = 50)
	private String nom;
	
	private String code;
	
	@Column(precision = 2)
	private double prix;
	
	private int duree;
	
	/*
	 * L'entité SessionFormation n'est plus accessible depuis l'entité Formation, car l'entité Formation n'a pas de reference 
	 *  sur l'entité SessionFormation. Pour resoudre ce problème JPA met en place le concept de champs inversé . 
	 * C'est à dire que la relation ne se trouve pas directement dans l'entité principale mais au niveau de la deuxième entité de la 
	 * relation (entité esclave).
	 * Ce champs inversé est defini par la propriété mappedBy.  
	 * 
	 * Dans l'entité Esclave on va donc ajouter un attribut mappedby
	 * L'attribut mappedBy, doit référencer le champs qui porte la relation 
	 * côté propriétaire (maitre) C'est à dire le champs formation 
	 * Ainsi, on relie cette entité à l'autre entité via la clé étrangère 
	 * 
	 * mappedBy signale à Hibernate que la clé de la relation se trouve de l'autre côté (entité propriétaire)
	 * 
	 * Cela signifie que même si on lie 2 tables ensemble, une seule de ces tables aura une contrainte de clé 
	 * étrangère sur l'autre
	 * 
	 * mappedBy nous permet de toujours lier la table ne contenant pas la contrainte à l'autre table
	 * 
	 * Sans l'annotation mappedBy, hibernate va nous créer une table de relation , permettant de relier 
	 * les deux tables.
	 * 
	 * 
	 * L'annotation mappedBy nous permet d'eviter de créer des tables d'association 
	 * inutiles (ex : formation_sessionformation)
	 */
	
	//Test avec les méthodes de la classe GenericDao
	//@OneToMany(fetch = FetchType.EAGER, mappedBy = "formation", cascade = CascadeType.PERSIST)
	//Test avec la methode createFormation de la classe FormationDAO
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "formation")
	private List<SessionFormation> sessions = new ArrayList<SessionFormation>();
	
	@ManyToMany(mappedBy = "competences")
	private List<Formateur> formateursCompetents = new ArrayList<Formateur>();

	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}
	

	public List<SessionFormation> getSessions() {
		return sessions;
	}

//	public void setSessions(List<SessionFormation> sessions) {
//		this.sessions = sessions;
//	}
	
	/**
	 * La méthode addSession nous permet d'ajouter une session dans l'entité (l'objet formation)
	 * Avec cette methode on pourra ajouter autant de session que l'on veut pour une formation donnée
	 * Ainsi avec Cascade.PERSIST lorsqu'on sauvegardera une formation en base de données, la cascade sauvegardera 
	 * aussi les sessions de cette formation
	 * 
	 * @param session : session d'une formation
	 */
	public void addSession(SessionFormation session) {
		if(!this.sessions.contains(session) && session != null) {
			this.sessions.add(session);
		}
	}
	
	public void removeSession(SessionFormation session) {
		this.sessions.remove(session);
	}



	public List<Formateur> getFormateursCompetents() {
		return formateursCompetents;
	}

//	public void setFormateursCompetents(List<Formateur> formateursCompetents) {
//		this.formateursCompetents = formateursCompetents;
//	}

	public void addFormateurCompetent(Formateur formateur) {
		if(!formateursCompetents.contains(formateur) && formateur != null) {
			formateursCompetents.add(formateur);
		}
	}
	
	public void removeFormateurCompetent(Formateur formateur) {
		formateursCompetents.remove(formateur);
	}

	@Override
	public String toString() {
		return "Formation [nom=" + nom + ", code=" + code + ", prix=" + prix + ", duree=" + duree + ", getId()="
				+ getId() + "]";
	}
}
