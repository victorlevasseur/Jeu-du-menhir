package fr.utt.girardguittard.levasseur.menhir;

import java.util.ArrayList;

import fr.utt.girardguittard.levasseur.menhir.joueurs.Joueur;
import fr.utt.girardguittard.levasseur.menhir.joueurs.JoueurPhysique;
import fr.utt.girardguittard.levasseur.menhir.joueurs.JoueurVirtuel;

public class Partie {
	
	private boolean partieAvancee;
	
	private ArrayList<Joueur> joueurs;
	
	public Partie(int nombreJoueurs, boolean partieAvancee) {
		this.partieAvancee = partieAvancee;
		
		this.joueurs.add(new JoueurPhysique());
		while(nombreJoueurs - 1 > 0) {
			this.joueurs.add(new JoueurVirtuel());
			nombreJoueurs--;
		}
	}
}
