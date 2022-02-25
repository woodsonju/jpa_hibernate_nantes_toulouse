package fr.dawan.yourbank.service;

import java.time.LocalDate;

import fr.dawan.yourbank.dao.CompteRepository;
import fr.dawan.yourbank.dao.ICompteRepository;
import fr.dawan.yourbank.dao.IOperationRepository;
import fr.dawan.yourbank.dao.OperationRepositoryImpl;
import fr.dawan.yourbank.entities.Compte;
import fr.dawan.yourbank.entities.CompteCourant;
import fr.dawan.yourbank.entities.Retrait;
import fr.dawan.yourbank.entities.Versement;
import fr.dawan.yourbank.exception_.CompteIntrouvableException;
import fr.dawan.yourbank.exception_.SoldeInsuffisantException;
import fr.dawan.yourbank.exception_.VirementMemeCompteException;

public class BankServiceImpl  implements IBankService{

	ICompteRepository compteRepository = new CompteRepository();
	IOperationRepository operationRepository = new OperationRepositoryImpl();
	
	@Override
	public Compte consulteCompte(String numCpte) throws CompteIntrouvableException {
		Compte cb = compteRepository.getCompte(numCpte);
		if(cb == null) {
			throw new CompteIntrouvableException("Compte introuvable");
		}
		return cb;
	}

	@Override
	public void verser(String numCpte, double montant) throws CompteIntrouvableException {
		Compte cb = consulteCompte(numCpte);
		Versement versement = new Versement(LocalDate.now(), montant, cb);
		operationRepository.saveOperation(versement);
		cb.setSolde(cb.getSolde() + montant);
		compteRepository.updateCompte(cb);
		
	}

	@Override
	public void retirer(String numCpte, double montant) throws CompteIntrouvableException, SoldeInsuffisantException {
		Compte cb = consulteCompte(numCpte);
		
		/*
		 * Avant le retrait, verifier si le solde est suffisant pour rétirer 
		 * Aussi, cela depent si c'est un compte courant (prend en consideration le découvert) ou 
		 * si c'est un epargne (le découvert est à égale à zero)
		 */
		double facilitesCaisse = 0;
		if(cb instanceof CompteCourant) {
			facilitesCaisse = ((CompteCourant) cb).getDecouvert(); //On recupère le découvert du compte
		}
		if(cb.getSolde()+facilitesCaisse < montant) {
			throw new SoldeInsuffisantException("Solde insuffisant");
		}
		Retrait retrait = new Retrait(LocalDate.now(), montant, cb);
		operationRepository.saveOperation(retrait);
		
		cb.setSolde(cb.getSolde() - montant);
		compteRepository.updateCompte(cb);
		
	}

	@Override
	public void virement(String numCpte1, String numCpte2, double montant) throws VirementMemeCompteException, CompteIntrouvableException, SoldeInsuffisantException {
		if(numCpte1.equals(numCpte2)) {
			throw new VirementMemeCompteException("Impossible d'effectuer un virement sur le même compte");
		}
		
		//On fait un retrait d'un montant d'un compte numCompte1 vers un autre compte numCompte2
		retirer(numCpte1, montant);
		verser(numCpte2, montant);
		
	}

}
