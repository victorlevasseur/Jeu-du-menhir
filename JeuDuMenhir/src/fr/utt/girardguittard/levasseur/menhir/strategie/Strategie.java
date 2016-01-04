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

package fr.utt.girardguittard.levasseur.menhir.strategie;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.joueurs.ChoixCarteAllies;
import fr.utt.girardguittard.levasseur.menhir.joueurs.ChoixCarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

/**
 * Permet aux joueurs virtuels de décider des actions à réaliser
 */
public interface Strategie {
	
	/**
	 * Permet de décider de l'action à réaliser en fonction de la carte ingrédient tirée
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param main la main du joueur
	 * @return le choix d'action et de carte que fait l'algorithme
	 */
	public ChoixCarteIngredient deciderChoixDuTour(Manche manche, Saison tour, MainJoueur main);
	
	/**
	 * Permet de choisir une carte allié
	 * @param manche la manche en cours
	 * @param tour le tour en cours
	 * @param joueurActuel le numéro du joueur dont c'est le tour actuellement
	 * @param main la main du joueur (qui choisit s'il joue la carte alliés ou non, pas forcément le joueur dont c'est le tour actuellement)
	 * @return le choix de jouer ou non la carte alliés (ainsi que la cible dans le cas des Taupes Geantes)
	 */
	public ChoixCarteAllies deciderCarteAllies(Manche manche, Saison tour, int joueurActuel, MainJoueur main); 

	/**
	 * Permet de choisir si l'on prend deux graines ou une carte.
	 * @param main la main du joueur
	 * @return true si une carte allies est choisie
	 */
	public boolean deciderCarteOuGraines(MainJoueur main);
}
