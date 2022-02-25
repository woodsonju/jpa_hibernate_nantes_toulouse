package fr.dawan.formationjpa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Trainer")
public class Formateur extends DbObject{
	
	//unique : permet d'empecher les doublons sur cet attribut
	@Column(name="t_matricule", unique = true)
	private String matricule;
	
	@Column(name="t_lastname", length = 100, nullable = false)
	private String nom;
	
	@Column(name="t_firstname")
	private String prenom;
	
	@Column(name="t_isdawan")
	private boolean estInterne;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	private Set<Formation> competences = new HashSet<Formation>();

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public boolean isEstInterne() {
		return estInterne;
	}

	public void setEstInterne(boolean estInterne) {
		this.estInterne = estInterne;
	}

	public Set<Formation> getCompetences() {
		return competences;
	}


//	public void setCompetences(Set<Formation> competences) {
//		this.competences = competences;
//	}
	
	public void addCompetences(Formation formation) {
		this.competences.add(formation);
	}
	
	public void removeCompetences(Formation f) {
		competences.remove(f);
	}

	@Override
	public String toString() {
		return "Formateur [matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom + ", estInterne="
				+ estInterne + ", getId()=" + getId() + "]";
	}



	
	
}
