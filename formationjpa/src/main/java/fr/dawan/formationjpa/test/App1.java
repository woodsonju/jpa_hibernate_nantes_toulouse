package fr.dawan.formationjpa.test;

import fr.dawan.formationjpa.dao.GenericDAO;
import fr.dawan.formationjpa.entities.Formation;

public class App1 {

	public static void main(String[] args) {
		
		/*
		 * Creation d'une formation 
		 */
		Formation formation = new Formation();
		formation.setCode("FOR-DEV001");
		formation.setDuree(5);
		formation.setNom("Java - JPA / Hibernate");
		formation.setPrix(1999.99);
		
		GenericDAO.create(formation);
		
		System.out.println(formation);
		
	}

}
