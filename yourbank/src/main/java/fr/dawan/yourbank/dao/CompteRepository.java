package fr.dawan.yourbank.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.dawan.yourbank.entities.Compte;

public class CompteRepository implements ICompteRepository{

	@Override
	public void saveCompte(Compte compte) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCompte(Compte compte) {
		if(compte.getNumCompte() != null) {
			EntityManager em = createEntityManager();
			EntityTransaction transaction = em.getTransaction();
			
			try {
				transaction.begin();
				em.merge(compte);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
			}finally {
				em.close();
			}
		}

	}

	@Override
	public void deleteCompte(String numCompte) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Compte> getAllComptes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compte getCompte(String numCompte) {
		EntityManager em = createEntityManager();
		Compte compte = null; 
		
		try {
			compte = em.find(Compte.class, numCompte);
		}catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return compte;
	}
	
	public static EntityManager createEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("yourbank");
		EntityManager entityManager = factory.createEntityManager();
		return entityManager;
	}

}
