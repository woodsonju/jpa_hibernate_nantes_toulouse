package fr.dawan.yourbank.dao;

import java.util.List;

import fr.dawan.yourbank.entities.Compte;

public interface ICompteRepository {
	
	public void saveCompte(Compte compte);
	public void updateCompte(Compte compte);
	public void deleteCompte(String numCompte);
	public List<Compte> getAllComptes();
	public Compte getCompte(String numCompte);
}
