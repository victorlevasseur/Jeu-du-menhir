package fr.utt.girardguittard.levasseur.menhir;

import fr.utt.girardguittard.levasseur.menhir.ui.InterfaceConsole;
import fr.utt.girardguittard.levasseur.menhir.ui.InterfaceManager;

public class Jeu {

	public static void main(String[] args) {
		
		//On définit l'interface utilisateur utilisée
		InterfaceManager.setInterfaceUtilisateur(new InterfaceConsole()); //Temporaire, à changer pour la dernière version du jeu
		
		//On notifie l'interface que c'est le début du jeu
		InterfaceManager.get().notifierDebutJeu();
		
		//Saisie des paramètres de la partie
		boolean partieAvancee = InterfaceManager.get().demanderTypePartie();
		int nombreJoueurs;
		do {
			nombreJoueurs = InterfaceManager.get().demanderNombreJoueurs();
			InterfaceManager.get().notifierValidationParametres(nombreJoueurs >= 2 && nombreJoueurs <= 6);
		} while(nombreJoueurs < 2 || nombreJoueurs > 6);
		
		Partie partie = new Partie(nombreJoueurs, partieAvancee);
		
		partie.jouer();
	}

}
