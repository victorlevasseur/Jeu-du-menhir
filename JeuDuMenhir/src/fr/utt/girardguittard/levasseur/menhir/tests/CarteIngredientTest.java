/*
	JeuDuMenhir is a board game adapted into a computer game.
	Copyright (C) 2015-2016  
	Antoine Girard Guittard (antoine.girard_guittard@utt.fr), Victor Levasseur (victorlevasseur52@gmail.com)
	
	This program is free software; you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation; either version 2 of the License, or
	(at your option) any later version.
	
	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.
	
	You should have received a copy of the GNU General Public License along
	with this program; if not, write to the Free Software Foundation, Inc.,
	51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

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
