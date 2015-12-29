package fr.utt.girardguittard.levasseur.menhir.console;

import java.util.Observable;
import java.util.Observer;

import fr.utt.girardguittard.levasseur.menhir.ActionIllegaleException;
import fr.utt.girardguittard.levasseur.menhir.Partie;
import fr.utt.girardguittard.levasseur.menhir.util.Console;

public class JeuConsole implements Observer {

	public static void main(String[] args) {
		System.out.println("Jeu du Menhir");
		System.out.println("Bienvenu(e) sur la version console du jeu !");
		
		JeuConsole jeu = new JeuConsole(demanderNombreJoueurs(), demanderTypePartie());
		jeu.lancerPartie();
	}
	
	private Partie partie;
	
	public JeuConsole(int nombreJoueurs, boolean partieAvancee) {
		this.partie = new Partie(nombreJoueurs, partieAvancee);
		this.partie.addObserver(this);
	}
	
	void lancerPartie() {
		try {
			//Lancement de la première manche
			this.partie.demarrerManche();
		} catch (ActionIllegaleException e) { 
			e.printStackTrace();
		}
	}

	public void update(Observable arg0, Object arg1) {
		
	}
	
	private static boolean demanderTypePartie() {
		String resultat;
		do {
			System.out.println("Choisissez le type de partie : S pour partie simple ou A pour partie avancée");
			resultat = Console.getInstance().readln().toUpperCase();
		} while(resultat.length() != 1 && resultat.charAt(0) != 'S' && resultat.charAt(0) != 'A');
		
		return (resultat.equals("A"));
	}

	private static int demanderNombreJoueurs() {
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

}
