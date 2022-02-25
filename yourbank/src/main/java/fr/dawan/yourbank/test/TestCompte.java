package fr.dawan.yourbank.test;

import fr.dawan.yourbank.dao.CompteRepository;
import fr.dawan.yourbank.dao.ICompteRepository;
import fr.dawan.yourbank.entities.Compte;
import fr.dawan.yourbank.exception_.CompteIntrouvableException;

public class TestCompte {

	static ICompteRepository compteRepository = new CompteRepository();
	
	public static void main(String[] args) {
		try {
			getCompteTest();
		} catch (CompteIntrouvableException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	/*
	 * throw : permet de lever une exception manuellement en instanciant un objet de type CompteIntrouvableException
	 * throws : Ce mot clé permet de signaler à la JVM qu'un morceau de code, une méthode, une class...est potentiellement 
	 * 			dangereux, et qu'il FAUT utilise un try{...}catch{...}
	 */
	private static void getCompteTest() throws CompteIntrouvableException {
		Compte cb_bdd = compteRepository.getCompte("aaaa88aaa");
		if(cb_bdd == null) {
			throw new CompteIntrouvableException("Compte introuvable");
		}
		System.out.println(cb_bdd);
	}

}
