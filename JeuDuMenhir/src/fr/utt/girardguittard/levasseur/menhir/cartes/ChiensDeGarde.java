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
 * Représene une carte chiens de garde.
 * @author victor
 *
 */
public class ChiensDeGarde extends CarteAllies{
	
	public ChiensDeGarde(HashMap<Saison, Integer> forces) {
		super(forces);
	}
	
	/**
	 * Créée une carte alliés ChienDeGarde à partir des forces sous forme d'un tableau à 4 valeurs
	 * représentant les forces pour chaque saisons (de PRINTEMPS à HIVER).
	 * @param forces un tableau de forces
	 * @return une carte ChienDeGarde
	 */
	public static ChiensDeGarde CreerAvecTableau(int[] forces) {
		if(forces.length != 4) {
			//TODO: Exceptions !
		}
		
		HashMap<Saison, Integer> forcesMap = new HashMap<Saison, Integer>();
		
		forcesMap.put(Saison.PRINTEMPS, forces[0]);
		forcesMap.put(Saison.ETE, forces[1]);
		forcesMap.put(Saison.AUTOMNE, forces[2]);
		forcesMap.put(Saison.HIVER, forces[3]);
		
		return new ChiensDeGarde(forcesMap);
	}
	
	public String getNom() {
		return "Chiens de garde";
	}

	/**
	 * Ajoute de la défense au joueur
	 * Ceci lui permet de se protéger contre une attaque de farfadet et de perdre moins de graines
	 * @param manche la manche en cours
	 * @param main la main du joueur utilisant la carte
	 * @param joueurCible le joueur ciblé par l'action (inutile)
	 * @param saisonActuelle la saison en cours
	 */
	public int agir(Manche manche, MainJoueur main, int joueurCible, Saison saisonActuelle){
		main.setDefenseChienDeGarde(this.getForce(saisonActuelle));
		
		return main.getDefenseChienDeGarde();
	}
}
