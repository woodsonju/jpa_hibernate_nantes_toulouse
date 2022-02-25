package fr.dawan.yourbank.dao;

import java.util.List;

import fr.dawan.yourbank.entities.Client;

/*
 * Fontionnalités : 
 * 	- Ajouter un client 
 * 	- Mettre à jour un client
 *  - Supprimer un client 
 *  - Récuperer la liste des clients 
 *  - Consulter les clients dont le nom contient un mot clé
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
