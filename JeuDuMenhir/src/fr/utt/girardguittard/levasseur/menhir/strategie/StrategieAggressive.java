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
import fr.utt.girardguittard.levasseur.menhir.cartes.Action;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;

public class StrategieAggressive implements Strategie {
	
	/**
	 * La méthode getMax permet de connaître la carte la plus forte dans la main du joueur pour une action et une saison donnée
	 * @param main la main du joueur
	 * @param saison la saison concernée
	 * @param action l'action concernée
	 * @return la carte la plus puissante pour cette saison et action
	 */
	private CarteIngredient getMax(MainJoueur main, Saison saison, Action action) {
		int max = 0;
		CarteIngredient carte = main.getCarteIngredient(0);
		for (int i = 0; i<main.getNombreCarteIngredient(); i++) {
			if (main.getCarteIngredient(i).getForce(saison, action) > max) {
				max = main.getCarteIngredient(i).getForce(saison, action);
				carte = main.getCarteIngredient(i);
			}
		}
		return carte;
	}
	
	public ChoixCarteIngredient deciderChoixDuTour(Manche manche, Saison tour, MainJoueur main) {
		
		//Au printemps l'objectif sera systématiquement de récolter le plus de graines possibles
		if(tour == Saison.PRINTEMPS) {
			
			//Sélection de la carte ayant la plus grande force sur l'action géant au printemps
			return new ChoixCarteIngredient(getMax(main, tour, Action.GEANT), 0, Action.GEANT);
		}
		
		/*En été on on privilégie le fait de voler ces adversaires sauf si :		
		 *- notre nombre de graines dépasse le nombre de graines maximal que l'on puisse transformé en menhir
		 *(dans ce cas on utilise la meilleure carte engrais)
		 *- la meilleure carte géant surpasse de deux points la meilleure carte farfadet
		 */
		else if(tour == Saison.ETE) {
			
			//Détermination du maximum de chaque catégorie :
			CarteIngredient maxFarfadet = getMax(main, tour, Action.FARFADET);
			CarteIngredient maxEngrais = getMax(main, tour, Action.ENGRAIS);
			CarteIngredient maxGeant = getMax(main, tour, Action.GEANT);
			
			//Selection de la carte que l'on va joué
			if(main.getNombreGraine() > maxEngrais.getForce(tour, Action.ENGRAIS)) {
				return new ChoixCarteIngredient(maxEngrais, 0, Action.ENGRAIS);
			}
			else if(maxGeant.getForce(tour, Action.GEANT) >= maxFarfadet.getForce(tour, Action.FARFADET) + 2) {
				return new ChoixCarteIngredient(maxGeant, 0, Action.GEANT);
			}
			else {
				
				//On vole le joueur ayant le plus de graines
				int max = -1;
				int cible = 0;
				for(int i = 0; i<manche.getNombreJoueurs(); i++) {
					if(main.getJoueur().getNumero() != i && manche.getJoueur(i).getMain().getNombreGraine() > max) {
						max = manche.getJoueur(i).getMain().getNombreGraine();
						cible = i;
					}
				}
				return new ChoixCarteIngredient(maxFarfadet, cible, Action.FARFADET);
			}
		}
		
		//L'automne fonctionne de la même façon que l'hiver
		else if(tour == Saison.AUTOMNE) {
			//Détermination du maximum de chaque catégorie :
			CarteIngredient maxFarfadet = getMax(main, tour, Action.FARFADET);
			CarteIngredient maxEngrais = getMax(main, tour, Action.ENGRAIS);
			CarteIngredient maxGeant = getMax(main, tour, Action.GEANT);
			
			//Selection de la carte que l'on va joué
			if(main.getNombreGraine() > maxEngrais.getForce(tour, Action.ENGRAIS)) {
				return new ChoixCarteIngredient(maxEngrais, 0, Action.ENGRAIS);
			}
			else if(maxGeant.getForce(tour, Action.GEANT) >= maxFarfadet.getForce(tour, Action.FARFADET) + 2) {
				return new ChoixCarteIngredient(maxGeant, 0, Action.GEANT);
			}
			else {
				
				//On vole le joueur ayant le plus de graines
				int max = -1;
				int cible = 0;
				for(int i = 0; i<manche.getNombreJoueurs(); i++) {
					if(main.getJoueur().getNumero() != i && manche.getJoueur(i).getMain().getNombreGraine() > max) {
						max = manche.getJoueur(i).getMain().getNombreGraine();
						cible = i;
					}
				}
				return new ChoixCarteIngredient(maxFarfadet, cible, Action.FARFADET);
			}
		}
		
		//En hiver il faut absolument faire pousser ces dernières graines en menhirs
		else{
			
			//Selection de la carte ayant la plus grande force sur l'action engrais en hiver
			return new ChoixCarteIngredient(getMax(main, tour, Action.ENGRAIS), 0, Action.ENGRAIS);
		}
	}
	
