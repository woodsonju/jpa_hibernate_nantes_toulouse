package fr.dawan.yourbank.service;

import fr.dawan.yourbank.entities.Compte;
import fr.dawan.yourbank.exception_.CompteIntrouvableException;
import fr.dawan.yourbank.exception_.SoldeInsuffisantException;
import fr.dawan.yourbank.exception_.VirementMemeCompteException;

public interface IBankService {
	
	public Compte consulteCompte(String numCpte) throws CompteIntrouvableException;
	public void verser(String numCpte, double montant) throws CompteIntrouvableException;
	public void retirer(String numCpte, double montant) throws CompteIntrouvableException, SoldeInsuffisantException;
	public void virement(String numCpte1, String numCpte2, double montant) throws VirementMemeCompteException, CompteIntrouvableException, SoldeInsuffisantException;
	
}
