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

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.strategie.*;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import java.util.Random;


/**
 * Classe représentant les joueurs controllés par l'IA.
 *
 */
public class JoueurVirtuel extends Joueur{
	
	/**
	 * Stratégie du joueur virtuel.
	 */
	private Strategie strat;
	
	/**
	 * Le constructeur de joueur virtuel lui assigne aléatoirement une stratégie.
	 * @param numero le numéro du joueur (à partir de 0)
	 */
	public JoueurVirtuel(int numero) {
		super(numero);
		Random random = new Random();
		if (random.nextInt(2) == 0) {
			this.strat = new StrategieAggressive();
		}
		else {
			this.strat = new StrategieDefensive();
		}
	}
	
	/**
	 * Permet de décider de l'action à réaliser en fonction de la carte ingrédient tirée.
	 * @param manche la manche en cours
	 * @param saison le saison en cours
	 */
	protected ChoixCarteIngredient deciderChoixDuTour(Manche manche, Saison saison){
		return strat.deciderChoixDuTour(manche, saison, main);
	}
	
	/**
	 * Permet de choisir une carte alliés.
	 * @param manche la manche en cours
	 * @param saison la saison actuelle
	 * @param joueurActuel le numéro du joueur
	 */
	protected ChoixCarteAllies deciderCarteAllies(Manche manche, Saison saison, int joueurActuel) {
		return strat.deciderCarteAllies(manche, saison, joueurActuel, main);
	}
	
	public boolean veutCarteAllies() {
		return strat.deciderCarteOuGraines(this.main);
	}
}
