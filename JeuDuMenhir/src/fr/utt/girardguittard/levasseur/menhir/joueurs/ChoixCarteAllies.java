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

package fr.utt.girardguittard.levasseur.menhir.joueurs;

/**
 * Classe contenant la carte alliés choisie par le joueur.
 *
 */
public class ChoixCarteAllies {

	/**
	 * Le booléen indiquant si le joueur souhaite jouer ou non sa carte alliés.
	 */
	private final boolean joue;
	
	/**
	 * La cible de la carte alliés
	 */
	private final int cible;
	
	/**
	 * Le constructeur de la classe, permettant au joueur d'initialiser les variables
	 * @param joue jouer ou non la carte alliés
	 * @param cible la cible de l'action
	 */
	public ChoixCarteAllies(boolean joue, int cible) {
		this.joue = joue;
		this.cible = cible;
	}

	/**
	 * Getter de la carte
	 * @return la carte alliés choisie
	 */
	public boolean isJoue() {
		return joue;
	}

	/**
	 * Getter de la cible
	 * @return le numéro du joueur ciblé
	 */
	public int getCible() {
		return cible;
	}
	
}
