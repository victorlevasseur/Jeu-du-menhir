package fr.utt.girardguittard.levasseur.menhir;

import java.util.ArrayList;

import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.cartes.CartesFabrique;
import fr.utt.girardguittard.levasseur.menhir.cartes.DeckCartes;
import fr.utt.girardguittard.levasseur.menhir.joueurs.Joueur;
import fr.utt.girardguittard.levasseur.menhir.joueurs.JoueurPhysique;
import fr.utt.girardguittard.levasseur.menhir.joueurs.JoueurVirtuel;

public class Partie {
	
	private boolean partieAvancee;
	
	private ArrayList<Joueur> joueurs;
	
	private DeckCartes<CarteIngredient> deckCartesIngredient;
	
	private DeckCartes<CarteAllies> deckCartesAllies;
	
	public Partie(int nombreJoueurs, boolean partieAvancee) {
		this.partieAvancee = partieAvancee;
		
		//Création des joueurs
		this.joueurs.add(new JoueurPhysique());
		while(nombreJoueurs - 1 > 0) {
			this.joueurs.add(new JoueurVirtuel());
			//TODO : Affecter des stratégies
			nombreJoueurs--;
		}
		
		//Création des decks
		this.deckCartesIngredient = new DeckCartes<CarteIngredient>();
		this.deckCartesIngredient.ajouterCartes(CartesFabrique.genererCartesIngredients());
		
		//Création du deck de cartes alliés si la partie est avancée
		if(this.partieAvancee) {
			this.deckCartesAllies = new DeckCartes<CarteAllies>();
			this.deckCartesAllies.ajouterCartes(CartesFabrique.genererCartesAllies());
		} else {
			this.deckCartesAllies = null;
		}
	}
	
	void jouer() {
		
	}
}
