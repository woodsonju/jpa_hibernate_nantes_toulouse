package fr.dawan.yourbank.exception_;

public class VirementMemeCompteException extends Exception {
	
	String virementMemeCompteImpossible;

	public VirementMemeCompteException() {
		super();
	}

	public VirementMemeCompteException(String virementMemeCompteImpossible) {
		this.virementMemeCompteImpossible = virementMemeCompteImpossible;
		System.out.println(virementMemeCompteImpossible);

	}
	
	

}
