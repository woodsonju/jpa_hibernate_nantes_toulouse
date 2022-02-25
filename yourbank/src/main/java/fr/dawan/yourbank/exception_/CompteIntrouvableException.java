package fr.dawan.yourbank.exception_;


/*
 * Pour créer ses prorpes exceptions : 
 *  1 - Hériter de la classe Exception 
 *  2- Appeler le constructeur de la classe Exception
 */
public class CompteIntrouvableException extends Exception {
	
	String compteIntrouvable;

	public CompteIntrouvableException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteIntrouvableException(String compteIntrouvable) {
		this.compteIntrouvable = compteIntrouvable;
		System.out.println(this.compteIntrouvable);
	}

	public String getCompteIntrouvable() {
		return compteIntrouvable;
	}
	
	
	
}
