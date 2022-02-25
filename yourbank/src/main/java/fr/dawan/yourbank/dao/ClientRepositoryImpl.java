package fr.dawan.yourbank.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.dawan.yourbank.entities.Client;

public class ClientRepositoryImpl implements IClientRepository{

	@Override
	public void saveClient(Client client) {
		if(client.getCode() == 0) {
			EntityManager em = createEntityManager();
			EntityTransaction transaction = em.getTransaction();
			
			try {
				transaction.begin();
				
				em.persist(client);
				
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
	public void updateClient(Client client) {
		if(client.getCode() > 0) {
			EntityManager em = createEntityManager();
			EntityTransaction transaction = em.getTransaction();
			
			try {
				transaction.begin();
				em.merge(client);
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
	public void deleteClient(long code) {
		EntityManager em = createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			Client client = em.find(Client.class, code);
			em.remove(client);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}
		
	}

	@Override
	public List<Client> getAllClients() {
		
		return null;
	}

	@Override
	public List<Client> findClientsByKey(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client getClient(long code) {
		EntityManager em = createEntityManager();
		Client clientBDD = null;
		
		try {
			//On charge le client depuis la Base de données selon son code
			clientBDD = em.find(Client.class, code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		em.close();
		return clientBDD;
	}
	
	public static EntityManager createEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("yourbank");
		EntityManager entityManager = factory.createEntityManager();
		return entityManager;
	}

}
