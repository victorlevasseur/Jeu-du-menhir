package fr.utt.girardguittard.levasseur.menhir;

/**
 * Enumération permettant de décrire l'état d'une manche.
 */
public enum EtatManche {
	/**
	 * La manche a été créé.
	 */
	DEBUT_MANCHE,
	
	/**
	 * La manche attend que les joueurs choisissent de prendre ou non une carte alliés.
	 */
	EN_ATTENTE_CHOIX_CARTE_ALLIES,
	
	/**
	 * La partie est prête à démarrer.
	 */
	PRET_A_DEMARRER,
	
	/**
	 * Une saison a débuté.
	 */
	DEBUT_SAISON,
	
	/**
	 * Le tour d'un joueur a débuté.
	 */
	DEBUT_TOUR_JOUEUR,
	
	/**
	 * Le tour d'un joueur vient de se terminer.
	 */
	FIN_TOUR_JOUEUR,
	
	/**
	 * La saison actuelle vient de se terminer.
	 */
	FIN_SAISON,
	
	/**
	 * La manche vient de se finir.
	 */
	FIN_MANCHE
}
