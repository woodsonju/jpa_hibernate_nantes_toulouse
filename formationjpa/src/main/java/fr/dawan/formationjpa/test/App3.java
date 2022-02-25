package fr.dawan.formationjpa.test;

import fr.dawan.formationjpa.dao.GenericDAO;
import fr.dawan.formationjpa.entities.Formation;

public class App3 {

	public static void main(String[] args) {
		
		/*
		 * Creation d'une formation 
		 */
		Formation formation = new Formation();
		formation.setCode("FOR-DEV002");
		formation.setDuree(5);
		formation.setNom("Java - Spring MVC");
		formation.setPrix(2532);
		
		GenericDAO.create(formation);
		System.out.println(formation);
		
		//On supprime la formation dont l'id est égal à 1
		GenericDAO.delete(Formation.class, 1);
		
		Formation formation2 = GenericDAO.findById(Formation.class, 1);
		System.out.println(formation2 == null);
	}

}
