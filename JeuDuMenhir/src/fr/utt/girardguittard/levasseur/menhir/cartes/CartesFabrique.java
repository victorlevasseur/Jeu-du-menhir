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

package fr.utt.girardguittard.levasseur.menhir.cartes;

import java.util.ArrayList;
import java.util.List;

public class CartesFabrique {
	
	/**
	 * Retourne une liste de cartes ingrédients utilisées par le jeu.
	 * @return une liste de cartes ingrédients
	 */
	public static List<CarteIngredient> genererCartesIngredients() {
		ArrayList<CarteIngredient> cartes = new ArrayList<CarteIngredient>();
		
		//Rayon de lune
		cartes.add(CarteIngredient.CreerAvecTableau("Rayon de lune", new int[]
				{1, 1, 1, 1,
				 2, 0, 1, 1,
				 2, 0, 2, 0}
		));
		cartes.add(CarteIngredient.CreerAvecTableau("Rayon de lune", new int[]
				{2, 0, 1, 1,
				 1, 3, 0, 0,
				 0, 1, 2, 1}
		));
		cartes.add(CarteIngredient.CreerAvecTableau("Rayon de lune", new int[]
				{0, 0, 4, 0,
				 0, 2, 2, 0,
				 0, 0, 1, 3}
		));
		
		//Chant de sirène
		cartes.add(CarteIngredient.CreerAvecTableau("Chant de sirène", new int[]
				{1, 3, 1, 0,
				 1, 2, 1, 1,
				 0, 1, 4, 0}
		));
		cartes.add(CarteIngredient.CreerAvecTableau("Chant de sirène", new int[]
				{2, 1, 1, 1,
				 1, 0, 2, 2,
				 3, 0, 0, 2}
		));
		cartes.add(CarteIngredient.CreerAvecTableau("Chant de sirène", new int[]
				{1, 2, 2, 0,
				 1, 1, 2, 1,
				 2, 0, 1, 2}
		));
		
		//Larmes de Dryade
		cartes.add(CarteIngredient.CreerAvecTableau("Larmes de Dryade", new int[]
				{2, 1, 1, 2,
				 1, 1, 1, 3,
				 2, 0, 2, 2}
		));
		cartes.add(CarteIngredient.CreerAvecTableau("Larmes de Dryade", new int[]
				{0, 3, 0, 3,
				 2, 1, 3, 0,
				 1, 1, 3, 1}
		));
		cartes.add(CarteIngredient.CreerAvecTableau("Larmes de Dryade", new int[]
				{1, 2, 1, 2,
				 1, 0, 1, 4,
				 2, 4, 0, 0}
		));
		
		//Fontaine d'eau pure
		cartes.add(CarteIngredient.CreerAvecTableau("Fontaine d'eau pure", new int[]
				{1, 3, 1, 2,
				 2, 1, 2, 2,
				 0, 0, 3, 4}
		));
		cartes.add(CarteIngredient.CreerAvecTableau("Fontaine d'eau pure", new int[]
				{2, 2, 0, 3,
				 1, 1, 4, 1,
				 1, 2, 1, 3}
		));
		cartes.add(CarteIngredient.CreerAvecTableau("Fontaine d'eau pure", new int[]
				{2, 2, 3, 1,
				 2, 3, 0, 3,
				 1, 1, 3, 3}
		));
		
		//Poudre d'or
		cartes.add(CarteIngredient.CreerAvecTableau("Poudre d'or", new int[]
				{2, 2, 3, 1,
				 2, 3, 0, 3,
				 1, 1, 3, 3}
		));
		cartes.add(CarteIngredient.CreerAvecTableau("Poudre d'or", new int[]
				{2, 2, 2, 2,
				 0, 4, 4, 0,
				 1, 3, 2, 2}
		));
		cartes.add(CarteIngredient.CreerAvecTableau("Poudre d'or", new int[]
				{3, 1, 3, 1,
				 1, 4, 2, 1,
				 2, 4, 1, 1}
		));
		
		//Racines d'arc-en-ciel
		cartes.add(CarteIngredient.CreerAvecTableau("Racines d'arc-en-ciel", new int[]
				{4, 1, 1, 1,
				 1, 2, 1, 3,
				 1, 2, 2, 2}
		));
		cartes.add(CarteIngredient.CreerAvecTableau("Racines d'arc-en-ciel", new int[]
				{2, 3, 2, 0,
				 0, 4, 3, 0,
				 2, 1, 1, 3}
		));
		cartes.add(CarteIngredient.CreerAvecTableau("Racines d'arc-en-ciel", new int[]
				{2, 2, 3, 0,
				 1, 1, 1, 4,
				 2, 0, 3, 2}
		));
		
		//Esprit de dolmen
		cartes.add(CarteIngredient.CreerAvecTableau("Esprit de dolmen", new int[]
				{3, 1, 4, 1,
				 2, 1, 3, 3,
				 2, 3, 2, 2}
		));
		cartes.add(CarteIngredient.CreerAvecTableau("Esprit de dolmen", new int[]
				{2, 4, 1, 2,
				 2, 2, 2, 3,
				 1, 4, 3, 1}
		));
		cartes.add(CarteIngredient.CreerAvecTableau("Esprit de dolmen", new int[]
				{3, 3, 3, 0,
				 1, 3, 3, 2,
				 2, 3, 1, 3}
		));
		
		//Rires de fées
		cartes.add(CarteIngredient.CreerAvecTableau("Rires de fées", new int[]
				{1, 2, 2, 1,
				 1, 2, 3, 0,
				 0, 2, 2, 2}
		));
		cartes.add(CarteIngredient.CreerAvecTableau("Rires de fées", new int[]
				{4, 0, 1, 1,
				 1, 1, 3, 1,
				 0, 0, 3, 3}
		));
		cartes.add(CarteIngredient.CreerAvecTableau("Rires de fées", new int[]
				{2, 0, 1, 3,
				 0, 3, 0, 3,
				 1, 2, 2, 1}
		));
		
		return cartes;
	}
	
	public static List<CarteAllies> genererCartesAllies() {
		ArrayList<CarteAllies> cartes = new ArrayList<CarteAllies>();
		
		//Taupes géantes
		cartes.add(TaupesGeantes.CreerAvecTableau(new int[]{1, 1, 1, 1}));
		cartes.add(TaupesGeantes.CreerAvecTableau(new int[]{0, 2, 2, 0}));
		cartes.add(TaupesGeantes.CreerAvecTableau(new int[]{0, 1, 2, 1}));
		
		//Chiens de garde
		cartes.add(ChiensDeGarde.CreerAvecTableau(new int[]{2, 0, 2, 0}));
		cartes.add(ChiensDeGarde.CreerAvecTableau(new int[]{1, 2, 0, 1}));
		cartes.add(ChiensDeGarde.CreerAvecTableau(new int[]{0, 1, 3, 0}));
		
		return cartes;
	}
}
