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

package fr.utt.girardguittard.levasseur.menhir.util;

import java.util.Comparator;

import fr.utt.girardguittard.levasseur.menhir.joueurs.Joueur;

/**
 * Permet de trier de façon décroissante les joueurs suivant leur score dans un manche.
 */
public class ScoreMancheComparator implements Comparator<Joueur> {

	public int compare(Joueur j1, Joueur j2) {
		if(j1.getMain().getNombreMenhir() != j2.getMain().getNombreMenhir()) {
			return j2.getMain().getNombreMenhir() - j1.getMain().getNombreMenhir();
		} else {
			return j2.getMain().getNombreGraine() - j1.getMain().getNombreGraine();
		}
	}

}
