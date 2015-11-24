package fr.utt.girardguittard.levasseur.menhir.ui;

import fr.utt.girardguittard.levasseur.menhir.util.Console;

/**
 * Classe permettant de faire communiquer la console avec le moteur de jeu du menhir.
 * Note : les méthodes ne sont pas réexpliquer, se référer à la documentation de InterfaceUtilisateur
 * pour avoir plus de détails sur elles.
 */
public class InterfaceConsole implements InterfaceUtilisateur {

	public void notifierDebutJeu() {
		System.out.println("Jeu du Menhir");
		System.out.println("Bienvenu(e) !");
	}

	public boolean demanderTypePartie() {
		String resultat;
		do {
			System.out.println("Choisissez le type de partie : S pour partie simple ou A pour partie avancée");
			resultat = Console.getInstance().readln().toUpperCase();
		} while(resultat.length() != 1 && resultat.charAt(0) != 'S' && resultat.charAt(0) != 'A');
		
		return (resultat == "A");
	}

	public int demanderNombreJoueurs() {
		System.out.println("Saisissez le nombre de joueurs (entre 2 et 6) : ");
		String nombreJoueursStr = Console.getInstance().readln();
		
		try {
			int nombreJoueurs = Integer.parseInt(nombreJoueursStr);
			return nombreJoueurs;
		} 
		catch(NumberFormatException e) {
			System.out.println("Ceci n'est pas un nombre valide !");
			return demanderNombreJoueurs();
		}
	}

	public void notifierValidationParametres(boolean nombreJoueursOk) {
		if(!nombreJoueursOk) {
			System.out.println("Le nombre de joueur est invalide : entre 2 et 6 joueurs seulement");
		}
	}

}
