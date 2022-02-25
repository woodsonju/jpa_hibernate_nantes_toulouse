package fr.dawan.formationjpa.test;

import java.util.List;
import java.util.Scanner;

import fr.dawan.formationjpa.dao.FormateurDao;
import fr.dawan.formationjpa.entities.Formateur;

public class App10 {

	public static void main(String[] args) {
		
		System.out.println("Veuillez saisir un nom, ou une partie de nom ");
		Scanner sc = new Scanner(System.in);
		String saisie = sc.nextLine();
		
		//testFindByNameNativeSQL(saisie);
		//testFindByNameJPQLPositionalParameter(saisie);
		testFindByNameJPQLNamedParameter(saisie);
		
	}


	private static void testFindByNameNativeSQL(String saisie) {
		List<Formateur> formateurs = FormateurDao.findByNameNativeSQL(saisie);
		
		System.out.println("Les FORMATEURS dont le nom contient \"" + saisie + "\".");
		
		for (Formateur formateur : formateurs) {
			System.out.println("t" + formateur);
		}
	}
	
	private static void testFindByNameJPQLPositionalParameter(String saisie) {
		List<Formateur> formateurs = FormateurDao.findByNameJPQLPositionalParameter(saisie);
		
		System.out.println("Les FORMATEURS dont le nom contient \"" + saisie + "\".");
		
		for (Formateur formateur : formateurs) {
			System.out.println("t" + formateur);
		}
	}

	private static void testFindByNameJPQLNamedParameter(String saisie) {
		List<Formateur> formateurs = FormateurDao.findByNameJPQLNamedParameter(saisie);
		
		System.out.println("Les FORMATEURS dont le nom contient \"" + saisie + "\".");
		
		for (Formateur formateur : formateurs) {
			System.out.println("t" + formateur);
		}
	}
}
