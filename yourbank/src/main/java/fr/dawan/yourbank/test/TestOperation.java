package fr.dawan.yourbank.test;

import fr.dawan.yourbank.entities.Compte;
import fr.dawan.yourbank.exception_.CompteIntrouvableException;
import fr.dawan.yourbank.exception_.SoldeInsuffisantException;
import fr.dawan.yourbank.exception_.VirementMemeCompteException;
import fr.dawan.yourbank.service.BankServiceImpl;
import fr.dawan.yourbank.service.IBankService;

public class TestOperation {

	static IBankService service = new BankServiceImpl();
	
	public static void main(String[] args) {
		
		double montant = 1000;
		String numCompte1 = "aaaa88aaa";
		String numCompte2 = "tttt77kkk";
		
		try {
//			testConsulterCompte(numCompte2);
//			testVerser(numCompte2, montant);
		//	testRetirer(numCompte2, montant);
			testVirement(numCompte1, numCompte2, montant);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public static void  testConsulterCompte(String numCpte) throws CompteIntrouvableException  {
		Compte compte = service.consulteCompte(numCpte);
		System.out.println(compte);
		System.out.println(compte != null);
	}
	
	public static void testVerser(String numCpte, double montant) throws CompteIntrouvableException {
		//Avant le versement 
		Compte compteBDD_Before = service.consulteCompte(numCpte);
		
		//Versement
		service.verser(numCpte, montant);
		
		//Apres versement 
		Compte compteBDD_After = service.consulteCompte(numCpte);
		
		System.out.println((compteBDD_Before.getSolde() + montant) == compteBDD_After.getSolde());
	}
	
	public static void testRetirer(String numCpte, double montant) throws CompteIntrouvableException, SoldeInsuffisantException  {
		//Avant le versement
		Compte compteBDD_Before = service.consulteCompte(numCpte);

		//Retrait
		service.retirer(numCpte, montant);

		//Après versement
		Compte compteBDD_After = service.consulteCompte(numCpte);

		System.out.println((compteBDD_Before.getSolde() - montant)  == compteBDD_After.getSolde());
	}

	public static void testVirement(String numCompte1, String numCompte2, double montant) throws CompteIntrouvableException, SoldeInsuffisantException, VirementMemeCompteException {
		//Avant le versement
		Compte compteBDD_Before = service.consulteCompte(numCompte1);

		//Retrait
		service.virement(numCompte1, numCompte2, montant);

		//Après versement
		Compte compteBDD_After = service.consulteCompte(numCompte1);

		System.out.println((compteBDD_Before.getSolde() - montant)  == compteBDD_After.getSolde());
	}

}
