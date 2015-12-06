package fr.utt.girardguittard.levasseur.menhir;

import java.util.ArrayList;
import java.util.Random;

import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.cartes.CartesFabrique;
import fr.utt.girardguittard.levasseur.menhir.cartes.DeckCartes;
import fr.utt.girardguittard.levasseur.menhir.joueurs.Joueur;
import fr.utt.girardguittard.levasseur.menhir.joueurs.JoueurPhysique;
import fr.utt.girardguittard.levasseur.menhir.joueurs.JoueurVirtuel;
import fr.utt.girardguittard.levasseur.menhir.ui.InterfaceManager;
import fr.utt.girardguittard.levasseur.menhir.util.Console;

public class Partie {
	
	final private boolean partieAvancee;

	private ArrayList<Joueur> joueurs;
	
	private DeckCartes<CarteIngredient> deckCartesIngredient;
	
	private DeckCartes<CarteAllies> deckCartesAllies;
	
	public Partie(int nombreJoueurs, boolean partieAvancee) {
		this.partieAvancee = partieAvancee;
		this.joueurs = new ArrayList<Joueur>();
		
		//Création des joueurs
		this.joueurs.add(new JoueurPhysique());
		while(nombreJoueurs - 1 > 0) {
			this.joueurs.add(new JoueurVirtuel());
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
		//On notifie l'interface utilisateur que la partie est lancée
		InterfaceManager.get().notifierDebutPartie(this);
		
		//On choisit aléatoirement le premier joueur qui va débuter la première manche
		int premierJoueur = (int)(Math.random() * (float)this.joueurs.size());
		
		//On effectue le nombre de manches souhaités (1 si partie simple, le nombre de manches sinon)
		//La condition ternaire est plutôt utile ici...
		for(int i = 0; i < (this.partieAvancee ? this.getNombreJoueurs() : 1); i++) {
			
			//Création de la manche
			Manche manche = new Manche(this.partieAvancee, 
					this.joueurs, 
					this.deckCartesIngredient, 
					this.deckCartesAllies,
					(premierJoueur + i) % this.joueurs.size());
			
			InterfaceManager.get().notifierDebutManche(i, manche);
			
			//On joue la manche
			manche.jouer();
			
			InterfaceManager.get().notifierFinManche();
		}
		
		InterfaceManager.get().notifierFinPartie();
	}
	
	public boolean isPartieAvancee() {
		return this.partieAvancee;
	}
	
	public int getNombreJoueurs() {
		return this.joueurs.size();
	}
}
