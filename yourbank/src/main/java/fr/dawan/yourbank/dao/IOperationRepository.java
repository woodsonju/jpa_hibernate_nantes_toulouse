package fr.dawan.yourbank.dao;

import java.util.List;

import fr.dawan.yourbank.entities.Compte;
import fr.dawan.yourbank.entities.Operation;

public interface IOperationRepository {
	
	public void saveOperation(Operation operation);
	public void updateOperation(Operation operation);
	public void deleteOperation(long numOperation);
	public Operation getOperation(long numOperation);
	public List<Operation> getAllOperations();
	
}
