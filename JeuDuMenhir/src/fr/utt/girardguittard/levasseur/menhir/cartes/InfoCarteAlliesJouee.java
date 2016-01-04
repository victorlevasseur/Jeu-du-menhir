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

import fr.utt.girardguittard.levasseur.menhir.Saison;
import fr.utt.girardguittard.levasseur.menhir.joueurs.ChoixCarteAllies;

/**
 * Classe permettant de transporter les données à notifier aux vues à
 * propos d'une carte alliés qui a été jouée. Elle permet, entre autre,
 * de connaître la force effective/réelle de la carte lorsqu'elle a été jouée
 * (exemple : le nombre de menhirs vraiment détruit chez un adversaire).
 */
public class InfoCarteAlliesJouee {

	private final int numeroJoueur;
	
	private final Saison saison;
	
	private final CarteAllies carteJouee;
	
	private final int joueurCible;
	
	private final int forceTheorique;
	
	private final int forceReelle;
	
	public InfoCarteAlliesJouee(int numeroJoueur, Saison saison, ChoixCarteAllies choix, CarteAllies carteAllies, int forceReelle) {
		this.numeroJoueur = numeroJoueur;
		this.saison = saison;
		this.carteJouee = choix.isJoue() ? carteAllies : null;
		this.joueurCible = choix.getCible();
		this.forceTheorique = carteAllies != null ? carteAllies.getForce(saison) : 0;
		this.forceReelle = forceReelle;
	}

	public int getNumeroJoueur() {
		return numeroJoueur;
	}

	public Saison getSaison() {
		return saison;
	}
	
	public boolean isJouee() {
		return this.carteJouee != null;
	}

	public CarteAllies getCarteJouee() {
		return carteJouee;
	}

	public int getJoueurCible() {
		return joueurCible;
	}

	public int getForceTheorique() {
		return forceTheorique;
	}

	public int getForceReelle() {
		return forceReelle;
	}
	
}
