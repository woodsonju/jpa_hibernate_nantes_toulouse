package fr.dawan.formationjpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.dawan.formationjpa.entities.Formateur;

public class FormateurDao {
	
	@SuppressWarnings("unchecked")
	public static List<Formateur> findByNameNativeSQL(String name) {
		List<Formateur> formateurs = null;
		EntityManager em = GenericDAO.createEntityManager();
		
		//SELECT * FROM trainer WHERE t_lastname like '%ie%'
		String sql = "SELECT * FROM trainer WHERE t_lastname like '%" + name + "%'";
		Query query = em.createNativeQuery(sql, Formateur.class);
		formateurs = query.getResultList();
		
		em.close();
		
		return formateurs;
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<Formateur> findByNameJPQLPositionalParameter(String name) {
		List<Formateur> formateurs = null;
		EntityManager em = GenericDAO.createEntityManager();
		
		String jpql = "SELECT f FROM Formateur f WHERE f.nom like ?1 ORDER BY f.nom, f.prenom";
		
		Query query = em.createQuery(jpql, Formateur.class);
		
		query.setParameter(1, "%" + name + "%");
		
		formateurs = query.getResultList();
		
		em.close();
		
		return formateurs;
	}	
	
	@SuppressWarnings("unchecked")
	public static List<Formateur> findByNameJPQLNamedParameter(String name) {
		List<Formateur> formateurs = null;
		EntityManager em = GenericDAO.createEntityManager();
		
		String jpql = "SELECT f FROM Formateur f WHERE f.nom like :leNom ORDER BY f.nom, f.prenom";
		
		Query query = em.createQuery(jpql, Formateur.class);
		
		query.setParameter("leNom", "%" + name + "%");
		
		formateurs = query.getResultList();
		
		em.close();
		
		return formateurs;
	}	
	
	/*
	 * Utilisation de l'api criteria
	 */
	public static List<Formateur> findByNameCriteria(String name) {
		List<Formateur> formateurs = null;
		EntityManager em = GenericDAO.createEntityManager();
		
		//Builder de requête 
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		//Initialisaiton de la requête 
		CriteriaQuery<Formateur> query = criteriaBuilder.createQuery(Formateur.class);
		
		//Creation du "FROM"
		Root<Formateur> entity =  query.from(Formateur.class);
		
		//réation du "WHERE", dans lequel on insère le "LIKE"
		query = query.where(criteriaBuilder.like(entity.get("nom"), "%" + name + "%"));
		
		//On recupère le resultat 
		formateurs = em.createQuery(query).getResultList();
		
		em.close();
		
		return formateurs; 
	}
	
}
