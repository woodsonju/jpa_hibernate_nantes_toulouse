package fr.dawan.formationjpa.test;

import java.util.UUID;

import fr.dawan.formationjpa.dao.GenericDAO;
import fr.dawan.formationjpa.entities.Formateur;
import fr.dawan.formationjpa.entities.Formation;

public class App7 {

	public static void main(String[] args) {
		
		Formateur formateur1 = new Formateur();
		formateur1.setEstInterne(true);
		formateur1.setMatricule(UUID.randomUUID().toString());
		formateur1.setNom("Bien");
		formateur1.setPrenom("Jim");
		
		Formateur formateur2 = new Formateur();
		formateur2.setEstInterne(false);
		formateur2.setMatricule(UUID.randomUUID().toString());
		formateur2.setNom("Trop");
		formateur2.setPrenom("Cool");
		
		Formateur formateur3 = new Formateur();
		formateur3.setEstInterne(true);
		formateur3.setMatricule(UUID.randomUUID().toString());
		formateur3.setNom("Vaga");
		formateur3.setPrenom("Cham");
		
		Formation formationSpring = new Formation();
		formationSpring.setCode("FOR-DEV002");
		formationSpring.setDuree(5);
		formationSpring.setNom("Java - Spring MVC");
		formationSpring.setPrix(2254);
		
		Formation formationWeb = new Formation();
		formationWeb.setCode("FOR-DEV003");
		formationWeb.setDuree(6);
		formationWeb.setNom("Webmaster - Web");
		formationWeb.setPrix(854);

		//Associer des formations à des formateurs
		//et/ou associer des formateurs à des formations 
		formateur3.addCompetences(formationSpring);
		formateur3.addCompetences(formationWeb);
		
		
		//Appel de la méthode create pour persiter les formateurs
		GenericDAO.create(formateur1);
		GenericDAO.create(formateur2);
		GenericDAO.create(formateur3);

	}

}
