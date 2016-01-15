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
import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.cartes.InfoCarteAlliesJouee;
import fr.utt.girardguittard.levasseur.menhir.cartes.InfoCarteIngredientJouee;
import fr.utt.girardguittard.levasseur.menhir.cartes.CarteAllies;
import fr.utt.girardguittard.levasseur.menhir.joueurs.MainJoueur;

/**
 * Représente un joueur avec son score et les différentes actions qu'il réalise au cours d'une manche.
 */
public abstract class Joueur {
	
	/**
	 * Score du joueur
	 */
	private int scoreTotal;
	
	/**
	 * La main du joueur
	 */
	protected MainJoueur main;
	
	/**
	 * Le numéro du joueur
	 */
	private final int numero;
	
	/**
	 * Constructeur du joueur.
	 * Un joueur commence avec un score de 0.
	 * @param numero le numéro du joueur (à partir de 0)
	 */
	public Joueur(int numero) {
		this.scoreTotal = 0;
		this.numero = numero;
	}

	/**
	 * Effectue les opérations qui ont lieu au cours d'un tour.
	 * Il s'agit de décider qu'elle action réaliser et de l'effectuer.
	 * @param manche la manche en cours
	 * @param saison la saison en cours
	 * @return un objet décrivant la carte jouée
	 * @throws CarteInvalideException 
	 */
	public InfoCarteIngredientJouee jouerTour(Manche manche, Saison saison) throws CarteInvalideException {
		ChoixCarteIngredient choix = deciderChoixDuTour(manche, saison);
		
		//On vérifie bien que la carte est dans la main du joueur
		if(choix == null || choix.getCarteChoisie() == null ||!this.main.contientCarteIngredient(choix.getCarteChoisie())) {
			throw new CarteInvalideException("La carte choisie est invalide (aucune ou pas dans la main du joueur) !");
		}
		
		int forceReelle = choix.getCarteChoisie().agir(manche, this.main, choix.getCible(), saison, choix.getActionChoisie());
		this.getMain().retirerCarteIngredient(choix.getCarteChoisie());
		
		return new InfoCarteIngredientJouee(this.numero, saison, choix, forceReelle);
	}
	
	/**
	 * Permet au joueur de jouer une carte alliés s'il en a une.
	 * @param manche la manche en cours
	 * @param saison la saison en cours
	 * @param joueurActuel le numéro du joueur dont c'est le tour actuellement
	 * @return un objet décrivant la carte alliés jouée ou non
	 */
	public InfoCarteAlliesJouee jouerCartesAllies(Manche manche, Saison saison, int joueurActuel) {
		if(this.getMain().getCarteAllies() != null)
		{
			ChoixCarteAllies choix = deciderCarteAllies(manche, saison, joueurActuel);
			if(choix.isJoue())
			{
				CarteAllies carteAllies = this.getMain().getCarteAllies();
				int forceReelle = carteAllies.agir(manche, this.getMain(), choix.getCible(), saison);
				
				this.getMain().retirerCarteAllies();
				return new InfoCarteAlliesJouee(this.numero, saison, choix, carteAllies, forceReelle);
			}
		}
		
		return new InfoCarteAlliesJouee(this.numero, saison, new ChoixCarteAllies(false, -1), null, 0);
	}
	
	/**
	 * Incrémente le score du joueur
	 * @param inc le nombre de points que le joueur vient de gagner
	 */
	public void incrementerScore(int inc) {
		this.scoreTotal += inc;
	}
	
	/**
	 * Permet de décider de l'action a réaliser en fonction de la carte ingrédient tirée.
	 * @param manche la manche en cours
	 * @param saison la saison en cours
	 */
	protected abstract ChoixCarteIngredient deciderChoixDuTour(Manche manche, Saison saison);
	
	/**
	 * Permet de choisir une carte alliés.
	 * @param manche la manche en cours
	 * @param saison la saison en cours
	 * @param joueurActuel le numéro du joueur
	 */
	protected abstract ChoixCarteAllies deciderCarteAllies(Manche manche, Saison saison, int joueurActuel);
	
	/**
	 * Permet de savoir si le joueur souhaite une carte alliée ou deux graines
	 * @return true si le joueur veut une carte alliés
	 */
	public abstract boolean veutCarteAllies();
	
	public int getNumero() {
		return numero;
	}
	
	public int getScore() {
		return this.scoreTotal;
	}
	
	public MainJoueur getMain() {
		return this.main;
	}
	
	public void setMain(MainJoueur main) {
		this.main = main;
	}
}