	public ChoixCarteAllies deciderCarteAllies(Manche manche, Saison tour, int joueurActuel, MainJoueur main) {

		//On joue la carte Taupes Geantes en hiver si le joueur avec le plus de menhir
		if(tour == Saison.HIVER) {
			if(main.getCarteAllies().getNom() == "Taupes géantes") {
				//On l'envoie sur le joueur ayant le plus de menhir
				//On vole le joueur ayant le plus de graines
				int max = -1;
				int cible = 0;
				for(int i = 0; i<manche.getNombreJoueurs(); i++) {
					if(main.getJoueur().getNumero() != i && manche.getJoueur(i).getMain().getNombreMenhir() > max) {
						max = manche.getJoueur(i).getMain().getNombreMenhir();
						cible = i;
					}
				}
				return new ChoixCarteAllies(true, cible);
			}
			else {
				return new ChoixCarteAllies(false, 0);
			}
		}
		
		//La carte Chiens de Gardes doit être joué au plus tard à l'automne
		else if(tour == Saison.AUTOMNE) {
			if(main.getCarteAllies().getNom() == "Chiens de garde") {
				return new ChoixCarteAllies(true, 0);
			}
			else{
				
				//On utilise la carte Taupes Geante si un adversaire à plus de menhir que sa force
				int max = -1;
				int cible = 0;
				for(int i = 0; i<manche.getNombreJoueurs(); i++) {
					if(main.getJoueur().getNumero() != i && manche.getJoueur(i).getMain().getNombreMenhir() > max) {
						max = manche.getJoueur(i).getMain().getNombreMenhir();
						cible = i;
					}
				}
				if(max > main.getCarteAllies().getForce(tour)) {
					return new ChoixCarteAllies(true, cible);
				}
				else {
					return new ChoixCarteAllies(false, 0);
				}
			}
		}
		//Si l'on est au printemps ou en été on peut jouer une carte si elle est rentable
		else {
			if(main.getCarteAllies().getNom() == "Chiens de garde") {
				
				//On utilise la carte Chiens de garde si l'on a plus de graines que ça force
				if(main.getNombreGraine() > main.getCarteAllies().getForce(tour)) {
					return new ChoixCarteAllies(true, 0);
				} else {
					return new ChoixCarteAllies(false, 0);
				}
			}
			else{
				
				//On utilise la carte Taupes Geante si un adversaire à plus de menhir que sa force
				int max = -1;
				int cible = 0;
				for(int i = 0; i<manche.getNombreJoueurs(); i++) {
					if(main.getJoueur().getNumero() != i && manche.getJoueur(i).getMain().getNombreMenhir() > max) {
						max = manche.getJoueur(i).getMain().getNombreMenhir();
						cible = i;
					}
				}
				if(max > main.getCarteAllies().getForce(tour)) {
					return new ChoixCarteAllies(true, cible);
				} else {
					return new ChoixCarteAllies(false, 0);
				}
			}
		}
	}
	
	/**
	 * La stratégie aggressive prend toujours une carte allies
	 */
	public boolean deciderCarteOuGraines(MainJoueur main) {
		return true;
	}
}
