package fr.dawan.yourbank.test;

import java.time.LocalDate;

import fr.dawan.yourbank.dao.ClientRepositoryImpl;
import fr.dawan.yourbank.dao.IClientRepository;
import fr.dawan.yourbank.entities.Client;
import fr.dawan.yourbank.entities.Compte;
import fr.dawan.yourbank.entities.CompteCourant;

public class TestClient {

	static IClientRepository repository = new ClientRepositoryImpl();
	
	public static void main(String[] args) {
		
		//testSaveClient();
		//Client client = testGetClientMethod();
	//	testUpdateClientMethod(client);
		//testDeleteMethode();
		
		testSaveClientWithComptes();
		
	}

	private static void testSaveClient() {
		Client c1 = new Client("Louis", "louis@gmail.com");
		Client c2 = new Client("Matheo", "matheo@gmail.com");
		Client c3 = new Client("Livai", "livai@gmail.com");
		
		repository.saveClient(c1);
		repository.saveClient(c2);
		repository.saveClient(c3);
	}
	
	private static Client testGetClientMethod() {
		Client clientBDD = repository.getClient(3);
		System.out.println(clientBDD);
		return clientBDD;
	}
	
	/**
	 * 
	 * @param client  : client récupéré de la base de donnée
	 */
	private static void testUpdateClientMethod(Client client) {
		
		client.setNom("toto");
		client.setEmail("toto@gmail.com");
		
		repository.updateClient(client);
		
		//Après modification 
		Client clientUpdateBDD = repository.getClient(client.getCode());
		
		System.out.println(client.getNom().equals(clientUpdateBDD.getNom()));
	}
	
	private static void testDeleteMethode() {
		repository.deleteClient(1);
		Client clientBDD = repository.getClient(1);
		System.out.println(clientBDD == null);
	}
	
	public static void testSaveClientWithComptes() {
		Client c1 = new Client("Tata", "tata@gmail.com");
		Client c2 = new Client("David", "david@gmail.com");
		Compte cb1 = new CompteCourant("aaaa88aaa", LocalDate.now(), 5000, c1, 400);
		Compte cb2 = new CompteCourant("33bbb333", LocalDate.now(), 15000, c2, 1000);
		Compte cb3 = new CompteCourant("tttt77kkk", LocalDate.now(), 30000, c1, 1000);
		
		//On va sauvegarder le client et laisser les cascades créer les comptes
		c1.addComptes(cb1);
		c2.addComptes(cb2);
		c1.addComptes(cb3);
		
		repository.saveClient(c1);

	}

}
