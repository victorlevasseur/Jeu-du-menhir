package fr.utt.girardguittard.levasseur.menhir.strategie;

import fr.utt.girardguittard.levasseur.menhir.Manche;
import fr.utt.girardguittard.levasseur.menhir.Partie;
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.Action;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.joueurs.ChoixCarteAllies;
import fr.utt.girardguittard.levasseur.menhir.joueurs.ChoixCarteIngredient;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

public class StrategieDefensive implements Strategie{
	
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
		
		/*En été on on privilégie géant sauf si :		
		 *- notre nombre de graines dépasse le nombre de graines maximal que l'on puisse transformer en menhir
		 *(dans ce cas on utilise la meilleure carte engrais)
		 *- la meilleure carte farfadeet surpasse de deux points la meilleure carte geant
		 */
		if(tour == Saison.ETE) {
			
			//Détermination du maximum de chaque catégorie :
			CarteIngredient maxFarfadet = getMax(main, tour, Action.FARFADET);
			CarteIngredient maxEngrais = getMax(main, tour, Action.ENGRAIS);
			CarteIngredient maxGeant = getMax(main, tour, Action.GEANT);
			
			//Selection de la carte que l'on va joué
			if(main.getNombreGraine() > maxEngrais.getForce(tour, Action.ENGRAIS)) {
				return new ChoixCarteIngredient(maxEngrais, 0, Action.ENGRAIS);
			}
			else if(maxFarfadet.getForce(tour, Action.FARFADET) >= maxEngrais.getForce(tour, Action.ENGRAIS) + 2) {
				//On vole le joueur ayant le plus de graines
				int max = manche.getJoueur(0).getMain().getNombreGraine();
				int cible = 0;
				for(int i = 0; i<manche.getNombreJoueurs(); i++) {
					if(manche.getJoueur(i).getMain().getNombreGraine() > max) {
						max = manche.getJoueur(i).getMain().getNombreGraine();
						cible = i;
					}
				}
				return new ChoixCarteIngredient(maxFarfadet, cible, Action.FARFADET);			}
			else {
				return new ChoixCarteIngredient(maxGeant, 0, Action.GEANT);
			}
		}
		
		//L'automne fonctionne de la même façon que l'hiver
		if(tour == Saison.AUTOMNE) {
			//Détermination du maximum de chaque catégorie :
			CarteIngredient maxFarfadet = getMax(main, tour, Action.FARFADET);
			CarteIngredient maxEngrais = getMax(main, tour, Action.ENGRAIS);
			CarteIngredient maxGeant = getMax(main, tour, Action.GEANT);
			
			//Selection de la carte que l'on va joué
			if(main.getNombreGraine() > maxEngrais.getForce(tour, Action.ENGRAIS)) {
				return new ChoixCarteIngredient(maxEngrais, 0, Action.ENGRAIS);
			}
			else if(maxFarfadet.getForce(tour, Action.FARFADET) >= maxEngrais.getForce(tour, Action.ENGRAIS) + 2) {
				//On vole le joueur ayant le plus de graines
				int max = manche.getJoueur(0).getMain().getNombreGraine();
				int cible = 0;
				for(int i = 0; i<manche.getNombreJoueurs(); i++) {
					if(manche.getJoueur(i).getMain().getNombreGraine() > max) {
						max = manche.getJoueur(i).getMain().getNombreGraine();
						cible = i;
					}
				}
				return new ChoixCarteIngredient(maxFarfadet, cible, Action.FARFADET);			}
			else {
				return new ChoixCarteIngredient(maxGeant, 0, Action.GEANT);
			}
		}
		
		//En hiver il faut absolument transférer ces dernières graines en menhirs
		if(tour == Saison.HIVER) {
			
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
				int max = manche.getJoueur(0).getMain().getNombreMenhir();
				int cible = 0;
				for(int i = 0; i<manche.getNombreJoueurs(); i++) {
					if(manche.getJoueur(i).getMain().getNombreMenhir() > max) {
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
		if(tour == Saison.AUTOMNE) {
			if(main.getCarteAllies().getNom() == "Chiens de garde") {
				return new ChoixCarteAllies(true, 0);
			}
			else {
				return new ChoixCarteAllies(false, 0);
			}
		}
		
		//Si l'on est au printemps ou en été on peut jouer la carte chiens de garde si elle est rentable
		if(tour == Saison.ETE || tour == Saison.PRINTEMPS) {
			if(main.getCarteAllies().getNom() == "Chiens de garde") {
				if(main.getNombreGraine() > main.getCarteAllies().getForce(tour)) {
					return new ChoixCarteAllies(true, 0);
				}
				else {
					return new ChoixCarteAllies(false, 0);
				}
			}
			else {
				return new ChoixCarteAllies(false, 0);
			}
		}
	}
}
