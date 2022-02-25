package fr.dawan.formationjpa.test;

import javax.persistence.Persistence;

/*
 * Le premier test qu'on va faire est de verifier que la base de donn�es 
 * est g�n�r�e avec les tables
 * Le premier objet qu'on cr�e avec JPA est EntityManagerFactory. 
 * Pour cr�er cet objet on utilise la classe  Persistence (objet fourni par JPA) et la
 * m�thode createEntityManagerFactory en lui donnant l'unit� de persistance
 * 
 * La m�thode createEntityManagerFactory : 
 * 		o Cr�e un objet EntityManagerFactory
 * 		o Lit le fichier de persistence "persistence.xml"
 * 		o Qui va entrainer la cr�ation du data-source qui etablira une connexion 
 * 		  � la base de donn�es
 */
public class App0 {

	public static void main(String[] args) {
		
		//Cr�e un objet de type EntityManagerFactory
		Persistence.createEntityManagerFactory("formationjpa");
	
	}

}
