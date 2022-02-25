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
	 * L'entit� SessionFormation n'est plus accessible depuis l'entit� Formation, car l'entit� Formation n'a pas de reference 
	 *  sur l'entit� SessionFormation. Pour resoudre ce probl�me JPA met en place le concept de champs invers� . 
	 * C'est � dire que la relation ne se trouve pas directement dans l'entit� principale mais au niveau de la deuxi�me entit� de la 
	 * relation (entit� esclave).
	 * Ce champs invers� est defini par la propri�t� mappedBy.  
	 * 
	 * Dans l'entit� Esclave on va donc ajouter un attribut mappedby
	 * L'attribut mappedBy, doit r�f�rencer le champs qui porte la relation 
	 * c�t� propri�taire (maitre) C'est � dire le champs formation 
	 * Ainsi, on relie cette entit� � l'autre entit� via la cl� �trang�re 
	 * 
	 * mappedBy signale � Hibernate que la cl� de la relation se trouve de l'autre c�t� (entit� propri�taire)
	 * 
	 * Cela signifie que m�me si on lie 2 tables ensemble, une seule de ces tables aura une contrainte de cl� 
	 * �trang�re sur l'autre
	 * 
	 * mappedBy nous permet de toujours lier la table ne contenant pas la contrainte � l'autre table
	 * 
	 * Sans l'annotation mappedBy, hibernate va nous cr�er une table de relation , permettant de relier 
	 * les deux tables.
	 * 
	 * 
	 * L'annotation mappedBy nous permet d'eviter de cr�er des tables d'association 
	 * inutiles (ex : formation_sessionformation)
	 */
	
	//Test avec les m�thodes de la classe GenericDao
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
	 * La m�thode addSession nous permet d'ajouter une session dans l'entit� (l'objet formation)
	 * Avec cette methode on pourra ajouter autant de session que l'on veut pour une formation donn�e
	 * Ainsi avec Cascade.PERSIST lorsqu'on sauvegardera une formation en base de donn�es, la cascade sauvegardera 
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
