package fr.dawan.formationjpa.test;

import java.util.List;

import fr.dawan.formationjpa.dao.GenericDAO;
import fr.dawan.formationjpa.entities.Formateur;

public class App9 {

	public static void main(String[] args) {
		
		List<Formateur> formateurs = GenericDAO.findAll(Formateur.class);
		System.out.println("TOUS LES FORMATEURS");
		for (Formateur formateur : formateurs) {
			System.out.println(formateur);
		}
		
		List<Formateur> listFormateurs = GenericDAO.findAll(Formateur.class, 2, 3);
		for (Formateur formateur : listFormateurs) {
			System.out.println("1--------"+ formateur);
		}
	}

}
