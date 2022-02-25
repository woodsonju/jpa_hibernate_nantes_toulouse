package fr.dawan.formationjpa.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/*
 * @MappedSuperclass : Dans ce cas la classe n'est pas une entit� JPA, mais ses
 * 					   champs seront enregistr�s en base
 */
@MappedSuperclass
public class DbObject {
	
	/*
	 * L'annotation @Id permet de sp�cifier que l'attribut id sera la cl� primaire 
	 * dans la table
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public long getId() {
		return id;
	}
	
}
