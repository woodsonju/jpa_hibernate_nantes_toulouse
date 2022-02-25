package fr.dawan.yourbank.exception_;

public class SoldeInsuffisantException extends Exception {
	
	String soldeInsuffisant;

	public SoldeInsuffisantException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SoldeInsuffisantException(String soldeInsuffisant) {
		this.soldeInsuffisant = soldeInsuffisant;
		System.out.println(this.soldeInsuffisant);
	} 
	
	
	
}
