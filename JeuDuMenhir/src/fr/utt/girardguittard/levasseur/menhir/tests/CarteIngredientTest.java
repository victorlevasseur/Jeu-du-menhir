package fr.utt.girardguittard.levasseur.menhir.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.Action;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.cartes.CartesFabrique;
import fr.utt.girardguittard.levasseur.menhir.cartes.DeckCartes;
import fr.utt.girardguittard.levasseur.menhir.joueurs.Joueur;
import fr.utt.girardguittard.levasseur.menhir.joueurs.JoueurPhysique;
import fr.utt.girardguittard.levasseur.menhir.joueurs.JoueurVirtuel;

public class CarteIngredientTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Test
	public void agirTest() {
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		joueurs.add(new JoueurPhysique(0));
		joueurs.add(new JoueurVirtuel(1));
		
		DeckCartes<CarteIngredient> deckIngredient = new DeckCartes<CarteIngredient>();
		deckIngredient.ajouterCartes(CartesFabrique.genererCartesIngredients());
		deckIngredient.remettreCartesEtMelanger();
		
		Manche manche = new Manche(false, joueurs, deckIngredient, null, 0);
		manche.getJoueur(1).getMain().ajouterGraines(2);
		
		HashMap<Saison, HashMap<Action, Integer>> forces = new HashMap<Saison, HashMap<Action, Integer>>();
		HashMap<Action, Integer> forcesEte = new HashMap<Action, Integer>();
		forcesEte.put(Action.ENGRAIS, 2);
		forcesEte.put(Action.GEANT, 3);
		forcesEte.put(Action.FARFADET, 1);
		forces.put(Saison.ETE, forcesEte);
		
		CarteIngredient carteTest = new CarteIngredient("Carte test", forces);
		
		carteTest.agir(manche, manche.getJoueur(0).getMain(), -1, Saison.ETE, Action.GEANT);
		assertEquals(manche.getJoueur(0).getMain().getNombreGraine(), 3);
		
		carteTest.agir(manche, manche.getJoueur(0).getMain(), 1, Saison.ETE, Action.FARFADET);
		assertEquals(manche.getJoueur(0).getMain().getNombreGraine(), 4);
		assertEquals(manche.getJoueur(1).getMain().getNombreGraine(), 1);
		
		carteTest.agir(manche, manche.getJoueur(0).getMain(), -1, Saison.ETE, Action.ENGRAIS);
		assertEquals(manche.getJoueur(0).getMain().getNombreGraine(), 2);
		assertEquals(manche.getJoueur(0).getMain().getNombreMenhir(), 2);
	}

}
