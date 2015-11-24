package fr.utt.girardguittard.levasseur.menhir.ui;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Partie;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.util.Console;

/**
 * Classe permettant de faire communiquer la console avec le moteur de jeu du menhir.
 * Note : les méthodes ne sont pas réexpliquer, se référer à la documentation de InterfaceUtilisateur
 * pour avoir plus de détails sur elles.
 */
public class InterfaceConsole implements InterfaceUtilisateur {
	
	private Partie partieEnCours;
	private Manche mancheEnCours;
	
	public InterfaceConsole() {
		this.partieEnCours = null;
		this.mancheEnCours = null;
	}

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
		
		return (resultat.equals("A"));
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

	public void notifierDebutPartie(Partie partie) {
		this.partieEnCours = partie;
		System.out.println("Début de la partie " + (partie.isPartieAvancee() ? "avancée" : "simple") + " avec " + partie.getNombreJoueurs() + " joueurs.");
	}

	public void notifierDebutManche(int numeroManche, Manche manche) {
		this.mancheEnCours = manche;
		System.out.println("Manche #" + (numeroManche+1) + " :");
		System.out.println(" - 4 cartes ingrédients ont été distribuées à chaque joueurs.");
		System.out.println(" - Le joueur #" + (manche.getPremierJoueur()+1) + " commence à jouer en premier.");
	}

	public void notifierDebutSaison(Saison saison) {
		System.out.println("C'est la saison " + saison.name());
	}

	public void notifierDebutTour(int numeroJoueur) {
		if(numeroJoueur == 0) {
			System.out.println("C'est à votre tour : ");
		} else {
			System.out.println("Au tour du joueur #" + (numeroJoueur+1) + " : ");
		}
	}

}
