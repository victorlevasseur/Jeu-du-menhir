package fr.utt.girardguittard.levasseur.menhir;

/**
 * Enumération permettant de représenter l'état de la partie
 */
public enum EtatPartie {
	/**
	 * La partie a été crée et la première manche est prête à débuter.
	 */
	LANCEE,
	
	/**
	 * Une manche est en cours.
	 */
	MANCHE_EN_COURS,
	
	/**
	 * La manche actuelle est finie. La suivante peut débuter. 
	 */
	MANCHE_FINIE,
	
	/**
	 * La partie est finie.
	 */
	FINIE
}
