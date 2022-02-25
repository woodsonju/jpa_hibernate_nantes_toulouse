package fr.dawan.yourbank.dao;

import java.util.List;

import fr.dawan.yourbank.entities.Client;

/*
 * Fontionnalit�s : 
 * 	- Ajouter un client 
 * 	- Mettre � jour un client
 *  - Supprimer un client 
 *  - R�cuperer la liste des clients 
 *  - Consulter les clients dont le nom contient un mot cl�
 *  - Consulter un client (probablement avec la liste de ses comptes)
 */
public interface IClientRepository {
	
	public void saveClient(Client client);
	public void updateClient(Client client);
	public void deleteClient(long code);
	public List<Client> getAllClients();
	public List<Client> findClientsByKey(String name);
	public Client getClient(long code);
	
}
