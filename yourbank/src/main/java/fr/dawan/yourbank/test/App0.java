package fr.dawan.yourbank.test;

import javax.persistence.Persistence;

public class App0 {

	public static void main(String[] args) {
		
		Persistence.createEntityManagerFactory("yourbank");
		
	}

}
