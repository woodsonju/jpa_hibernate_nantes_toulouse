package fr.dawan.formationjpa.test;

import fr.dawan.formationjpa.dao.GenericDAO;
import fr.dawan.formationjpa.entities.Formateur;
import fr.dawan.formationjpa.entities.Formation;

public class App8 {

	public static void main(String[] args) {
		
		//On récupère le formateur ayant l'id 7 
		Formateur formateur = GenericDAO.findById(Formateur.class, 7);
		System.out.println("Nb de compétences pour Cham : " + formateur.getCompetences().size());
		
		//Pour chaque formation comprise dans la liste de compétence du formateur 
		for (Formation formation : formateur.getCompetences()) {
			System.out.println(formation);
		}
	}

}
