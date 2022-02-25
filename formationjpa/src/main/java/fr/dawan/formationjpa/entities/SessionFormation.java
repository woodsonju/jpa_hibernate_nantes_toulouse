package fr.dawan.formationjpa.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SessionFormation extends DbObject{
	
	private String lieu; 
	private LocalDate date;
	private int nbPlaceMaxi;
	private int nbPlace;
	
	/*
	 * L'entité qui est relié à une seule entité est l'entité qui possédera la clé étrangère
	 * Elle est considéré comme l'entité propriétaire (maitre) de la relation
	 * 
	 * Cette relation se déclare avec l'annotation @ManyToOne  
	 * On peut specifier le nom de la clé etrangère avec l'annotation @JoinColumn 
	 */
	//@ManyToOne(cascade = CascadeType.PERSIST)
	@ManyToOne
	@JoinColumn(name="id_formation")
	private Formation formation;
	
	@ManyToOne
	private Formateur formateur;
	
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getNbPlaceMaxi() {
		return nbPlaceMaxi;
	}
	public void setNbPlaceMaxi(int nbPlaceMaxi) {
		this.nbPlaceMaxi = nbPlaceMaxi;
	}
	public int getNbPlace() {
		return nbPlace;
	}
	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}
	public Formation getFormation() {
		return formation;
	}
	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	
	public Formateur getFormateur() {
		return formateur;
	}
	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}
	
	@Override
	public String toString() {
		return "SessionFormation [lieu=" + lieu + ", date=" + date + ", nbPlaceMaxi=" + nbPlaceMaxi + ", nbPlace="
				+ nbPlace + ", formation=" + formation + ", formateur=" + formateur + ", getId()=" + getId() + "]";
	}
	
	
}
