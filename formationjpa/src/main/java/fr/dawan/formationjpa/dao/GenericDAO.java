package fr.dawan.formationjpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.dawan.formationjpa.entities.DbObject;

public class GenericDAO {

	public static <T extends DbObject> void create (T entity) {
		if(entity.getId() == 0) {
			EntityManager entityManager =  createEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			try {
				//début de la transaction 
				transaction.begin();

				//On insère une entité 
				entityManager.persist(entity);

				//On commit tout ce qui a été fait dans la trasaction
				transaction.commit();
			} catch(Exception e) {
				//En cas d'erreur, on effectue un rollback
				transaction.rollback();
				e.printStackTrace();
			} finally {
				entityManager.close();
			}
		}
	}

	public static <T extends DbObject> T findById(Class<T> clazz, long id) {

		EntityManager entityManager = createEntityManager();

		T entity = null;

		try {

			//On charge la formation depuis la BDD , seleon son Id
			entity = entityManager.find(clazz, id);

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}

		return entity;
	}


	public static <T extends DbObject> void update(T entity) {
		if(entity.getId() > 0) {
			EntityManager entityManager = createEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();

			try {
				//Début de la transaction
				transaction.begin();

				//On met à jour l'entité 
				entityManager.merge(entity);

				//On commit ce qui a été fait dans la transaction 
				transaction.commit();
			} catch(Exception e) {
				transaction.rollback();
				e.printStackTrace();
			} finally {
				entityManager.close();
			}
		}
	}

	public static <T extends DbObject> void delete(Class<T> clazz,long id) {

		EntityManager entityManager = createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			transaction.begin();
			T entity = entityManager.find(clazz, id);
			entityManager.remove(entity);

			transaction.commit();

		} catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}


	public static <T extends DbObject> List<T> findAll(Class<T> clazz) {
		List<T> resultat = null;
		EntityManager em = createEntityManager();

		//SELECT * FROM tableEntity (SQL)
		//SELECT entity FROM Entity entity (JPQL : Java Persistence Query Language)

		//Création de la requête 
		String sql = "SELECT entity FROM " + clazz.getName() + " entity";
		TypedQuery<T> query = em.createQuery(sql, clazz);

		//Une requête de sélection est exécutée depuis javax.persistence.Query via la méthode
		//getResultList si plusieurs résultat sont possible (dans ce cas une list est retournée)
		resultat = query.getResultList();

		em.close();

		return resultat;
	}


	/**
	 * Permet de récuperer toutes les entrées pour une table données à partir d'une entrée, 
	 * pour un nombre de résultat donné
	 * @param <T>
	 * @param clazz : Le type que l'on souhaite récupérer
	 * @param begin  : L'index du premier résultat
	 * @param nbResult : nombre de sultat
	 * @return : Une liste d'entrée paginée
	 */
	public static <T extends DbObject> List<T> findAll(Class<T> clazz, int begin, int nbResult) {
		List<T> resulat = null;
		EntityManager em = createEntityManager();

		//Création de la requête 
		String sql = "SELECT entity FROM " + clazz.getName() + " entity";
		TypedQuery<T> query = em.createQuery(sql, clazz);
		
		//On paramètre... et execution de la requête et récuperation du résultat 
		resulat = query.setFirstResult(begin)  //On commence à ce numéro(begin) - au Nième résultat
					.setMaxResults(nbResult)   //On charge le nbResultat
					.getResultList();  //On recupère
		em.close();
		return resulat;
	}


	public static EntityManager createEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("formationjpa");
		EntityManager entityManager = factory.createEntityManager();
		return entityManager;
	}


}
