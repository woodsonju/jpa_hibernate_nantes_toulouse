package fr.dawan.formationjpa.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/*
 * @MappedSuperclass : Dans ce cas la classe n'est pas une entité JPA, mais ses
 * 					   champs seront enregistrés en base
 */
@MappedSuperclass
public class DbObject {
	
	/*
	 * L'annotation @Id permet de spécifier que l'attribut id sera la clé primaire 
	 * dans la table
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public long getId() {
		return id;
	}
	
}
