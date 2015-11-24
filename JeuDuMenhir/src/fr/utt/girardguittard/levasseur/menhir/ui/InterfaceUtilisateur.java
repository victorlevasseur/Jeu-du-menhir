package fr.utt.girardguittard.levasseur.menhir.ui;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Partie;
import fr.utt.girardguittard.levasseur.menhir.Saison;

/**
 * Interface spécifiant les méthodes qui doivent permettre à une interface de fonctionner
 * avec le moteur du jeu du menhir.
 */
public interface InterfaceUtilisateur {
	
	/**
	 * Méthode appelée lors du lancement du jeu (juste avant de sélectionner le nombre de joueurs et le type de partie).
	 */
	public void notifierDebutJeu();
	
	/**
	 * Méthode appelée pour demander à l'utilisateur le type de partie qu'il souhaite.
	 * @return vrai pour une partie avancée, faux pour une partie simple
	 */
	public boolean demanderTypePartie();
	
	/**
	 * Méthode appelée pour demander à l'utilisateur le nombre de joueurs.
	 * @return le nombre de joueur demandés par l'utilisateur
	 */
	public int demanderNombreJoueurs();
	
	/**
	 * Méthode appelée juste après la récupération du type de partie et du nombre de joueurs.
	 * @param nombreJoueursOk vrai si le nombre de joueurs est correct, faux sinon. Si faux, l'interface sera 
	 * amenée à redemander le nombre de joueurs à l'utilisateur et afficher un message d'erreur ou prochain appel
	 * de la méthode demanderNombreJoueurs().
	 */
	public void notifierValidationParametres(boolean nombreJoueursOk);
	
	/**
	 * Méthode appelée au lancement de la partie.
	 * @param partie la partie lancée
	 */
	public void notifierDebutPartie(Partie partie);
	
	/**
	 * Méthode appelée au lancement d'une manche.
	 * @param numeroManche le numéro de la manche (de 0 à 3)
	 * @param manche la manche qui est lancée
	 */
	public void notifierDebutManche(int numeroManche, Manche manche);
	
	/**
	 * Méthode appelée au lancement d'une saison.
	 * @param saison la saison qui débute
	 */
	public void notifierDebutSaison(Saison saison);
	
	/**
	 * Méthode appelée au lancement du tour d'un joueur.
	 * @param numeroJoueur le numéro du joueur qui débute son tour
	 */
	public void notifierDebutTour(int numeroJoueur);
}
