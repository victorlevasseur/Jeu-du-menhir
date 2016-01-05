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

import java.util.HashMap;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

/**
 * Représente les cartes "alliés" présente dans la version avancée du jeu.
 * Chaque carte dispose d'une action
 */
public abstract class CarteAllies {
	
	private HashMap<Saison, Integer> forces;
	
	public CarteAllies(HashMap<Saison, Integer> forces) {
		this.forces = forces;
	}
	
	public abstract String getNom();
	
	public int getForce(Saison tour) {
		return this.forces.get(tour);
	}
	
	/**
	 * Réalise l'action de la carte
	 * @param manche la manche en cours
	 * @param main la main du joueur utilisant la carte
	 * @param joueurCible le joueur ciblé par l'action (si besoin)
	 * @param tour le tour en cours
	 * @return la force réelle avec laquelle la carte a agit
	 */
	public abstract int agir(Manche manche, MainJoueur main, int joueurCible, Saison tour);
	
	public String toString() {
		StringBuffer buf = new StringBuffer();

		buf.append("== " + this.getNom() + " ==\n");
		buf.append("          P  E  A  H\n");
		buf.append("   Force  ");
		for(Saison saison : Saison.values()) {
			buf.append(this.getForce(saison));
			buf.append("  ");
		}
		
		return buf.toString();
	}
	
}
