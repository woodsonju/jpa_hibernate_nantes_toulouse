package fr.dawan.yourbank.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.dawan.yourbank.entities.Operation;

public class OperationRepositoryImpl implements IOperationRepository{

	@Override
	public void saveOperation(Operation operation) {

		if(operation.getNumOperation() == 0) {
			EntityManager em = createEntityManager();
			EntityTransaction transaction = em.getTransaction();

			try {
				transaction.begin();

				em.persist(operation);

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
	public void updateOperation(Operation operation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteOperation(long numOperation) {
		// TODO Auto-generated method stub

	}

	@Override
	public Operation getOperation(long numOperation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Operation> getAllOperations() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static EntityManager createEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("yourbank");
		EntityManager entityManager = factory.createEntityManager();
		return entityManager;
	}

}
