package fr.dawan.formationjpa.test;

import fr.dawan.formationjpa.dao.GenericDAO;
import fr.dawan.formationjpa.entities.Formation;

public class App2 {

	public static void main(String[] args) {
		
		Formation formation = GenericDAO.findById(Formation.class, 1);
		System.out.println(formation);
		
		formation.setPrix(799.95);
		formation.setDuree(2);
		GenericDAO.update(formation);
		
	}

}
