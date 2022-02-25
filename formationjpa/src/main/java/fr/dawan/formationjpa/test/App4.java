package fr.dawan.formationjpa.test;

import java.time.LocalDate;
import java.time.Month;

import fr.dawan.formationjpa.dao.GenericDAO;
import fr.dawan.formationjpa.entities.Formation;
import fr.dawan.formationjpa.entities.SessionFormation;

public class App4 {

	public static void main(String[] args) {
		
		Formation formationJPA = new Formation();
		formationJPA.setCode("FOR-DEV001");
		formationJPA.setDuree(5);
		formationJPA.setNom("Java - JPA / Hibernate");
		formationJPA.setPrix(1999.99);
		
		SessionFormation sessionMars = new SessionFormation();
		sessionMars.setDate(LocalDate.of(2022, Month.MARCH, 7));
		sessionMars.setNbPlaceMaxi(15);
		sessionMars.setNbPlace(12);
		sessionMars.setFormation(formationJPA);
		
		SessionFormation sessionsSeptembre = new SessionFormation();
		sessionsSeptembre.setDate(LocalDate.of(2022, Month.SEPTEMBER, 5));
		sessionsSeptembre.setNbPlaceMaxi(15);
		sessionsSeptembre.setNbPlace(12);
		sessionsSeptembre.setFormation(formationJPA);
		
		//******************************************
		//PREMIERE TENTATIVE
		//******************************************
		
	//	GenericDAO.create(formationJPA);
		GenericDAO.create(sessionMars);
		GenericDAO.create(sessionsSeptembre);
		
		//Le nombre de session à partir de l'objet formation en mémoire 
		System.out.println("Nombre de sessions dans la formation JPA : " + formationJPA.getSessions().size());
		
		System.out.println("id de la formation " + formationJPA.getId());
		
		//récuperation de la formation correspondante en base de données 
		Formation formationBDD = GenericDAO.findById(Formation.class, formationJPA.getId());
		
		//Le nombre de session à partir de l'objet formation provenant de la base de données 
		System.out.println("nombre de sessions dans la formation JPA : " + formationBDD.getSessions().size());
	}

}
