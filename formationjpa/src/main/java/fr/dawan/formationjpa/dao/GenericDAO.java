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
				//d�but de la transaction 
				transaction.begin();

				//On ins�re une entit� 
				entityManager.persist(entity);

				//On commit tout ce qui a �t� fait dans la trasaction
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
				//D�but de la transaction
				transaction.begin();

				//On met � jour l'entit� 
				entityManager.merge(entity);

				//On commit ce qui a �t� fait dans la transaction 
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

		//Cr�ation de la requ�te 
		String sql = "SELECT entity FROM " + clazz.getName() + " entity";
		TypedQuery<T> query = em.createQuery(sql, clazz);

		//Une requ�te de s�lection est ex�cut�e depuis javax.persistence.Query via la m�thode
		//getResultList si plusieurs r�sultat sont possible (dans ce cas une list est retourn�e)
		resultat = query.getResultList();

		em.close();

		return resultat;
	}


	/**
	 * Permet de r�cuperer toutes les entr�es pour une table donn�es � partir d'une entr�e, 
	 * pour un nombre de r�sultat donn�
	 * @param <T>
	 * @param clazz : Le type que l'on souhaite r�cup�rer
	 * @param begin  : L'index du premier r�sultat
	 * @param nbResult : nombre de sultat
	 * @return : Une liste d'entr�e pagin�e
	 */
	public static <T extends DbObject> List<T> findAll(Class<T> clazz, int begin, int nbResult) {
		List<T> resulat = null;
		EntityManager em = createEntityManager();

		//Cr�ation de la requ�te 
		String sql = "SELECT entity FROM " + clazz.getName() + " entity";
		TypedQuery<T> query = em.createQuery(sql, clazz);
		
		//On param�tre... et execution de la requ�te et r�cuperation du r�sultat 
		resulat = query.setFirstResult(begin)  //On commence � ce num�ro(begin) - au Ni�me r�sultat
					.setMaxResults(nbResult)   //On charge le nbResultat
					.getResultList();  //On recup�re
		em.close();
		return resulat;
	}


	public static EntityManager createEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("formationjpa");
		EntityManager entityManager = factory.createEntityManager();
		return entityManager;
	}


}
