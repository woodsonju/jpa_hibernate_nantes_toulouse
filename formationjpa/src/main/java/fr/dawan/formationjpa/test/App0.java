package fr.dawan.formationjpa.test;

import javax.persistence.Persistence;

/*
 * Le premier test qu'on va faire est de verifier que la base de données 
 * est générée avec les tables
 * Le premier objet qu'on crée avec JPA est EntityManagerFactory. 
 * Pour créer cet objet on utilise la classe  Persistence (objet fourni par JPA) et la
 * méthode createEntityManagerFactory en lui donnant l'unité de persistance
 * 
 * La méthode createEntityManagerFactory : 
 * 		o Crée un objet EntityManagerFactory
 * 		o Lit le fichier de persistence "persistence.xml"
 * 		o Qui va entrainer la création du data-source qui etablira une connexion 
 * 		  à la base de données
 */
public class App0 {

	public static void main(String[] args) {
		
		//Crée un objet de type EntityManagerFactory
		Persistence.createEntityManagerFactory("formationjpa");
	
	}

}
